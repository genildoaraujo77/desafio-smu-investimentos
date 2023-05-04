package com.smu.investimentos.auth.account.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smu.investimentos.auth.account.api.users.UserRequest;
import com.smu.investimentos.auth.account.utils.AbstractReader;
import com.smu.investimentos.auth.account.api.services.AccountService;
import com.smu.investimentos.auth.account.api.services.UserService;
import com.smu.investimentos.auth.account.api.users.UserResponse;
import com.smu.investimentos.auth.account.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class UserServiceTest {
    @Mock
    private UserService userService;

    @Mock
    private UserRepository repository;

    @Mock
    private AccountService serviceAccount;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        repository.deleteAll();
    }

    @Test
    void deveriaSalvarInvestidorECriarConta() {

        UserRequest investorDtoInsert = null;
        UserResponse investorDtoResult = null;
        try {
            investorDtoInsert = retornaJson("investorDtoInsert.json");
            investorDtoResult = retornaJsonResult("investorDtoResult.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Mockito.when(userService.createUserAndAccount(investorDtoInsert)).thenReturn(investorDtoResult);

        UserResponse userResponse = userService.createUserAndAccount(investorDtoInsert);
        assertEquals(1L, userResponse.getId());
        assertEquals("Genildo Araujo", userResponse.getName());
        assertEquals("000001", userResponse.getAccount().getNumber());

        verify(userService).createUserAndAccount(investorDtoInsert);

    }

    private UserRequest retornaJson(String resourceName) throws IOException {
            String jsonText = AbstractReader.readJson(resourceName);
            Type collectionType = new TypeToken<UserRequest>(){}.getType();
            return new Gson().fromJson(jsonText,collectionType);
    }
    private UserResponse retornaJsonResult(String resourceName) throws IOException {
            String jsonText = AbstractReader.readJson(resourceName);
            Type collectionType = new TypeToken<UserResponse>(){}.getType();
            return new Gson().fromJson(jsonText,collectionType);
    }

}
