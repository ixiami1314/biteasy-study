package com.biteasy.study.jms;

import org.springframework.stereotype.Service;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by xiaoxia on 16/3/13.
 */
@Service ("consumerForBiteasyEvent")
public class BiteasyEventListener implements MessageListener {

    @Override
    public void onMessage (Message message) {
        if (message.getClass().equals (BiteasyEvent.class)) {
            // 直接把消息存进DB
        }
    }
}
