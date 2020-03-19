package com.sasha.services;

import com.sasha.entity.bets.*;
import com.sasha.entity.sportevents.FootballSportEvent;
import com.sasha.entity.sportevents.SportEvent;
import com.sasha.ui.BetIO;
import com.sasha.util.StopProcessingException;
import com.sasha.util.UnsupportedBetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class BetService {
    private final static Logger logger = Logger.getLogger("BetService");
    private BetIO betIO;

    @Autowired
    private AnnotationConfigApplicationContext context;

    public BetService(BetIO betIO) {
        this.betIO = betIO;
    }

    public DisplayedBet getChosenBet(List<Bet> bets) throws StopProcessingException {
        List<DisplayedBet> displayedBets = receiveDisplayedBets(bets);
        showCurrentDisplayedBets(displayedBets);
        return receiveBet(displayedBets);
    }

    private List<DisplayedBet> receiveDisplayedBets(List<Bet> bets) {
        System.out.println("Please choose an outcome to bet on! (choose a number or press q for quit)");
        List<DisplayedBet> displayedBets = new ArrayList<>();
        for (Bet bet : bets) {
            displayedBets.addAll(getDisplayedBetEvents(bet));
        }
        return displayedBets;
    }

    private void showCurrentDisplayedBets(List<DisplayedBet> displayedBets) {
        int i = 1;
        for (DisplayedBet displayedBet : displayedBets) {
            OutcomeOdd outcomeOdd = displayedBet.getOutcomeOdd();
            System.out.println(i++ + ": Bet on the " + displayedBet.getEventTitle() + " sport event, " +
                    displayedBet.getBetTitle() +
                    ". The odd on this is " + outcomeOdd.getOddValue() +
                    ", valid from " + outcomeOdd.getValidFrom() +
                    " to " + outcomeOdd.getValidTo());
        }
    }

    private DisplayedBet receiveBet(List<DisplayedBet> bets) throws StopProcessingException {
        String variant = betIO.getInputtedBetVariant();
        if ("q".equalsIgnoreCase(variant)) {
            throw new StopProcessingException();
        }
        return bets.get(Integer.valueOf(variant) - 1);
    }

    public List<Bet> createBets(List<SportEvent> sportEvents) {
        List<Bet> bets = new ArrayList<>();
        for (SportEvent event : sportEvents) {
            bets.add(createWinBets(event));
            if (event instanceof FootballSportEvent) {
                bets.add(createGoalsBets(event));
//                createScoreBets(event);
            }
        }
        return bets;
    }

    private Bet createWinBets(SportEvent event) {
        Bet bet = context.getBean(Bet.class);
        bet.setSportEvent(event);
        bet.setBetsType(BetsType.WinnerBet);
        bet.setOutcomeList(createOutcomes(event, BetsType.WinnerBet));
        return bet;
    }

    private Bet createGoalsBets(SportEvent event) {
        Bet bet = context.getBean(Bet.class);
        bet.setSportEvent(event);
        bet.setBetsType(BetsType.GoalsBet);
        bet.setOutcomeList(createOutcomes(event));
        return bet;
    }

    private List<Outcome> createOutcomes(SportEvent event, BetsType type) {
        List<Outcome> outcomes = new ArrayList<>();
        String[] betNames = StringUtils.split(event.getTitle(), "vs");
        for (String value : betNames) {
            Outcome outcome = context.getBean(Outcome.class);
            outcome.setValue(value.trim());
            outcome.setOutcomeOdds(generateOutcomeOdds(event));
            outcomes.add(outcome);
        }
        return outcomes;
    }

    private List<Outcome> createOutcomes(SportEvent event) {
        List<Outcome> outcomes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Outcome outcome = context.getBean(Outcome.class);
            outcome.setValue("the number of scored goals will be " + i);
            outcome.setOutcomeOdds(generateOutcomeOdds(event));
            outcomes.add(outcome);
        }
        return outcomes;
    }

    private List<OutcomeOdd> generateOutcomeOdds(SportEvent event) {
        List<OutcomeOdd> odds = new ArrayList<>();
        Random random = new Random();

        LocalDateTime finalDate = event.getStartDate();
        OutcomeOdd firstOutcomeOdd = new OutcomeOdd();
        firstOutcomeOdd.setOutcomeOddValue("#1");
        int i = random.nextInt(5);
        firstOutcomeOdd.setOddValue(new BigDecimal((int) (random.nextDouble() * 5)));
        firstOutcomeOdd.setValidFrom(finalDate.minusDays(10));
        firstOutcomeOdd.setValidTo(finalDate.minusDays(i));
        odds.add(firstOutcomeOdd);
        OutcomeOdd secondOutcomeOdd = new OutcomeOdd();
        secondOutcomeOdd.setOutcomeOddValue("#2");
        secondOutcomeOdd.setOddValue(new BigDecimal((int) (random.nextDouble() * 5)));
        secondOutcomeOdd.setValidFrom(finalDate.minusDays(i));
        secondOutcomeOdd.setValidTo(finalDate);
        odds.add(secondOutcomeOdd);
        return odds;
    }

    private List<DisplayedBet> getDisplayedBetEvents(Bet bet) {
        List<DisplayedBet> displayedBets = new ArrayList<>();
        for (Outcome outcome : bet.getOutcomeList()) {
            DisplayedBet displayedBet = new DisplayedBet();
            displayedBet.setEventTitle(bet.getSportEvent().getTitle());
            displayedBet.setBetTitle(outcome.getValue());
            OutcomeOdd currentOdd;
            try {
                currentOdd = getCurrentOdd(outcome);
            } catch (UnsupportedBetException e) {
                logger.warning("Outcome must be verified");
                break;
            }
            displayedBet.setOutcomeOdd(currentOdd);
            displayedBets.add(displayedBet);
        }
        return displayedBets;
    }

    private OutcomeOdd getCurrentOdd(Outcome outcome) throws UnsupportedBetException {
        LocalDateTime now = LocalDateTime.now();
        for (OutcomeOdd odd : outcome.getOutcomeOdds()) {
            if (odd.getValidFrom().isBefore(now) && odd.getValidTo().isAfter(now)) {
                return odd;
            }
        }
        throw new UnsupportedBetException();
    }
}
