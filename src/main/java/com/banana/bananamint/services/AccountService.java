package com.banana.bananamint.services;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.exception.AccountException;

import java.util.List;

public interface AccountService {
    public List<Account> showAll(Long idCustomer) throws AccountException;

    public Account open(Long idCustomer, Account account) throws AccountException;
}
