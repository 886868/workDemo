package com.mrle.controller;

import com.mrle.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RibbonController {

    @Autowired
    private RibbonService ribbonService;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/hi/{name}")
    public Map<String, String> hi(@PathVariable("name")String name){
        return ribbonService.hiService(name);
    }

    @GetMapping("/testRb")
    public String testRibbon(){
        ServiceInstance instance = loadBalancerClient.choose("service-hi");
        return instance.getHost() + ":" + instance.getPort();
    }
}
