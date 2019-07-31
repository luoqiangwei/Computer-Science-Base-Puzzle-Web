package cn.ovea_y.puzzle.util.json;


import cn.ovea_y.puzzle.util.commons.DateFormatter;
import cn.ovea_y.puzzle.util.json.exption.JSONExption;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Qiangwei Luo
 */
public class JSON {
    public static String objectToJson(Object object) throws JSONExption {
        Class clazz = object.getClass();
        Method[] methods = clazz.getMethods();
        Field[] fields = clazz.getDeclaredFields();
        List<String> list = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder("{");
        if(fields != null){
            for(Field field : fields){
                list.add(field.getName());
            }
        }
        if (methods != null) {
            for(Method method : methods){
                if(method.getName().startsWith("get") && !method.getName().equals("getClass")) {
                    StringBuilder t = new StringBuilder();
                    t.append("\"");
                    String temp = findField(list, method.getName().substring(3));
                    if(temp == null){
                        t.append(method.getName().substring(3));
                        t.setCharAt(1, String.valueOf(t.charAt(1)).toLowerCase().charAt(0));
                    }else{
                        t.append(temp);
                    }
                    try {
                        if(method.getReturnType().getName().equals("java.lang.Integer") || method.getReturnType().getName().equals("int") || method.getReturnType().getName().equals("java.lang.Boolean") || method.getReturnType().getName().equals("boolean") || method.getReturnType().getName().equals("java.lang.Long") || method.getReturnType().getName().equals("long") || method.getReturnType().getName().equals("java.lang.Float") || method.getReturnType().getName().equals("float") || method.getReturnType().getName().equals("java.lang.Double") || method.getReturnType().getName().equals("double") || method.getReturnType().getName().equals("java.lang.Byte") || method.getReturnType().getName().equals("byte") || method.getReturnType().getName().equals("java.lang.Short") || method.getReturnType().getName().equals("short")){
                            t.append("\":");
                            t.append(method.invoke(object, null).toString());
                        }else if(method.getReturnType().getName().contains("java.util.") && method.getReturnType().getName().contains("Map")){
                            Map map = (Map) method.invoke(object, null);
                            t.append("\":");
                            deepFindMap(t, map);
                        }else if(method.getReturnType().getName().contains("java.util.") && method.getReturnType().getName().contains("Set")){
                            Set set = (Set) method.invoke(object, null);
                            t.append("\":");
                            deepFindSet(t, set);
                        }else if(method.getReturnType().getName().contains("java.util.") && method.getReturnType().getName().contains("List")){
                            List l = (List) method.invoke(object, null);
                            t.append("\":");
                            deepFindList(t, l);
                        }else if(method.getReturnType().isArray()){
                            t.append("\":");
                            deepFindArray(t, method.invoke(object, null));
                        }else if(method.getReturnType().getName().contains("java.util.") && method.getReturnType().getName().contains("Date")){
                            t.append("\":\"");
                            t.append(DateFormatter.formatToDate(new Date().getTime()));
                            t.append("\"");
                        }else if(!(method.getReturnType().getName().indexOf("java") == 0)){
                            t.append("\":");
                            t.append(objectToJson(method.invoke(object, null)));
                        }else{
                            t.append("\":\"");
                            t.append(method.invoke(object, null).toString());
                            t.append("\"");
                        }
                    } catch (NullPointerException e){
                        if(t.charAt(t.length() - 1) == '"') {
                            t.setCharAt(t.length() - 1, 'n');
                            t.append("ull");
                        }else {
                            t.append("null");
                        }
                    } catch (Exception e){
                        throw new JSONExption(e.getMessage());
                    }
                    t.append(",");
                    stringBuilder.append(t);
                }
                if(method.getName().startsWith("is")){
                    StringBuilder t = new StringBuilder();
                    t.append("\"");
                    String temp = findField(list, method.getName().substring(2));
                    if(temp == null){
                        t.append(method.getName().substring(2));
                        t.setCharAt(1, String.valueOf(t.charAt(1)).toLowerCase().charAt(0));
                    }else{
                        t.append(temp);
                    }
                    try {
                        t.append("\":\"");
                        t.append(method.invoke(object, null).toString());
                        t.append("\"");
                    } catch (NullPointerException e){
                        if(t.charAt(t.length() - 1) == '"') {
                            t.setCharAt(t.length() - 1, 'n');
                            t.append("ull");
                        }else {
                            t.append("null");
                        }
                    } catch (Exception e){
                        throw new JSONExption(e.getMessage());
                    }
                    t.append(",");
                    stringBuilder.append(t);
                }
            }
        }
        if(stringBuilder.length() != 1) {
            stringBuilder.setCharAt(stringBuilder.length() - 1, '}');
        } else {
            stringBuilder.append("}");
        }
        return stringBuilder.toString();
    }

