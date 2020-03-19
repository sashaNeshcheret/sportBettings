package com.sasha.factories;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
//TODO check if it is needed
public class CustomBeanFactory implements ApplicationContextAware {
    @Override
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
