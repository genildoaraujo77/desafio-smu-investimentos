package com.smu.investimentos.auth.account.api.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public UsuarioNaoEncontradoException(Long cozinhaId) {
		this(String.format("Não existe um cadastro de usuário com código %d", cozinhaId));
	}
	
}
