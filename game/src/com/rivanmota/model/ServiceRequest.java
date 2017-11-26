package com.rivanmota.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.rivanmota.utils.ObjectUtils;

@XmlRootElement(name="service-request")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceRequest {

	@XmlElement
	private String documento;
	
	@XmlElement
	private String[] opcaoEscolhida;
	
	@XmlElement
	private Integer idUsuario;
	
	@XmlElement
	private Integer idCliente;
	
	@XmlElement
	private Integer idQuiz;
	
	@XmlElement
	private Integer idHistoricoUsuario;
	
	@XmlElement
	private Integer limit;
	
	@XmlElement
	private Integer offset;
	
	@XmlElement(required=true)
	private String type;
	
	@XmlElement(required=true)
	private Autenticacao autenticacao;
	
	public ServiceRequest() {
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String[] getOpcaoEscolhida() {
		return opcaoEscolhida;
	}

	public void setOpcaoEscolhida(String[] opcaoEscolhida) {
		this.opcaoEscolhida = opcaoEscolhida;
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

	public Integer getIdQuiz() {
		return idQuiz;
	}

	public void setIdQuiz(Integer idQuiz) {
		this.idQuiz = idQuiz;
	}

	public Integer getIdHistoricoUsuario() {
		return idHistoricoUsuario;
	}

	public void setIdHistoricoUsuario(Integer idHistoricoUsuario) {
		this.idHistoricoUsuario = idHistoricoUsuario;
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
