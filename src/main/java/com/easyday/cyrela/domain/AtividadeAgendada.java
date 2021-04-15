package com.easyday.cyrela.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import com.easyday.cyrela.domain.enums.TipoAtividade;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class AtividadeAgendada implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="actualstart")
	@Future
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataInicio;

	@Column(name="actualend")
	@Future
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataTermino;
	
	@Column(name="pjo_tipodeatividade")
	private Integer tipoAtividade;
	
	@Column(name="subject")
	@Size(min=3, max=120, message="O assunto precisa ter no mínimo 3 caracteres e no máximo 120!")
	private String assunto;
	
	@Column(name="pjo_empreendimentoid")
	private Integer empreendimento;
	
	@Column(name="pjo_blocoid")
	private Integer bloco;
	
	@Column(name="pjo_unidadeid")
	private Integer unidade;
	
	public AtividadeAgendada() {}
	
	public AtividadeAgendada(Integer id, Date dataInicio, Date dataTermino, TipoAtividade tipoAtividade, String assunto,
			Integer empreendimento, Integer bloco, Integer unidade) {
		super();
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.tipoAtividade = (tipoAtividade == null) ? null : tipoAtividade.getCod();
		this.assunto = assunto;
		this.empreendimento = empreendimento;
		this.bloco = bloco;
		this.unidade = unidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public TipoAtividade getTipoAtividade() {
		return TipoAtividade.toEnum(tipoAtividade);
	}

	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade.getCod();
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AtividadeAgendada other = (AtividadeAgendada) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}