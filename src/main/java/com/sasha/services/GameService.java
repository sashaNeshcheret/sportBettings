package com.sasha.services;

import com.sasha.entity.bets.Bet;
import com.sasha.entity.bets.DisplayedBet;
import com.sasha.entity.bets.Outcome;
import com.sasha.entity.bets.OutcomeOdd;
import com.sasha.entity.sportevents.SportEvent;
import com.sasha.entity.users.User;
import com.sasha.entity.wagers.Wager;
import com.sasha.entity.wagers.WagerState;
import com.sasha.ui.BetIO;
import com.sasha.ui.UserFactory;
import com.sasha.util.StopProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class GameService {
    private SportEventCreator sportEventGenerator;
    private BetService betService;
    private UserService userService;
    private UserFactory userFactory;
    private BetIO betIO;
    private Random random;

    @Autowired
    private AnnotationConfigApplicationContext context;

    public GameService(SportEventCreator sportEventGenerator, BetService betService, UserService userService, UserFactory userFactory, BetIO betIO, Random random) {
        this.sportEventGenerator = sportEventGenerator;
        this.betService = betService;
        this.userService = userService;
        this.userFactory = userFactory;
        this.betIO = betIO;
        this.random = random;
    }

    public void iterate() {
        User player = userFactory.getUser();
        List<Wager> wagers = new ArrayList<>();
        List<SportEvent> sportEvents = sportEventGenerator.generateSportsEvent();
        List<Bet> bets = betService.createBets(sportEvents);
        try {
            while (true) {
                wagers.add(composeWager(player, bets));
            }
        } catch (StopProcessingException e) {
            System.out.println("All bets are received");
        }
        List<Outcome> winOutcomes = gameSimulation(bets);
        markWinners(wagers, winOutcomes);
        resultComputing(wagers);
        Map<User, List<Wager>> collect = wagers.stream().collect(Collectors.groupingBy(Wager::getPlayer));
        collect.keySet().stream().map(User::getBalance).forEach(System.out::println);
        System.out.println("You new balance is: " + player.getBalance());
    }

    private void markWinners(List<Wager> wagers, List<Outcome> winOutcomes) {
        for (int i = 0; i < wagers.size() - 1; i++) {
            OutcomeOdd wagerOutcomeOdd = wagers.get(i).getOutcomeOdd();
            for (Outcome outcome : winOutcomes) {
                if (outcome.getOutcomeOdds().contains(wagerOutcomeOdd)) {
                    wagers.get(i).setWagerState(WagerState.WIN);
                } /*else {
//                    wagers.get(i).setWagerState(WagerState.LOSE);
                }*/
            }
        }
    }

    private void resultComputing(List<Wager> wagers) {
        wagers.stream()
                .filter(wager -> WagerState.WIN.equals(wager.getWagerState()))
                .forEach(this::calculateWinAmount);
    }

    private void calculateWinAmount(Wager wager) {
        wager.getPlayer().setBalance(wager.getAmount().multiply(wager.getOutcomeOdd().getOddValue()));

    }

    private List<Outcome> gameSimulation(List<Bet> bets) {
        List<Outcome> winner = new ArrayList<>();
        for (Bet bet : bets) {
            switch (bet.getBetsType()) {
                case WinnerBet:
                    winner.add(winnerSimulation(bet));
                    break;
                case GoalsBet:
                case ScoreBet:
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


    public Wager composeWager(User player, List<Bet> bets) throws StopProcessingException {
        DisplayedBet bet = betService.getChosenBet(bets);
        BigDecimal betAmount = currentBetAmount();
        boolean isEnoughBalance = userService.isEnoughBalance(player, betAmount);
        while (!isEnoughBalance) {
            System.out.println("You don't have enough money");
            userService.showCurrentBalance(player);
            betAmount = currentBetAmount();
            isEnoughBalance = userService.isEnoughBalance(player, betAmount);
        }
        return receiveBet(player, bet, betAmount);
    }

    private Wager receiveBet(User player, DisplayedBet bet, BigDecimal betAmount) {
        // TODO check if it is legal)
        Wager wager = context.getBean(Wager.class);
        wager.setPlayer(player);
        wager.setAmount(betAmount);
        wager.setCurrency(player.getCurrency());
        wager.setOutcomeOdd(bet.getOutcomeOdd());
        wager.setCreatedTime(LocalDateTime.now());
        userService.changeBalance(wager);
        userService.showCurrentBalance(player);
        System.out.println(wager.hashCode());
        return wager;
    }

    public BigDecimal currentBetAmount() {
        return betIO.getInputAmount();
    }
}
