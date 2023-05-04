package com.smu.investimentos.auth.account.config;

import com.smu.investimentos.auth.account.domain.model.Account;
import com.smu.investimentos.auth.account.domain.model.UserEntity;
import com.smu.investimentos.auth.account.domain.repository.AccountRepository;
import com.smu.investimentos.auth.account.domain.repository.UserRepository;
import com.smu.investimentos.auth.account.domain.utils.GenerateNumbers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class FirstUserConfig implements ApplicationRunner {
	
	private final Logger logger = LoggerFactory.getLogger(FirstUserConfig.class);
	private final UserRepository userRepository;
	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;

	public FirstUserConfig(UserRepository userRepository, PasswordEncoder passwordEncoder,
						   AccountRepository accountRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.accountRepository = accountRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (userRepository.count() != 0) {
			return;
		}
		
		logger.info("Nenhum usuário encontrado, cadastrando usuário admin padrão.");

		UserEntity userEntity = userRepository.save(
				new UserEntity(
						null,
						"Genildo Araujo",
						"admin@email.com",
						"29586841804",
						passwordEncoder.encode("123456"),
						UserEntity.Type.ADMIN,
						null,
						OffsetDateTime.now()
				)
		);

		Account account = accountRepository.save(new Account(1L,userEntity, GenerateNumbers.generateNumberAccount(1),
				                 "001", 0.00, Account.StatusAccount.OPEN, List.of(), List.of(), List.of(), OffsetDateTime.now()));
		userEntity.setAccount(account);
		userRepository.save(userEntity);

	}
}
