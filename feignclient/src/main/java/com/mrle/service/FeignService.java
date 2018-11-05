package com.mrle.service;

import com.mrle.web.FeignClientDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FeignService {
    @Autowired
    private FeignClientDemo feignClientDemo;

    public Map sayHi(String name){
        return feignClientDemo.sayHiFromClientEureka(name);
    }

}
