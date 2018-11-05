package com.mrle.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @JmsListener(destination = "test.queue")
    public void receiveMsg(String text) {
        System.out.println("<<<<<<========= 收到消息：" + text);
    }
}
