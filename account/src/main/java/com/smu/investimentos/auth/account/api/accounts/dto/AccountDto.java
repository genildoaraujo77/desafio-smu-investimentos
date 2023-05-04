package com.smu.investimentos.auth.account.api.accounts.dto;

import com.smu.investimentos.auth.account.domain.model.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto implements Serializable {
    private String number;
    private String agency;
    private double balance;
    private Account.StatusAccount status;

}
