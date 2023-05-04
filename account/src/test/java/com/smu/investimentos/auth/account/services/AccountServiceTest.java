package com.smu.investimentos.auth.account.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smu.investimentos.auth.account.api.accounts.dto.AccountDto;
import com.smu.investimentos.auth.account.api.services.AccountService;
import com.smu.investimentos.auth.account.domain.model.Account;
import com.smu.investimentos.auth.account.domain.repository.*;
import com.smu.investimentos.auth.account.utils.AbstractReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

public class AccountServiceTest {
    @Mock
    private AccountRepository repository;

    @Mock
    private AccountService accountService;

    @Mock
    UserRepository userRepository;

    @Mock
    private DepositRepository depositRepository;

    @Mock
    private DrawRepository drawRepository;

    @Mock
    private TransferRepository transferRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void deveriaRealizarDepositoNaConta() {

        AccountDto accountDtoResult = null;
        try {
            accountDtoResult = retornaJson("depositAccountResult.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Mockito.when(accountService.deposit(100.00, "000001")).thenReturn(accountDtoResult);

        AccountDto accountDto = accountService.deposit(100.00, "000001");
        assertEquals("000001", accountDto.getNumber());
        assertEquals(100.0, accountDto.getBalance());

        verify(accountService).deposit(100.00, "000001");

    }

    @Test
    void deveriaRealizarSaqueNaConta() {

        accountService.deposit(100.00, "000001");

        Mockito.when(accountService.draw(89.00, "000001"))
                .thenReturn(new AccountDto("000001", "001", 11.0, Account.StatusAccount.OPEN));

        AccountDto accountDto = accountService.draw(89.00, "000001");
        assertEquals("000001", accountDto.getNumber());
        assertEquals(11.0, accountDto.getBalance());

        verify(accountService).draw(89.00, "000001");

    }

    @Test
    void deveriaRealizartransferenciaParaOutraConta() {

        accountService.deposit(100.00, "000001");

        Mockito.when(accountService.transfer(60.00, "000001", "000002"))
                .thenReturn(new AccountDto("000001", "001", 40.0, Account.StatusAccount.OPEN));

        AccountDto accountDto = accountService.transfer(60.00, "000001", "000002");
        assertEquals("000001", accountDto.getNumber());
        assertEquals(40.0, accountDto.getBalance());

        verify(accountService).transfer(60.00, "000001", "000002");

    }

    @Test
    void naoDeveriaRealizartransferenciaParaPropriaConta() {

        accountService.deposit(100.00, "000001");

        Mockito.when(accountService.transfer(60.00, "000001", "000001"))
                .thenThrow(EntityNotFoundException.class);

                assertThrows(EntityNotFoundException.class, () ->
                accountService.transfer(60.00, "000001", "000001"));

        verify(accountService).transfer(60.00, "000001", "000001");

    }

    private AccountDto retornaJson(String resourceName) throws IOException {
            String jsonText = AbstractReader.readJson(resourceName);
            Type collectionType = new TypeToken<AccountDto>(){}.getType();
            return new Gson().fromJson(jsonText,collectionType);
    }

}
