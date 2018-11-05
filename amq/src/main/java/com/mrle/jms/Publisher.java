package com.mrle.jms;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
public class Publisher {

    @Autowired
    private JmsMessagingTemplate jmsTmp;

    public void publish(String destNm, String msg) {
        Destination d = new ActiveMQTopic(destNm);
        System.out.println("========>>>>>> 发布topic消息: " + msg);
        jmsTmp.convertAndSend(d, msg);
    }
}
