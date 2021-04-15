package com.easyday.cyrela.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.easyday.cyrela.domain.enums.Bandeira;

@Entity
public class Ocorrencia implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ticketnumber")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer numeroOcorrencia;
	
	@ManyToOne
	@JoinColumn(name="cliente_id", nullable=false)
	private Cliente clienteDaUnidade;
	
	@Column(name="pjo_empreendimentoid")
	private Integer empreendimento;

	@Column(name="pjo_blocoid")
	private Integer bloco;
	
	@Column(name="pjo_unidadeid")
	private Integer unidade;
	
	@Column(name="pjo_bandeira")
	private Integer bandeira;
	
	@Column(name="description")
	private String descricao;
	
	public Ocorrencia() {}

	public Ocorrencia(Integer numeroOcorrencia, Cliente clienteDaUnidade, Integer empreendimento, Integer bloco,
			Integer unidade, Bandeira bandeira, String descricao) {
		super();
		this.numeroOcorrencia = numeroOcorrencia;
		this.clienteDaUnidade = clienteDaUnidade;
		this.empreendimento = empreendimento;
		this.bloco = bloco;
		this.unidade = unidade;
		this.bandeira = (bandeira == null) ? null : bandeira.getCod();
		this.descricao = descricao;
	}

	public Integer getNumeroOcorrencia() {
		return numeroOcorrencia;
	}

	public void setNumeroOcorrencia(Integer numeroOcorrencia) {
		this.numeroOcorrencia = numeroOcorrencia;
	}

	public Cliente getClienteDaUnidade() {
		return clienteDaUnidade;
	}

	public void setClienteDaUnidade(Cliente clienteDaUnidade) {
		this.clienteDaUnidade = clienteDaUnidade;
	}

	public Integer getEmpreendimento() {
		return empreendimento;
	}

	public void setEmpreendimento(Integer empreendimento) {
		this.empreendimento = empreendimento;
	}

	public Integer getBloco() {
		return bloco;
	}

	public void setBloco(Integer bloco) {
		this.bloco = bloco;
	}

	public Integer getUnidade() {
		return unidade;
	}

	public void setUnidade(Integer unidade) {
		this.unidade = unidade;
	}

	public Bandeira getBandeira() {
		return Bandeira.toEnum(bandeira);
	}

	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira.getCod();
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroOcorrencia == null) ? 0 : numeroOcorrencia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ocorrencia other = (Ocorrencia) obj;
		if (numeroOcorrencia == null) {
			if (other.numeroOcorrencia != null)
				return false;
		} else if (!numeroOcorrencia.equals(other.numeroOcorrencia))
			return false;
		return true;
	}
}
