package com.easyday.cyrela.domain.enums;

public enum Bandeira {
	
	CYRELA(1, "Cyrela"),
	LIVING(2, "Living"),
	VIVAZ(3, "Vivaz");
	
	private int cod;
	private String descricao;
	
	private Bandeira(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Bandeira toEnum(Integer cod) {
		if(cod == null) return null;
		
		for(Bandeira x : Bandeira.values()) {
			if(cod.equals(x.getCod())) return x;
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}