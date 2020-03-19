package com.sasha.ui;

import java.math.BigDecimal;

public class BetIO {
    private InputReader reader;

    public BetIO(InputReader reader) {
        this.reader = reader;
    }

    public BigDecimal getInputAmount(){
        System.out.println("What amount do you wish to bet on it? (press q to quit)");
        String amount = reader.getNextLine();
        return  new BigDecimal(amount);
    }

    public String getInputtedBetVariant(){
        return reader.getNextLine();
    }
}
