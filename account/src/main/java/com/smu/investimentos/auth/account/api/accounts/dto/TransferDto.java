package com.smu.investimentos.auth.account.api.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferDto implements Serializable {

    private Long id;

    private Double amountTransferred;

    private OffsetDateTime createdAt;

}
