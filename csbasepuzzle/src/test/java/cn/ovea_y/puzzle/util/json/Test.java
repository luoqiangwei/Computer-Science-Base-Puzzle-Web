package cn.ovea_y.puzzle.util.json;

import cn.ovea_y.puzzle.util.json.exption.JSONExption;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {
    @org.junit.Test
    public void fun() throws JSONExption {
        User user = new User("OVEA", new Car("OX", "RED"));
        Map map = new HashMap();
        map.put("String", new Car("BOX", "black"));
        map.put(1, 34);
        user.setId(map);
        System.out.println(JSON.objectToJson(user));
    }
}
