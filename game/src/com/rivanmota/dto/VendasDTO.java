package com.rivanmota.dto;


public class VendasDTO {

	private String nomeUsuario;
	private String perfilUsuario;
	private String dataVenda;
	private String produto;
	private String nomeCliente;
	private String empresa;
	private String cidade;
	private String estado;

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(String perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public String getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "{\"nomeUsuario\": \"" + nomeUsuario + "\", \"perfilUsuario\": \"" + perfilUsuario + "\", \"dataVenda\": \"" + dataVenda
				+ "\", \"produto\": \"" + produto + "\", \"nomeCliente\": \"" + nomeCliente + "\", \"empresa\": \"" + empresa + "\", \"cidade\": \""
				+ cidade + "\", \"estado\": \"" + estado + "\"}";
	}

}
