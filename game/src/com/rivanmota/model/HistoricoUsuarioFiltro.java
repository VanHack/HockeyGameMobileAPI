package com.rivanmota.model;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.rivanmota.utils.ObjectUtils;

@XmlRootElement(name="historico-usuario-filtro")
@XmlAccessorType(XmlAccessType.FIELD)
public class HistoricoUsuarioFiltro {

	@XmlElement
	private Integer id;
	
	@XmlElement
	private Integer idUsuario;
	
	@XmlElement
	private Integer idCliente;
	
	@XmlElement
	private Integer idStatus;
	
	@XmlElement
	private Calendar data;
	
	@XmlElement
	private Integer pontos;
	
	@XmlElement
	private Integer limit;
	
	@XmlElement
	private Integer offset;
	
	@XmlElement(required=true)
	private String type;
	
	@XmlElement(required=true)
	private Autenticacao autenticacao;
	
	public HistoricoUsuarioFiltro() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getType() {
		if (ObjectUtils.isNullOrEmpty(type)) {
			return "xml";
		}
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
