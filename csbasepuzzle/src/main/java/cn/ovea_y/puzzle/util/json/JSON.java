package cn.ovea_y.puzzle.util.json;

import cn.oveay.classdesign.util.json.exption.JSONExption;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
