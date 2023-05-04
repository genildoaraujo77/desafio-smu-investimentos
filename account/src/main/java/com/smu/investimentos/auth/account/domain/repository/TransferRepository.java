package com.smu.investimentos.auth.account.domain.repository;

import com.smu.investimentos.auth.account.domain.model.Account;
import com.smu.investimentos.auth.account.domain.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findByAccount(Account account);
}
