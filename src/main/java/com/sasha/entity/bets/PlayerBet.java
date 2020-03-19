package com.sasha.entity.bets;

import java.math.BigDecimal;

public class PlayerBet {
    private DisplayedBet bet;
    private BigDecimal value;

    public PlayerBet(DisplayedBet bet, BigDecimal value) {
        this.bet = bet;
        this.value = value;
    }

    public DisplayedBet getBet() {
        return bet;
    }

    public BigDecimal getValue() {
        return value;
    }
}
