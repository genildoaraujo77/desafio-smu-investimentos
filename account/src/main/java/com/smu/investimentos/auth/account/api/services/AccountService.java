package com.smu.investimentos.auth.account.api.services;

import com.smu.investimentos.auth.account.api.accounts.dto.*;
import com.smu.investimentos.auth.account.domain.model.*;
import com.smu.investimentos.auth.account.domain.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private DrawRepository drawRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //    Depósito
    public AccountDto deposit(double value, String numeroAccount){
        Account account = repository.findByNumberAccount(numeroAccount);

        if (depositIsValid(value, account)){
            Deposit newDeposit = new Deposit(null, value, OffsetDateTime.now(), account);
//            Deposit deposit =
                    depositRepository.save(newDeposit);
//            account.deposit(deposit.getValueDeposited());
            repository.save(account);
        } else {
            throw new EntityNotFoundException();
        }

        return modelMapper.map(account, AccountDto.class);
    }

    private static boolean depositIsValid(double value, Account account) {
        return account != null && account.deposit(value);
    }

    //    Saque
    public AccountDto draw(double value, String numberAccount){
        Account account = repository.findByNumberAccount(numberAccount);

        if (drawIsValid(value, account)){
            Withdrawal newWithdrawal = new Withdrawal(null, value, OffsetDateTime.now(), account);
//            Withdrawal withdrawal =
                    drawRepository.save(newWithdrawal);
//            account.draw(withdrawal.getWithdrawnAmount());
            repository.save(account);
        } else {
            throw new EntityNotFoundException();
        }

        return modelMapper.map(account, AccountDto.class);
    }

    private static boolean drawIsValid(double value, Account account) {
        return account != null && account.draw(value);
    }

    //    Transferência
    public AccountDto transfer(double value, String numeroAccount, String numeroAccountTerceiro){
        Account account = repository.findByNumberAccount(numeroAccount);
        Account accountTerceiro = repository.findByNumberAccount(numeroAccountTerceiro);

        if (isValid(value, account, accountTerceiro)){
            this.deposit(value, accountTerceiro.getNumberAccount());
            Transfer newTransfer = new Transfer(null, value, OffsetDateTime.now(), account);
//            Transfer transfer =
              transferRepository.save(newTransfer);
//            account.setTransfer(transfer);

            List<Account> accounts = new ArrayList<>();
            accounts.add(account);
            accounts.add(accountTerceiro);

            repository.saveAll(accounts);

        } else {
            throw new EntityNotFoundException();
        }

        return modelMapper.map(account, AccountDto.class);
    }

    private static boolean isValid(double value, Account account, Account accountTerceiro) {
        return (account != null || accountTerceiro != null) && Objects.requireNonNull(account).draw(value) && account != accountTerceiro;
    }

    //    extrato
    public ExtractDto extract(String numberAccount){
        Account account = repository.findByNumberAccount(numberAccount);

        if (account == null) {
            throw new EntityNotFoundException();
        }

        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        ExtractDto extractDto = new ExtractDto();
        extractDto .setAccount(accountDto);
        extractDto.setDeposits(getDepositsByAccount(account));
        extractDto.setDraws(getDrawsByAccount(account));
        extractDto.setTransfers(getTransfersByAccount(account));

        return extractDto;
    }

    private List<TransferDto> getTransfersByAccount(Account account) {
        List<Transfer> transfer = transferRepository.findByAccount(account);
        return transfer.stream().map(s -> modelMapper.map(s, TransferDto.class))
                .collect(Collectors.toList());
    }

    private List<DrawDto> getDrawsByAccount(Account account) {
        List<Withdrawal> withdrawals = drawRepository.findByAccount(account);
        return withdrawals.stream().map(w -> modelMapper.map(w, DrawDto.class))
                .collect(Collectors.toList());
    }

    public List<DepositDto> getDepositsByAccount(Account account) {
        List<Deposit> deposits = depositRepository.findByAccount(account);
        return deposits.stream().map(d -> modelMapper.map(d, DepositDto.class))
                .collect(Collectors.toList());
    }

    public AccountDto block(String numberAccount) {
        Account account = repository.findByNumberAccount(numberAccount);
        account.setStatusAccount(Account.StatusAccount.BLOCKED);
        account.getUserEntity().setType(UserEntity.Type.BLOCKED);
        repository.save(account);
        return modelMapper.map(account, AccountDto.class);
    }
}
