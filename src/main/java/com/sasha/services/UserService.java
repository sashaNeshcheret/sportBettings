package com.sasha.services;

import com.sasha.entity.users.User;
import com.sasha.entity.wagers.Wager;

import java.math.BigDecimal;

public class UserService {

    public boolean isEnoughBalance(User player, BigDecimal amount){
        return player.getBalance().compareTo(amount) >= 0;
    }

    public void changeBalance(Wager wager) {
        User player = wager.getPlayer();
        BigDecimal result = player.getBalance().subtract(wager.getAmount());
        player.setBalance(result);
    }

    public void showCurrentBalance(User player) {
        System.out.println("Your balance is " + player.getBalance() + " " + player.getCurrency());
    }
}
