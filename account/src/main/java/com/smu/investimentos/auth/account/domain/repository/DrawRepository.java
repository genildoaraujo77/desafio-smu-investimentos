package com.smu.investimentos.auth.account.domain.repository;

import com.smu.investimentos.auth.account.domain.model.Account;
import com.smu.investimentos.auth.account.domain.model.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrawRepository extends JpaRepository<Withdrawal, Long> {
    List<Withdrawal> findByAccount(Account account);
}
