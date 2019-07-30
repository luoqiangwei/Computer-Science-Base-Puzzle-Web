package cn.ovea_y.puzzle.util.json;

import cn.ovea_y.puzzle.util.json.exption.JSONExption;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {
    @org.junit.Test
    public void fun() throws JSONExption {
        User user = new User("OVEA", new Car("OX", "RED"));
        HashMap map = new HashMap();
        Map map1 = new HashMap();
        map.put("String", new Car("BOX", "black"));
        map.put(1, new int[][]{new int[]{12, 34,656 ,34}, new int[]{1}});
        user.setCars(new Car[][]{new Car[]{new Car("AA", "AAC"), new Car("BB", "BBC")}, new Car[]{}});
        user.setArray(new Object[]{map});
        user.setId(map);
        System.out.println(JSON.objectToJson(user));
    }
}
