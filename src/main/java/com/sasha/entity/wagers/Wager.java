package com.sasha.entity.wagers;

import com.sasha.entity.bets.OutcomeOdd;
import com.sasha.entity.users.User;
import com.sasha.util.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Wager {
    //    Wager: the wager placed by a Player on an outcome;
//    It stores the odd, the amount and the currency of the bet,
//    the date and time when the bet is created and the state of having been processed or not.
    private User player;
    private OutcomeOdd outcomeOdd;
    private BigDecimal amount;
    private Currency currency;
    private LocalDateTime createdTime;
    private boolean isProcessed;
    private WagerState wagerState;

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public OutcomeOdd getOutcomeOdd() {
        return outcomeOdd;
    }

    public void setOutcomeOdd(OutcomeOdd outcomeOdd) {
        this.outcomeOdd = outcomeOdd;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public WagerState getWagerState() {
        return wagerState;
    }

    public void setWagerState(WagerState wagerState) {
        this.wagerState = wagerState;
    }
}
