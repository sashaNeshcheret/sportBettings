package com.sasha.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;

import java.util.Arrays;
import java.util.List;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
        System.out.println(configurableListableBeanFactory.getBeanPostProcessorCount());
        System.out.println(Arrays.toString(configurableListableBeanFactory.getDependenciesForBean("footballSportEvent")));
        System.out.println(configurableListableBeanFactory.getBeanDefinitionCount());
        BeanDefinition footballSportEvent = configurableListableBeanFactory.getBeanDefinition("footballSportEvent");
        ConstructorArgumentValues constructorArgumentValues = footballSportEvent.getConstructorArgumentValues();
        List<ConstructorArgumentValues.ValueHolder> genericArgumentValues = constructorArgumentValues.getGenericArgumentValues();
        MutablePropertyValues propertyValues = footballSportEvent.getPropertyValues();
        String scope = footballSportEvent.getScope();
        footballSportEvent.getRole();



    }
}
