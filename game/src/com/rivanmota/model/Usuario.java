package com.rivanmota.model;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.rivanmota.model.enums.StatusUsuario;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="usuario")
public class Usuario {

	@XmlElement
	private Integer id;
	
	@XmlElement
	private String nome;
	
	@XmlElement
	private String documento;
	
	@XmlElement
	private String emailCorporativo;
	
	@XmlElement
	private String celularCorporativo;
	
	@XmlElement
	private String senha;

	@XmlElement
	private Calendar dataNascimento;
	
	@XmlElement
	private Calendar dataCadastro;
	
	@XmlElement
	private Calendar dataUpdate;
	
	@XmlElement
	private Genero genero;
	
	@XmlElement
	private boolean concorda;
	
	@XmlElement
	private Usuario diretorRegionalVendas;
	
	@XmlElement
	private StatusUsuario statusUsuario;
	
	@XmlElement
	private String perfil;
	
	@XmlElement(required=true)
	private String type;
		
	@XmlElement
	private Autenticacao autenticacao;
	
	public enum Genero {
		M,
		F;
	}
	
	public Usuario() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEmailCorporativo() {
		return emailCorporativo;
	}

	public void setEmailCorporativo(String emailCorporativo) {
		this.emailCorporativo = emailCorporativo;
	}

	public String getCelularCorporativo() {
		return celularCorporativo;
	}

	public void setCelularCorporativo(String celularCorporativo) {
		this.celularCorporativo = celularCorporativo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Calendar getDataUpdate() {
		return dataUpdate;
	}

	public void setDataUpdate(Calendar dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public boolean isConcorda() {
		return concorda;
	}

	public void setConcorda(boolean concorda) {
		this.concorda = concorda;
	}

	public Usuario getDiretorRegionalVendas() {
		return diretorRegionalVendas;
	}

	public void setDiretorRegionalVendas(Usuario diretorRegionalVendas) {
		this.diretorRegionalVendas = diretorRegionalVendas;
	}

	public StatusUsuario getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(StatusUsuario statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Autenticacao getAutenticacao() {
		return autenticacao;
	}

	public void setAutenticacao(Autenticacao autenticacao) {
		this.autenticacao = autenticacao;
	}
	

}