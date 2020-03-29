package com.sasha.services;

import com.sasha.dataAccess.BetRepository;
import com.sasha.dataAccess.DisplayedBetRepository;
import com.sasha.dataAccess.OutcomeOddRepository;
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
    private BetRepository<Bet> betRepository;
    private DisplayedBetRepository<DisplayedBet> displayedBetRepository;
    private Random random;

    @Autowired
    private AnnotationConfigApplicationContext context;

    public BetService(BetIO betIO, BetRepository<Bet> betRepository, DisplayedBetRepository<DisplayedBet> displayedBetRepository, Random random) {
        this.betIO = betIO;
        this.betRepository = betRepository;
        this.displayedBetRepository = displayedBetRepository;
        this.random = random;
    }

    public /*List<DisplayedBet>*/ void createDisplayedBets() {
        System.out.println("Please choose an outcome to bet on! (choose a number or press q for quit)");
        List<DisplayedBet> displayedBets = new ArrayList<>();
        List<Bet> bets = betRepository.findAll();
        for (Bet bet : bets) {
            displayedBets.addAll(getDisplayedBetEvents(bet));
        }
        for (DisplayedBet displayedBet : displayedBets) {
            displayedBetRepository.create(displayedBet);
        }
        return;
    }

    public List<DisplayedBet> showCurrentDisplayedBets() {
        int i = 1;
        List<DisplayedBet> displayedBets = displayedBetRepository.findAll();
        for (DisplayedBet displayedBet : displayedBets) {
            OutcomeOdd outcomeOdd = displayedBet.getOutcomeOdd();
            System.out.println(i++ + ": Bet on the " + displayedBet.getEventTitle() + " sport event, " +
                    displayedBet.getBetTitle() +
                    ". The odd on this is " + outcomeOdd.getOddValue() +
                    ", valid from " + outcomeOdd.getValidFrom() +
                    " to " + outcomeOdd.getValidTo());
        }
        return displayedBets;
    }

    public DisplayedBet receiveBet(List<DisplayedBet> bets) throws StopProcessingException {
        String variant = betIO.getInputtedBetVariant();
        if ("q".equalsIgnoreCase(variant)) {
            throw new StopProcessingException();
        }
        return bets.get(Integer.valueOf(variant) - 1);
    }

    public /*List<Bet>*/void createBets(List<FootballSportEvent> sportEvents) {
        List<Bet> bets = new ArrayList<>();
        for (FootballSportEvent event : sportEvents) {
            Bet bet = createWinBets(event);
            bets.add(bet);
            betRepository.create(bet);
            if (event instanceof FootballSportEvent) {
                Bet goalsBets = createGoalsBets(event);
                bets.add(goalsBets);
                betRepository.create(goalsBets);
//                createScoreBets(event);
            }

        }
//        return bets;
    }

    private Bet createWinBets(FootballSportEvent event) {
        Bet bet = context.getBean(Bet.class);
        bet.setEvent(event);
        bet.setBetsType(BetsType.WinnerBet);
        bet.setOutcomeList(createOutcomes(event, BetsType.WinnerBet));
        return bet;
    }

    private Bet createGoalsBets(FootballSportEvent event) {
        Bet bet = context.getBean(Bet.class);
        bet.setEvent(event);
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
//            outcomeRepository.create(outcome);
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
//            outcomeRepository.create(outcome);
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
//        outcomeOddRepository.create(firstOutcomeOdd);
        odds.add(firstOutcomeOdd);
        OutcomeOdd secondOutcomeOdd = new OutcomeOdd();
        secondOutcomeOdd.setOutcomeOddValue("#2");
        secondOutcomeOdd.setOddValue(new BigDecimal((int) (random.nextDouble() * 5)));
        secondOutcomeOdd.setValidFrom(finalDate.minusDays(i));
        secondOutcomeOdd.setValidTo(finalDate);
//        outcomeOddRepository.create(secondOutcomeOdd);
        odds.add(secondOutcomeOdd);
        return odds;
    }

    private List<DisplayedBet> getDisplayedBetEvents(Bet bet) {
        List<DisplayedBet> displayedBets = new ArrayList<>();
        for (Outcome outcome : bet.getOutcomeList()) {
            DisplayedBet displayedBet = new DisplayedBet();
            displayedBet.setEventTitle(bet.getEvent().getTitle());
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

    public List<Outcome> gameSimulation() {
        List<Outcome> winner = new ArrayList<>();
        List<Bet> bets = betRepository.findAll();
        for (Bet bet : bets) {
            switch (bet.getBetsType()) {
                case "WinnerBet":
                    winner.add(winnerSimulation(bet));
                    break;
                case "GoalsBet":
                case "ScoreBet":
                    winner.add(scoreGoalsSimulation(bet));
            }
        }
        return winner;
    }
    private Outcome winnerSimulation(Bet bet) {
        return bet.getOutcomeList().get(random.nextInt(2));
    }

    private Outcome scoreGoalsSimulation(Bet bet) {
        List<Outcome> outcomes = bet.getOutcomeList();
        return outcomes.get(random.nextInt(outcomes.size()));
    }
}
