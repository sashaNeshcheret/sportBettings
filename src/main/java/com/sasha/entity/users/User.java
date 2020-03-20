package com.sasha.entity.users;

import com.sasha.util.Currency;

import java.math.BigDecimal;

public interface User {

    String getName();

    void setName(String name);

    BigDecimal getBalance();

    void setBalance(BigDecimal balance);

    Currency getCurrency();
}
