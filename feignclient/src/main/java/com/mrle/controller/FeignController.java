package com.mrle.controller;

import com.mrle.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FeignController {

    @Autowired
    private FeignService feignService;

    @GetMapping("/hi/{name}")
    public Map sayHi(@PathVariable("name")String name){
        return feignService.sayHi(name);
    }

}
