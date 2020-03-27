package com.sasha.entity.bets;

import com.sasha.entity.wagers.Wager;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "outcome_odd")
public class OutcomeOdd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "outcome_odd_value")
    private String OutcomeOddValue;

    @Column(name = "odd_value")
    private BigDecimal oddValue;

    @Column(name = "valid_to")
    private LocalDateTime validTo;

    @Column(name = "valid_from")
    private LocalDateTime validFrom;

//    @OneToOne(mappedBy = "odd")
    private Wager wager;

    public String getOutcomeOddValue() {
        return OutcomeOddValue;
    }

    public void setOutcomeOddValue(String outcomeOddValue) {
        OutcomeOddValue = outcomeOddValue;
    }

    public BigDecimal getOddValue() {
        return oddValue;
    }

    public void setOddValue(BigDecimal oddValue) {
        this.oddValue = oddValue;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutcomeOdd that = (OutcomeOdd) o;
        return OutcomeOddValue.equals(that.OutcomeOddValue) &&
                oddValue.equals(that.oddValue) &&
                validFrom.equals(that.validFrom) &&
                validTo.equals(that.validTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(OutcomeOddValue, oddValue, validFrom, validTo);
    }
}
