package com.sasha.entity.wagers;

import com.sasha.entity.bets.OutcomeOdd;
import com.sasha.entity.users.User;
import com.sasha.util.Currency;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="wager")
public class Wager {
    //    Wager: the wager placed by a Player on an outcome;
//    It stores the odd, the amount and the currency of the bet,
//    the date and time when the bet is created and the state of having been processed or not.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private String userId;
    @Column(name = "outcome_odd_id")
    private OutcomeOdd outcomeOdd;
    @Column
    private BigDecimal amount;
    @Column
    private Currency currency;
    @Column(name = "created_time")
    private LocalDateTime createdTime;
    @Column(name = "is_processed")
    private boolean isProcessed;
    @Column(name = "wager_state")
    private WagerState wagerState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
