package com.rivanmota.dao;

import com.rivanmota.model.Autenticacao;
import com.rivanmota.model.Configuracao;

public interface ConfiguracaoDAO {

	String getValorPorChave(String chave);
	boolean autenticarRequisicao(Autenticacao autenticacao);
	Configuracao getByChave(String chave);
	
}
