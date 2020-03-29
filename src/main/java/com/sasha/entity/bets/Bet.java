package com.sasha.entity.bets;

import com.sasha.entity.sportevents.FootballSportEvent;
import com.sasha.entity.sportevents.SportEvent;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bet")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="bet_event", joinColumns={@JoinColumn(name="bet", referencedColumnName="id")}
            , inverseJoinColumns={@JoinColumn(name="event_id", referencedColumnName="id")})
    private FootballSportEvent event;

    @Column
    private String description;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="bet_outcome", joinColumns={@JoinColumn(name="bet", referencedColumnName="id")}
            , inverseJoinColumns={@JoinColumn(name="outcome_id", referencedColumnName="id")})
    private List<Outcome> outcome;

    @Column(name = "bets_type")
    private String betsType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SportEvent getEvent(){
        return event;
    }

    public void setEvent(FootballSportEvent event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Outcome> getOutcomeList() {
        return outcome;
    }

    public void setOutcomeList(List<Outcome> outcomeList) {
        this.outcome = outcomeList;
    }

    public String getBetsType() {
        return betsType;
    }

    public void setBetsType(BetsType betsType) {
        this.betsType = betsType.toString();
    }

}
