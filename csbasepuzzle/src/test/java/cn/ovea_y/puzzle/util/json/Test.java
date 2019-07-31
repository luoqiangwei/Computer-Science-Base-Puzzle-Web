package cn.ovea_y.puzzle.util.json;

import cn.ovea_y.puzzle.util.json.exption.JSONExption;

import java.util.*;

public class Test {
    @org.junit.Test
    public void fun() throws JSONExption {
        User user = new User("OVEA", new Car("OX", "RED"));
        HashMap map = new HashMap();
        Map map1 = new HashMap();
        map.put("String", new Car("BOX", "black"));
//        map.put("String", "box");
        map.put('1', new int[][]{new int[]{12, 34,656 ,34}, new int[]{1}});
//        user.setCars(new Car[][]{new Car[]{new Car("AA", "AAC"), new Car("BB", "BBC")}, new Car[]{}});
        user.setArray(new Object[]{map});
        user.setId(map);
        Set set = new HashSet();
        set.add(23);
//        set.add(map);
        user.setSet(set);
        user.setDate(new Date());
//        User user1 = new User(null, null);
//        System.out.println(JSON.objectToJson(user1));
        System.out.println(JSON.objectToJson(user));
        //{"name":"OVEA","array":[{1=[[12, 34, 656, 34],[1]],"String"={"name":"BOX","color":"black"}],
        // "id":{1=[[12, 34, 656, 34],[1]],"String"={"name":"BOX","color":"black"}},
        // "set":[23,{1=[[12, 34, 656, 34],[1]],"String"={"name":"BOX","color":"black"}],
        // "list":null,"cars":[[{"name":"AA","color":"AAC"},{"name":"BB","color":"BBC"}],[]],"car":{"name":"OX","color":"RED"}}
    }
}
