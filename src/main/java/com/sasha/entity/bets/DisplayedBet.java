package com.sasha.entity.bets;

import org.springframework.context.annotation.EnableLoadTimeWeaving;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "displayed_bet")
public class DisplayedBet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "event_title")
    private String eventTitle;
    @Column(name = "betTitle")
    private String betTitle;
    @OneToOne
    @JoinTable(name="displayed_bet_outcome_odd", joinColumns={@JoinColumn(name="displayed_bet_id", referencedColumnName="id")}
            , inverseJoinColumns={@JoinColumn(name="outcome_odd_id", referencedColumnName="id")})
    private OutcomeOdd outcomeOdd;
    @Column(name = "valid_from")
    private LocalDateTime validFrom;
    @Column(name = "valid_to")
    private LocalDateTime validTo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
