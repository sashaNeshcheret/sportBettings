package com.sasha.entity.users;

import com.sasha.util.Currency;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Player implements User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column(name = "account_number")
    private long accountNumber;
    @Column
    private BigDecimal balance;
    @Column
    private Currency currency;
    @Column(name = "birth_date")
    private LocalDate dateOfBirth;

    public static Builder builder() {
        return new Player().new Builder();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", currency=" + currency +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    public class Builder {

        private Builder() {
        }

        public Builder setName(String userName) {
            Player.this.name = userName;

            return this;
        }

        public Builder setAccountNumber(long accountNumber) {
            Player.this.accountNumber = accountNumber;

            return this;
        }

        public Builder setBalance(BigDecimal balance) {
            Player.this.balance = balance;

            return this;
        }

        public Builder setCurrency(Currency currency) {
            Player.this.currency = currency;

            return this;
        }

        public Builder setDateOfBirth(LocalDate dateOfBirth) {
            Player.this.dateOfBirth = dateOfBirth;

            return this;
        }

        public Player build() {
            return Player.this;
        }
    }
}
