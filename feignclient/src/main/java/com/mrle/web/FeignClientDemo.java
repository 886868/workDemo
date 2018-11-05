package com.mrle.web;

import com.mrle.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(value = "service-hi", configuration = FeignConfig.class,fallback = HiFeign.class)
public interface FeignClientDemo {
    @GetMapping("/hi/{name}")
    Map sayHiFromClientEureka(@PathVariable("name")String name);
}
