package com.rivanmota.model;

import com.rivanmota.model.enums.TipoLog;

public class Log {
	
	private String descricao;
	private String ip;
	private String navegador;
	private Integer idUsuario;
	private TipoLog tipoLog;
	
	public Log() {

	}
	
	public Log(Integer idUsuario, TipoLog tipoLog) {
		super();
		this.idUsuario = idUsuario;
		this.tipoLog = tipoLog;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNavegador() {
		return navegador;
	}

	public void setNavegador(String navegador) {
		this.navegador = navegador;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public TipoLog getTipoLog() {
		return tipoLog;
	}

	public void setTipoLog(TipoLog tipoLog) {
		this.tipoLog = tipoLog;
	}

}
