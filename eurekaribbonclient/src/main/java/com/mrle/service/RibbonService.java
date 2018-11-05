package com.mrle.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RibbonService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiErr")
    public Map hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi/"+name,Map.class);
    }

    public Map hiErr(String name) {
        Map<String, String> rst = new HashMap<>();
        rst.put("errMsg", "error!!" + "param:" + name);
        return rst;
    }
}
