package com.sasha.entity.bets;

import com.sasha.entity.sportevents.SportEvent;

import java.util.List;

public class Bet {

    private SportEvent sportEvent;
    private String description;
    private List<Outcome> outcomeList;
    private BetsType betsType;


    public SportEvent getSportEvent(){
        return sportEvent;
    }

    public void setSportEvent(SportEvent sportEvent) {
        this.sportEvent = sportEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Outcome> getOutcomeList() {
        return outcomeList;
    }

    public void setOutcomeList(List<Outcome> outcomeList) {
        this.outcomeList = outcomeList;
    }

    public BetsType getBetsType() {
        return betsType;
    }

    public void setBetsType(BetsType betsType) {
        this.betsType = betsType;
    }
    @Override
    public String toString(){
        return this.sportEvent.getTitle() + this.betsType.toString();
    }
}
