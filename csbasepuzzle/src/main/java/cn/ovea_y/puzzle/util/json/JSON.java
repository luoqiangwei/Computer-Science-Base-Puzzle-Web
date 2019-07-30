package cn.ovea_y.puzzle.util.json;


import cn.ovea_y.puzzle.util.json.exption.JSONExption;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class JSON {
//    数组请用包装类型！ 绝对不用使用原生类型！
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
                        System.out.println(method.getReturnType().getName());
                        if(method.getReturnType().getName().equals("java.lang.Integer") || method.getReturnType().getName().equals("int") || method.getReturnType().getName().equals("java.lang.Boolean") || method.getReturnType().getName().equals("boolean") || method.getReturnType().getName().equals("java.lang.Long") || method.getReturnType().getName().equals("long") || method.getReturnType().getName().equals("java.lang.Float") || method.getReturnType().getName().equals("float") || method.getReturnType().getName().equals("java.lang.Double") || method.getReturnType().getName().equals("double") || method.getReturnType().getName().equals("java.lang.Byte") || method.getReturnType().getName().equals("byte")){
                            t.append("\":");
                            t.append(method.invoke(object, null).toString());
                        }else if(method.getReturnType().getName().equals("java.util.Map")){
                            Map map = (Map) method.invoke(object, null);
                            t.append("\":{");
                            Set key = map.keySet();
                            for(Object item : key){
                                t.append("\"" + item + "\":");
                                Object oo = map.get(item);
                                if(!oo.getClass().getName().contains("java.lang")){
                                    t.append(objectToJson(oo));
                                }else{
                                    t.append(oo);
                                }
                                t.append(",");
                            }
                            t.setCharAt(t.length() - 1, '}');
                        }else if(method.getReturnType().isArray()){
//                            Object[] objects = (Object[]) method.invoke(object, null);
                            t.append("\":");
                            t.append(Arrays.deepToString((Object[]) method.invoke(object, null)));
                        }else if(!method.getReturnType().getName().contains("java.lang")){
                            t.append("\":");
                            t.append(objectToJson(method.invoke(object, null)));
                        }else{
                            t.append("\":\"");
                            t.append(method.invoke(object, null).toString());
                            t.append("\"");
                        }
                    } catch (NullPointerException e){
                        t.setCharAt(t.length() - 1, 'n');
                        t.append("ull");
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
                        t.setCharAt(t.length() - 1, 'n');
                        t.append("ull");
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

    private static String findField(List<String> list, String x){
        for(String temp : list){
            if(temp.toUpperCase().equals(x.toUpperCase())){
                return temp;
            }
        }
        return null;
    }
}
