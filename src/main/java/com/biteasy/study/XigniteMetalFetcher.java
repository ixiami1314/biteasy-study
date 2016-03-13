package com.biteasy.study;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xiaoxia on 16/3/13.
 */
public class XigniteMetalFetcher {
    public static void main (String [] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext ("classpath:spring/biteasy-base.xml");

    }
}
