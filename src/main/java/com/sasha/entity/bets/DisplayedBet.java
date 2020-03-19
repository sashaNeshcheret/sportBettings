package com.sasha.entity.bets;

import java.time.LocalDateTime;

public class DisplayedBet {
    private String eventTitle;
    private String betTitle;
    private OutcomeOdd outcomeOdd;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;

    public String getEventTitle() {
        return eventTitle;
    }

    public String getBetTitle() {
        return betTitle;
    }
    public OutcomeOdd getOutcomeOdd() {
        return outcomeOdd;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setBetTitle(String betTitle) {
        this.betTitle = betTitle;
    }

    public void setOutcomeOdd(OutcomeOdd outcomeOdd) {
        this.outcomeOdd = outcomeOdd;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
    }
}
