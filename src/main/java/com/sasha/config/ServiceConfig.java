package com.sasha.config;

import com.sasha.entity.bets.Bet;
import com.sasha.entity.bets.Outcome;
import com.sasha.entity.sportevents.FootballSportEvent;
import com.sasha.entity.sportevents.TennisSportEvent;
import com.sasha.entity.wagers.Wager;
import com.sasha.services.BetService;
import com.sasha.services.GameService;
import com.sasha.services.SportEventCreator;
import com.sasha.services.UserService;
import com.sasha.ui.BetIO;
import com.sasha.ui.InputReader;
import com.sasha.ui.UserFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

@Configuration
@PropertySource("classpath:/application.properties")
public class ServiceConfig {

    @Value("${football.title}")
    private String footballEventTitle;

    @Value("${football.start.time}")
    private String footballEventStartTime;

    @Value("${football.end.time}")
    private String footballEventEndTime;

    @Value("${tennis.title}")
    private String tennisEventTitle;

    @Value("${tennis.start.time}")
    private String tennisEventStartTime;

    @Value("${tennis.end.time}")
    private String tennisEventEndTime;

    @Bean
    public GameService gameService(SportEventCreator sportEventGenerator, BetService betService, UserService userService, UserFactory userFactory, BetIO betIO, Random random){
        return new GameService(sportEventGenerator, betService, userService, userFactory, betIO, random);
    }

    @Bean
    public SportEventCreator sportEventCreator(FootballSportEvent footballEvent, TennisSportEvent tennisEvent){
        return new SportEventCreator(footballEvent, tennisEvent);
    }

    @Bean
    public BetService betService(BetIO betIO){
        return new BetService(betIO);
    }

    @Bean
    public UserService userService(InputReader reader){
        return new UserService();
    }

    @Bean
    public UserFactory userFactory(InputReader reader){
        return new UserFactory(new HashMap<>(), reader);
    }

    @Bean
    public BetIO betIO(InputReader reader){
        return new BetIO(reader);
    }

    @Bean
    public InputReader inputReader(Scanner scanner){
        return new InputReader(scanner);
    }

    @Bean
    public Scanner scaner(){
        return new Scanner(System.in);
    }

    @Bean
    public Random random(){
        return new Random();
    }


    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Wager wager(){
        return new Wager();
    }

    @Bean
    @Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
    public Bet bet(){
        return new Bet();
    }

    @Bean
    @Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
    public Outcome outcome(){
        return new Outcome();
    }

    @Bean
    public FootballSportEvent footballSportEvent(){
        return new FootballSportEvent(footballEventTitle,
                LocalDateTime.parse(footballEventStartTime),
                LocalDateTime.parse(footballEventEndTime));
    }

    @Bean
    public TennisSportEvent tennisSportEvent(){
        return new TennisSportEvent(tennisEventTitle,
                LocalDateTime.parse(tennisEventStartTime),
                LocalDateTime.parse(tennisEventEndTime));
    }

//    @Bean
//    public static CustomBeanFactoryPostProcessor customBeanFactoryPostProcessor(){
//        return new CustomBeanFactoryPostProcessor();
//    }
//
    @Bean
    public static SportEventBeanPostProcessor customBeanPostProcessor(){
        return new SportEventBeanPostProcessor();
    }
//
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer configurer(){
//        return new PropertySourcesPlaceholderConfigurer();
//    }
}
