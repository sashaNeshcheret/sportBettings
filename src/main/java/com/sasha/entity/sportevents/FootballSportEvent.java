package com.sasha.entity.sportevents;

import java.time.LocalDateTime;

@Event
public class FootballSportEvent extends SportEvent {

    public FootballSportEvent(String title, LocalDateTime startDate, LocalDateTime endDate) {
        super(title, startDate, endDate);
    }
}
