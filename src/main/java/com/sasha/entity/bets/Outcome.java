package com.sasha.entity.bets;


import java.util.List;
import java.util.Objects;

public class Outcome {
    private String value;
    private List<OutcomeOdd> outcomeOdds;

//    public Outcome(String value, List<OutcomeOdd> outcomeOdds) {
//        this.value = value;
//        this.outcomeOdds = outcomeOdds;
//    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<OutcomeOdd> getOutcomeOdds() {
        return outcomeOdds;
    }

    public void setOutcomeOdds(List<OutcomeOdd> outcomeOdds) {
        this.outcomeOdds = outcomeOdds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outcome outcome = (Outcome) o;
        return Objects.equals(value, outcome.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
