package com.smu.investimentos.auth.account.api.users;

import com.smu.investimentos.auth.account.domain.model.UserEntity;

import javax.validation.constraints.NotBlank;

public class UserUpdateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private UserEntity.Type type;


    public void update(UserEntity currentUser) {
        currentUser.setEmail(this.email);
        currentUser.setName(this.name);
        currentUser.setType(this.type);
    }
}
