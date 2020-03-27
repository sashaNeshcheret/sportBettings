package com.sasha.dataAccess;

import com.sasha.entity.bets.Bet;

public interface BetRepository<T extends Bet> extends Repository<T> {
}
