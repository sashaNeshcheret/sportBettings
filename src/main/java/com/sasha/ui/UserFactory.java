package com.sasha.ui;

import com.sasha.dataAccess.UserRepository;
import com.sasha.dataAccess.UserRepositoryImpl;
import com.sasha.entity.users.Player;
import com.sasha.entity.users.User;
import com.sasha.util.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class UserFactory {
    private Map<String, User> userMap;
    private UserRepository<User> userRepository;
    private InputReader reader;
    public UserFactory(UserRepository<User> userRepository, InputReader reader) {
        this.userRepository = userRepository;
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
        userRepository.create(player);
        User savedUser = userRepository.findById(userName);
        System.out.println("Welcome " + savedUser.getName() + "!\n" +
                "Your balance is " + savedUser.getBalance() + " " + savedUser.getCurrency() + "\n");
        return player;
    }

    public User getUser() {
        System.out.println("Hi, what is your name?");
        String userName = reader.getNextLine();
        User user = null; //userRepository.findById(userName);
        if(null == user){
            user = createUser(userName);
        }
        return user;
    }
}