    private static void deepFindSet(StringBuilder t, Set set) throws JSONExption {
        if(set != null){
            t.append("[");
            for(Object o : set){
                if(o.getClass().isArray()){
                    deepFindArray(t, o);
                }else if(o instanceof Set){
                    deepFindSet(t, (Set) o);
                }else if(o instanceof Map){
                    deepFindMap(t, (Map) o);
                }else if(o instanceof List){
                    deepFindList(t, (List) o);
                }else if(o instanceof Date){
                    t.append(DateFormatter.formatToDate(new Date().getTime()));
                }else if(o.getClass().getName().indexOf("java") == 0 && !o.getClass().getName().contains("Object")){
                    t.append(o);
                    t.append(',');
                }else {
                    t.append(objectToJson(o));
                    t.append(',');
                }
            }
            if(t.charAt(t.length() - 1) == '[') {
                t.append(']');
            }else {
                t.setCharAt(t.length() - 1, ']');
            }
        }else {
            t.append("null");
        }
    }

    private static void deepFindList(StringBuilder t, List list) throws JSONExption {
        if(list != null){
            t.append("[");
            for(Object o : list){
                if(o.getClass().isArray()){
                    deepFindArray(t, o);
                }else if(o instanceof Set){
                    deepFindSet(t, (Set) o);
                }else if(o instanceof Map){
                    deepFindMap(t, (Map) o);
                }else if(o instanceof List){
                    deepFindList(t, (List) o);
                }else if(o instanceof Date){
                    t.append(DateFormatter.formatToDate(new Date().getTime()));
                }else if(o.getClass().getName().indexOf("java") == 0 && !o.getClass().getName().contains("Object")){
                    t.append(o);
                    t.append(',');
                }else {
                    t.append(objectToJson(o));
                    t.append(',');
                }
            }
            if(t.charAt(t.length() - 1) == '[') {
                t.append(']');
            }else {
                t.setCharAt(t.length() - 1, ']');
            }
        }else {
            t.append("null");
        }
    }

    private static void deepFindMap(StringBuilder t, Map map) throws JSONExption {
        Set key = map.keySet();
        t.append("{");
        for(Object item : key){
            if(item.getClass().getName().equals("java.lang.Integer") || item.getClass().getName().equals("int") || item.getClass().getName().equals("java.lang.Boolean") || item.getClass().getName().equals("boolean") || item.getClass().getName().equals("java.lang.Long") || item.getClass().getName().equals("long") || item.getClass().getName().equals("java.lang.Float") || item.getClass().getName().equals("float") || item.getClass().equals("java.lang.Double") || item.getClass().getName().equals("double") || item.getClass().getName().equals("java.lang.Byte") || item.getClass().getName().equals("byte") || item.getClass().getName().equals("java.lang.Short") || item.getClass().getName().equals("short")){
                t.append(item + ":");
            }else if(item instanceof Map){
                deepFindMap(t, map);
                t.append(":");
            }else if(item.getClass().isArray()){
                deepFindArray(t, item);
                t.append(":");
            }else if(item instanceof Set){
                deepFindSet(t, (Set) item);
                t.append(":");
            }else if(item instanceof List){
                deepFindList(t, (List) item);
                t.append(":");
            }else if(item instanceof Date){
                t.append(DateFormatter.formatToDate(new Date().getTime()));
            }else if(item.getClass().getName().indexOf("java") == 0){
                t.append("\"" + item + "\":");
            }else {
                t.append(objectToJson(item));
                t.append(":");
            }
            Object oo = map.get(item);
            if(oo.getClass().getName().indexOf("java") == 0){
                if(oo instanceof Map){
                    deepFindMap(t, (Map) oo);
                }else if(item instanceof Set){
                    deepFindSet(t, (Set) item);
                }else if(item instanceof List){
                    deepFindList(t, (List) item);
                }else if(item.getClass().getName().equals("java.lang.Integer") || item.getClass().getName().equals("int") || item.getClass().getName().equals("java.lang.Boolean") || item.getClass().getName().equals("boolean") || item.getClass().getName().equals("java.lang.Long") || item.getClass().getName().equals("long") || item.getClass().getName().equals("java.lang.Float") || item.getClass().getName().equals("float") || item.getClass().equals("java.lang.Double") || item.getClass().getName().equals("double") || item.getClass().getName().equals("java.lang.Byte") || item.getClass().getName().equals("byte") || item.getClass().getName().equals("java.lang.Short") || item.getClass().getName().equals("short")){
                    t.append(oo);
                }else if(item instanceof Date){
                    t.append(DateFormatter.formatToDate(new Date().getTime()));
                }else{
                    t.append("\"" + oo + "\"");
                }
            }else if(oo.getClass().isArray()){
                deepFindArray(t, oo);
            }else{
                t.append(objectToJson(oo));
            }
            t.append(",");
        }
        if(t.charAt(t.length() - 1) == '{') {
            t.append('}');
        }else {
            t.setCharAt(t.length() - 1, '}');
        }
    }

