package com.sasha.entity.wagers;

import com.sasha.entity.bets.OutcomeOdd;
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
    @Column(name = "wager_id")
    private Integer id;

    @Column(name = "user_id")
    private String userId;
    @OneToOne//(mappedBy = "wagers", cascade = CascadeType.ALL)
    @JoinColumn(name = "outcome_odd_id")
    private OutcomeOdd odd;
//    private String odd;
    @Column
    private BigDecimal amount;
    @Column
    private String currency;
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

    public OutcomeOdd getOdd() {
        return null;
    }

    public void setOdd(OutcomeOdd odd) {
        this.odd = odd;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency.toString();
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
