package com.mrle.json;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonTest {
    @Test
    public void json1xTest() throws IOException {
        ObjectMapper om = new ObjectMapper();

        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, String> map1 = new HashMap<String, String>();

        map1.put("a","aaa");
        map.put("head", map1);
        String aaaaaaa  = om.writeValueAsString(map);


        Map<String, Map<String, String>> reqMap = om.readValue(aaaaaaa, new TypeReference<Map<String, Map<String, String>>>() {});
        reqMap.get("head").put("a","bbb");

        System.out.println(om.writeValueAsString(reqMap));

    }
}
