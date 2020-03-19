package com.sasha.entity.sportevents;

import java.time.LocalDateTime;


public class TennisSportEvent extends SportEvent {

    public TennisSportEvent(String title, LocalDateTime startDate, LocalDateTime endDate) {
        super(title, startDate, endDate);
    }
}