    private static void deepFindArray(StringBuilder stringBuilder, Object object) throws JSONExption {
        if(object.getClass().getName().contains("[[")){
            Object[] objects = (Object[]) object;
            stringBuilder.append("[");
            for(Object o : objects){
                deepFindArray(stringBuilder, o);
                stringBuilder.append(",");
            }
            if(stringBuilder.charAt(stringBuilder.length() - 1) == '[') {
                stringBuilder.append(']');
            }else {
                stringBuilder.setCharAt(stringBuilder.length() - 1, ']');
            }

        }else if(!object.getClass().getName().contains("[[") && object.getClass().getName().contains("[")){
            switch (object.getClass().getName()){
                case "[B":
                    stringBuilder.append(Arrays.toString((byte[])object));
                    break;
                case "[C":
                    stringBuilder.append(Arrays.toString((char[])object));
                    break;
                case "[S":
                    stringBuilder.append(Arrays.toString((short[])object));
                    break;
                case "[I":
                    stringBuilder.append(Arrays.toString((int[])object));
                    break;
                case "[L":
                    stringBuilder.append(Arrays.toString((long[])object));
                    break;
                case "[F":
                    stringBuilder.append(Arrays.toString((float[])object));
                    break;
                case "[D":
                    stringBuilder.append(Arrays.toString((double[])object));
                    break;
                case "[Z":
                    stringBuilder.append(Arrays.toString((boolean[])object));
                    break;
                default:
                    if(object.getClass().getName().contains("[Ljava.lang") && !object.getClass().getName().contains("Object")){
                        stringBuilder.append(Arrays.toString((Object[]) object));
                    }else{
                        stringBuilder.append('[');
                        for(Object o : (Object[]) object){
                            if(o instanceof Map){
                                deepFindMap(stringBuilder, (Map) o);
                            }else if(o instanceof Set){
                                deepFindSet(stringBuilder, (Set) o);
                            }else if(o instanceof List){
                                deepFindList(stringBuilder, (List) o);
                            }else if(o instanceof Date){
                                stringBuilder.append(DateFormatter.formatToDate(new Date().getTime()));
                            }else {
                                stringBuilder.append(objectToJson(o));
                                stringBuilder.append(",");
                            }
                        }
                        if(stringBuilder.charAt(stringBuilder.length() - 1) == '[' || stringBuilder.charAt(stringBuilder.length() - 1) == '}') {
                            stringBuilder.append(']');
                        }else {
                            stringBuilder.setCharAt(stringBuilder.length() - 1, ']');
                        }
                    }
            }
        }
    }

    private static String findField(List<String> list, String x){
        for(String temp : list){
            if(temp.toUpperCase().equals(x.toUpperCase())){
                return temp;
            }
        }
        return null;
    }
}
