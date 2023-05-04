package com.smu.investimentos.auth.account.domain.repository;

import com.smu.investimentos.auth.account.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByNumberAccount(String numberAccount);
}
