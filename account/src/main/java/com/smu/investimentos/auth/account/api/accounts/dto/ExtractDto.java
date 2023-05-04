package com.smu.investimentos.auth.account.api.accounts.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExtractDto implements Serializable {
    private AccountDto account;
    private List<DepositDto> deposits = new ArrayList<>();
    private List<DrawDto> draws = new ArrayList<>();
    private List<TransferDto> transfers = new ArrayList<>();
}
