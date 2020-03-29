package com.sasha.services;

import com.sasha.entity.sportevents.FootballSportEvent;
import com.sasha.entity.sportevents.SportEvent;
import com.sasha.entity.sportevents.TennisSportEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SportEventCreator {
    private FootballSportEvent footballEvent;
    private TennisSportEvent tennisEvent;

    public SportEventCreator(FootballSportEvent footballEvent, TennisSportEvent tennisEvent) {
        this.footballEvent = footballEvent;
        this.tennisEvent = tennisEvent;
    }


    FootballSportEvent createSportEvent(int eventNumber) {
//        SportEvent sportEvent;
        switch (eventNumber) {
            case 0:
//                sportEvent = createFootballEvent();
                return footballEvent;
//            case 1:
////                sportEvent = createTennisEvent();
//                return tennisEvent;
            default: return null;
        }
    }

//    private SportEvent createFootballEvent() {
//        return new FootballSportEvent("Southampton vs Bournemoth",
//                LocalDateTime.parse("2020-03-20T09:00:00"),
//                LocalDateTime.parse("2020-03-20T11:00:00"));
//    }
//
//    private SportEvent createTennisEvent() {
//        return new TennisSportEvent("Rafael Nadal vs. Alexander Zverev",
//                LocalDateTime.parse("2020-03-20T06:00:00"),
//                LocalDateTime.parse("2020-03-20T10:00:00"));
//    }

    public List<FootballSportEvent> generateSportsEvent(){
        List<FootballSportEvent> sportEvents = new ArrayList<>();
//        for(int i = 0; i < 2; i++){
        int i = 0;
            sportEvents.add(createSportEvent(i));
//        }
        return sportEvents;
    }

}
