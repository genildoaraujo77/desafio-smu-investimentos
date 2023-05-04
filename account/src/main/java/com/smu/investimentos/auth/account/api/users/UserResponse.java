package com.smu.investimentos.auth.account.api.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.smu.investimentos.auth.account.api.accounts.dto.AccountDto;
import com.smu.investimentos.auth.account.domain.model.Account;
import com.smu.investimentos.auth.account.domain.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse implements Serializable {
    private Long id;
    private String email;
    private String name;
    private UserEntity.Type type;
    private AccountDto account;

    public static UserResponse of(UserEntity user, ModelMapper modelMapper) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getType(),
                modelMapper.map(user.getAccount(), AccountDto.class)
        );
    }
}
