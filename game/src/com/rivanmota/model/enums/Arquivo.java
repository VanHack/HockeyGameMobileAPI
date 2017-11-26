package com.rivanmota.model.enums;

public enum Arquivo {

	CAMINHO_FISICO ("CAMINHO_FISICO"),
	CAMINHO_WEB ("CAMINHO_WEB"),
	IMG_TEMPLATES("IMG_TEMPLATES"),
	SITE_ROOT("SITE_ROOT"),
	EMAIL_USER("EMAIL_USER"),
	EMAIL_SENHA("EMAIL_SENHA"),
	EMAIL_PORTA("EMAIL_PORTA"),
	EMAIL_HOST("EMAIL_HOST"),
	EMAIL_FROM("EMAIL_FROM"),
	PASTA_DOCUMENTOS("PASTA_DOCUMENTOS");
	
	private String chave;
	
	private Arquivo(String chave) {
		this.chave = chave;
	}
	
	public String getChave() {
		return this.chave;
	}
	
}
