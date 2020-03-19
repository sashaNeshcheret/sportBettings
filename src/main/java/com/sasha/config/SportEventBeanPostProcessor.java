package com.sasha.config;

import com.sasha.entity.sportevents.SportEvent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SportEventBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Class<?>> mapNameToClass = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        Annotation[] annotations = aClass.getAnnotations();
        for(Annotation annotation : annotations){
            if(annotation.annotationType().getName().equals("com.sasha.entity.sportevents.Event")){
                mapNameToClass.put(beanName, aClass);
//                SportEvent sportEvent = (SportEvent) bean;
//                System.out.println("before init " + sportEvent.getTitle());
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = mapNameToClass.get(beanName);
        if(null != aClass){
            SportEvent sportEvent = (SportEvent) bean;
            if (StringUtils.startsWithIgnoreCase(sportEvent.getTitle(), "$")) {
                sportEvent.setTitle("barabaka vs sabaka");
            }
//            System.out.println("after init " + sportEvent.getTitle());
        }
        return bean;
    }

}
