package com.sasha.entity.sportevents;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class SportEvent {

    public abstract String getTitle();

    public abstract LocalDateTime getStartDate();

    @PostConstruct
    public void init(){
//        System.out.println("init " + title);
    }
}
