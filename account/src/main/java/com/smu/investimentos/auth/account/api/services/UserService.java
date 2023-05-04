package com.smu.investimentos.auth.account.api.services;

import com.smu.investimentos.auth.account.api.ResourceNotFoundException;
import com.smu.investimentos.auth.account.api.users.UserRequest;
import com.smu.investimentos.auth.account.api.users.UserResponse;
import com.smu.investimentos.auth.account.api.users.UserUpdateRequest;
import com.smu.investimentos.auth.account.domain.model.Account;
import com.smu.investimentos.auth.account.domain.model.UserEntity;
import com.smu.investimentos.auth.account.domain.repository.AccountRepository;
import com.smu.investimentos.auth.account.domain.repository.UserRepository;
import com.smu.investimentos.auth.account.domain.utils.GenerateNumbers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<UserResponse> findAll(Pageable pageable) {
        final Page<UserEntity> userEntityPage = userRepository.findAll(pageable);
        final var userResponses = userEntityPage.getContent()
                .stream()
                .map((user) -> UserResponse.of(user, modelMapper))
                .collect(Collectors.toList());
        return new PageImpl<>(userResponses, pageable, userEntityPage.getTotalElements());
    }

    public UserResponse findById(Long id) {
        return UserResponse.of(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado.")),
                modelMapper);
    }

    public UserResponse update(Long id,
                               UserUpdateRequest request) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
        request.update(user);
        userRepository.save(user);
        return UserResponse.of(user, modelMapper);
    }

    public UserResponse createUserAndAccount(UserRequest userRequest) {
        final var userEntity = modelMapper.map(userRequest, UserEntity.class);
        var password = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(password);
        if (userEntity.getCpfCnpj().length() > 11) {
            userEntity.setType(UserEntity.Type.PJ);
        } else {
            userEntity.setType(UserEntity.Type.PF);
        }
        Account account = new Account();
        account.setAgency("001");
        Integer qtdAccounts = Math.toIntExact(accountRepository.count());
        qtdAccounts += 1;
        account.setNumberAccount(GenerateNumbers.generateNumberAccount((qtdAccounts)));
        var dateTime = OffsetDateTime.now();
        account.setCreatedAt(dateTime);
        userEntity.setCreatedAt(dateTime);
        account.setStatusAccount(Account.StatusAccount.OPEN);
        account.setBalance(0.00);
        account.setUserEntity(userEntity);
        Account accountSaved = accountRepository.save(account);
        userEntity.setAccount(accountSaved);
        userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserResponse.class);
    }
}
