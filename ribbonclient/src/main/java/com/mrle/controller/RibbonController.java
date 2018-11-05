package com.mrle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController {
    @Autowired
    private LoadBalancerClient loadBalancer;

    @GetMapping("/trb")
    public String trb(){
        ServiceInstance instance = loadBalancer.choose("stores");
        return instance.getHost() + ":" + instance.getPort();
    }
}
