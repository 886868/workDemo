package com.mrle.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HiFeign implements FeignClientDemo {
    @Override
    public Map sayHiFromClientEureka(String name) {
        Map<String, String> rst = new HashMap<>();
        rst.put("errMsg", "error!!" + "param:" + name);
        return rst;
    }
}
