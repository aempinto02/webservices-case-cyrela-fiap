package com.easyday.cyrela.domain.enums;

public enum TipoAtividade {
	
	VISTORIA(1, "Vistoria"),
	PERICIA(2, "Perícia"),
	ENCERRAMENTO(3, "Encerramento");
	
	private int cod;
	private String descricao;
	
	private TipoAtividade(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoAtividade toEnum(Integer cod) {
		if(cod == null) return null;
		
		for(TipoAtividade x : TipoAtividade.values()) {
			if(cod.equals(x.getCod())) return x;
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}