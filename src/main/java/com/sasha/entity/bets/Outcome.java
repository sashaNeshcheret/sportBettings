package com.sasha.entity.bets;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "outcome")
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String value;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="outcome_outcome_odd", joinColumns={@JoinColumn(name="outcome_id", referencedColumnName="id")}
            , inverseJoinColumns={@JoinColumn(name="outcome_odd_id", referencedColumnName="id")})
    private List<OutcomeOdd> outcomeOdds;

//    public Outcome(String value, List<OutcomeOdd> outcomeOdds) {
//        this.value = value;
//        this.outcomeOdds = outcomeOdds;
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
