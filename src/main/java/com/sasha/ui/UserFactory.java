package com.sasha.ui;

import com.sasha.entity.users.Player;
import com.sasha.entity.users.User;
import com.sasha.util.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;


public class UserFactory {
    private Map<String, User> userMap;
    private InputReader reader;
    public UserFactory(Map<String, User> userMap, InputReader reader) {
        this.userMap = userMap;
        this.reader = reader;
    }

    private User createUser(String userName) {
        System.out.println("What is your account number?");
        long accountNumber = Long.valueOf(reader.getNextLine());
        System.out.println("How much money do you have (more than 0)");
        BigDecimal balance = new BigDecimal(reader.getNextLine());
        System.out.println("What is your currency? (UAH, EUR or USD)");
        Currency currency = Currency.valueOf(reader.getNextLine().toUpperCase());
        System.out.println("When were you born? eg.:1990-02-20");
        LocalDate dateOfBirth = LocalDate.parse(reader.getNextLine());
        Player player = Player.builder()
                .setName(userName)
                .setAccountNumber(accountNumber)
                .setBalance(balance)
                .setCurrency(currency)
                .setDateOfBirth(dateOfBirth)
                .build();
        userMap.put(userName, player);
        System.out.println("Welcome " + userName + "!\n" +
                "Your balance is " + player.getBalance() + " " + player.getCurrency() + "\n");
        return player;
    }

    public User getUser() {
        System.out.println("Hi, what is your name?");
        String username = reader.getNextLine();
        if (userMap.containsKey(username)) {
            System.out.println("Hello, " + username + "nice to meet you again");
            return userMap.get(username);
        }

        return createUser(username);
    }
}
