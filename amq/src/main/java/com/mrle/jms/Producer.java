package com.mrle.jms;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

@Component
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsTmp;

    public void sendMsg(String desNm, String msg) {
        System.out.println("==========>>>>>> 发送queue消息 " + msg);
        Destination d = new ActiveMQQueue(desNm);

        jmsTmp.convertAndSend(d, msg);
    }
}
