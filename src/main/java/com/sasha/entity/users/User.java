package com.sasha.entity.users;

import com.sasha.util.Currency;

import java.math.BigDecimal;

public interface User {
    BigDecimal getBalance();

    void setBalance(BigDecimal balance);

    Currency getCurrency();
}
