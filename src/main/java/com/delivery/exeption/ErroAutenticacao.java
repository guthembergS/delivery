package com.delivery.exeption;

public class ErroAutenticacao extends RuntimeException{
	
	public ErroAutenticacao(String msg) {
		super(msg);
	}
}
