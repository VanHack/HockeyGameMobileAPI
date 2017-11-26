package com.rivanmota.model;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="quiz")
public class Quiz {

	@XmlElement
	private Integer id;
	
	@XmlElement
	private String descricao;
	
	@XmlElement
	private String opcaoA;
	
	@XmlElement
	private String opcaoB;
	
	@XmlElement
	private String opcaoC;
	
	@XmlElement
	private String opcaoCorreta;
	
	@XmlElement
	private Calendar dataCadastro;
	
	@XmlElement
	private Calendar dataValidade;
	
	public Quiz() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getOpcaoA() {
		return opcaoA;
	}

	public void setOpcaoA(String opcaoA) {
		this.opcaoA = opcaoA;
	}

	public String getOpcaoB() {
		return opcaoB;
	}

	public void setOpcaoB(String opcaoB) {
		this.opcaoB = opcaoB;
	}

	public String getOpcaoC() {
		return opcaoC;
	}

	public void setOpcaoC(String opcaoC) {
		this.opcaoC = opcaoC;
	}

	public String getOpcaoCorreta() {
		return opcaoCorreta;
	}

	public void setOpcaoCorreta(String opcaoCorreta) {
		this.opcaoCorreta = opcaoCorreta;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Calendar getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Calendar dataValidade) {
		this.dataValidade = dataValidade;
	}

}
