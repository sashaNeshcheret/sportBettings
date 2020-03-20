package com.sasha.services;

import com.sasha.dataAccess.UserRepository;
import com.sasha.entity.users.User;

import java.math.BigDecimal;

public class UserService {
    private UserRepository userRepository;

    public boolean isEnoughBalance(User player, BigDecimal amount) {
        return player.getBalance().compareTo(amount) >= 0;
    }

    public void changeBalance(User user, BigDecimal amount) {
        BigDecimal result = user.getBalance().subtract(amount);
        user.setBalance(result);
    }

    public void showCurrentBalance(User player) {
        System.out.println("Your balance is " + player.getBalance() + " " + player.getCurrency());
    }
}
