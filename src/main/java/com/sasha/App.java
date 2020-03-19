package com.sasha;

import com.sasha.config.ServiceConfig;
import com.sasha.services.GameService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ServiceConfig.class);
        GameService gameService = annotationConfigApplicationContext.getBean(GameService.class);
        gameService.iterate();
    }
}
