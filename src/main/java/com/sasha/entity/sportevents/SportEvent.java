package com.sasha.entity.sportevents;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

public abstract class SportEvent {
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public SportEvent(String title, LocalDateTime startDate, LocalDateTime endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @PostConstruct
    public void init(){
//        System.out.println("init " + title);
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
