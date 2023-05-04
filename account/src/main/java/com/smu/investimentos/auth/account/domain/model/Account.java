package com.smu.investimentos.auth.account.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	@Id
	@GeneratedValue
	private Long id;
	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
	@JoinColumn(name = "user_entity_id", referencedColumnName = "id")
	private UserEntity userEntity;
	private String numberAccount;
	@NotNull(message = "Agência é obrigatório")
	private String agency;
	private double balance;
	@Enumerated(EnumType.STRING)
	private StatusAccount statusAccount;

	@OneToMany(targetEntity = Withdrawal.class,mappedBy="account" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Withdrawal>  withdrawals = new ArrayList<>();

	@OneToMany(targetEntity = Transfer.class,mappedBy="account" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Transfer> transfers = new ArrayList<>();

	@OneToMany(targetEntity = Deposit.class,mappedBy="account" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Deposit> deposits;

	@CreatedDate
	private OffsetDateTime createdAt;

	public boolean deposit(double value) {
		balance = balance + value;
		return true;
	}

	public boolean draw(double value){
		if ((balance - value) >= 0){
			balance -= value;
			return true;
		}
		return false;
	}

	public enum StatusAccount {
		OPEN, ACTIVE, BLOCKED;
	}
}
