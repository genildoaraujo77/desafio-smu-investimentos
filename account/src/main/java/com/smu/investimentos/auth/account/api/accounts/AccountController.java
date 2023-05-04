package com.smu.investimentos.auth.account.api.accounts;


import com.smu.investimentos.auth.account.api.accounts.dto.AccountDto;
import com.smu.investimentos.auth.account.api.accounts.dto.ExtractDto;
import com.smu.investimentos.auth.account.api.services.AccountService;
import com.smu.investimentos.auth.account.security.CanBlockAccounts;
import com.smu.investimentos.auth.account.security.CanReadAccounts;
import com.smu.investimentos.auth.account.security.CanWriteAccounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping(value = "/{value}/{numberAccount}")
	@CanWriteAccounts
	public AccountDto deposit(@PathVariable double value, @PathVariable String numberAccount){
		return accountService.deposit(value, numberAccount);
	}

	@PostMapping(value = "/draw/{value}/{numberAccount}")
	@CanWriteAccounts
	public AccountDto draw(@PathVariable double value, @PathVariable String numberAccount){
		return accountService.draw(value, numberAccount);
	}

	@PostMapping(value = "/{numberAccount}/transfer/{value}/{numberAccountThird}")
	@CanWriteAccounts
	public AccountDto transfer(@PathVariable double value, @PathVariable String numberAccount, @PathVariable String numberAccountThird){
		return accountService.transfer(value, numberAccount, numberAccountThird);
	}

	@GetMapping(value = "/extract/{numberAccount}")
	@CanReadAccounts
	public ExtractDto extract(@PathVariable String numberAccount){
		return accountService.extract(numberAccount);
	}

	@PostMapping(value = "/block-account/{numberAccount}")
	@CanBlockAccounts
	public AccountDto blockAccount(@PathVariable String numberAccount){
		return accountService.block(numberAccount);
	}

}
