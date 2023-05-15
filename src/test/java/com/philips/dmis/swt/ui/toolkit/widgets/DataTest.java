package com.philips.dmis.swt.ui.toolkit.widgets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DataTest {
    @Test
    public void serializeNull() throws Exception {
        StaticData d = new StaticData(null);
        Assertions.assertEquals("", d.getJson());
    }

    @Test
    public void serializeArray() throws Exception {
        StaticData d = new StaticData(new String[]{"hello", "world"});
        Assertions.assertEquals("[\"hello\",\"world\"]", d.getJson());
    }

    @Test
    public void serializeList() throws Exception {
        StaticData d = new StaticData(Arrays.asList("hello", "world"));
        Assertions.assertEquals("[\"hello\",\"world\"]", d.getJson());
    }

    @Test
    public void serializeMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("fruit", Arrays.asList("apple", "banana"));
        StaticData d = new StaticData(map);
        Assertions.assertEquals("{\"fruit\":[\"apple\",\"banana\"]}", d.getJson());
    }

    @Test
    public void serializeString() throws Exception {
        StaticData d = new StaticData("String");
        Assertions.assertEquals("{\"items\":\"String\"}", d.getJson());
    }

    @Test
    public void serializeObject() throws Exception {
        class X {
            int id = 123;
            String firstName = "John";
            String lastName = "Doe";

            public int getId() {
                return id;
            }

            public String getFirstName() {
                return firstName;
            }

            public String getLastName() {
                return lastName;
            }
        }
        StaticData d = new StaticData(new X());
        Assertions.assertEquals("{\"id\":123,\"firstName\":\"John\",\"lastName\":\"Doe\"}", d.getJson());
    }
}
