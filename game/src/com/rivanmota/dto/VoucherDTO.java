package com.rivanmota.dto;

import java.util.Calendar;

public class VoucherDTO {

	private Integer codigoCliente;
	private Long codigoOferta;
	private String cupom;
	private String tipoCupom;
	private Long codigoLoja;
	private Calendar dataValidade;
	
	public VoucherDTO() {
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public Long getCodigoOferta() {
		return codigoOferta;
	}

	public void setCodigoOferta(Long codigoOferta) {
		this.codigoOferta = codigoOferta;
	}

	public String getCupom() {
		return cupom;
	}

	public void setCupom(String cupom) {
		this.cupom = cupom;
	}

	public String getTipoCupom() {
		return tipoCupom;
	}

	public void setTipoCupom(String tipoCupom) {
		this.tipoCupom = tipoCupom;
	}

	public Long getCodigoLoja() {
		return codigoLoja;
	}

	public void setCodigoLoja(Long codigoLoja) {
		this.codigoLoja = codigoLoja;
	}

	public Calendar getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Calendar dataValidade) {
		this.dataValidade = dataValidade;
	}
	
}
