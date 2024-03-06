package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountJPARepository {
    public List<Account> findAll(Long idCustomer) throws SQLException;

    public Account save(Account account) throws SQLException;
}
