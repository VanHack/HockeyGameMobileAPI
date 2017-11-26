package com.rivanmota.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.rivanmota.utils.ObjectUtils;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="autenticacao")
public class Autenticacao {
	
	@XmlElement(required=true)
	private String usuario;
	
	@XmlElement(required=true)
	private String senha;
	
	public Autenticacao() {
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean isValida() {
		return ObjectUtils.isNotNullAndNotEmpty(usuario) && ObjectUtils.isNotNullAndNotEmpty(senha);
	}
	
	@Override
	public String toString() {
		return usuario + " | " + senha;
	}
	
}
