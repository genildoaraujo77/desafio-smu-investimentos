package com.smu.investimentos.auth.account.api.users;

import com.smu.investimentos.auth.account.domain.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String cpfCnpj;
    @NotBlank
    private String password;
    @NotNull
    private UserEntity.Type type;

}
