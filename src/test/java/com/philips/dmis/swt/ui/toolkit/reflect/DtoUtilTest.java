package com.philips.dmis.swt.ui.toolkit.reflect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DtoUtilTest {
    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static class TestClass {
        public static int getStaticValue() {
            return 1;
        }

        public String someField0;
        private String someField1;
        private String someField2;
        private String someField3;
        private String SomeFIELD4;

        public String getSomeField2() {
            return someField2;
        }

        public void setSomeField3(String someField3) {
            this.someField3 = someField3;
        }

        public String getSomeFIELD4() {
            return SomeFIELD4;
        }
    }

    @Test
    public void test() throws JsonProcessingException {
        List<Member> properties = DtoUtil.getProperties(TestClass.class);
        List<String> jsonKeys = properties.stream().map(m -> DtoUtil.getJsonKey(m)).sorted().toList();
        //assertArrayEquals(new Object[]{"someFIELD4", "someField0", "someField2"}, jsonKeys.toArray());
        String json = OBJECT_MAPPER.writeValueAsString(new TestClass());
        Map<?, ?> obj = OBJECT_MAPPER.readValue(json, Map.class);
        List<String> jsonKeysFromSerializer = obj.keySet().stream().map(k -> k.toString()).sorted().toList();
        assertArrayEquals(jsonKeys.toArray(), jsonKeysFromSerializer.toArray());
    }
}
