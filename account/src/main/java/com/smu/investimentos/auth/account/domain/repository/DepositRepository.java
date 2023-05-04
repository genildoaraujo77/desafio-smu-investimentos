package com.smu.investimentos.auth.account.domain.repository;

import com.smu.investimentos.auth.account.domain.model.Account;
import com.smu.investimentos.auth.account.domain.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> findByAccount(Account account);
}
