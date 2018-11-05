package com.mrle.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HiController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/hi/{name}")
    public Map<String, String> hi(@PathVariable("name")String name) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("port", port);

        return map;
    }

}
