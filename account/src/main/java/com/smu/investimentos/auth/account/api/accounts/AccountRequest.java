package com.smu.investimentos.auth.account.api.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    private String numberAccount;
    @NotBlank(message = "Agência é obrigatório")
    private String agency;
}
