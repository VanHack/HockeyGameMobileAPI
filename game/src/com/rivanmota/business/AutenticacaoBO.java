package com.rivanmota.business;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import com.rivanmota.dao.ConfiguracaoDAO;
import com.rivanmota.dao.impl.ConfiguracaoDAOImpl;
import com.rivanmota.model.Autenticacao;
import com.rivanmota.utils.ObjectUtils;

public class AutenticacaoBO {
	
	private Connection connection;
	private ConfiguracaoDAO configuracaoDAO;
	
	public AutenticacaoBO(HttpServletRequest request) {
		this.connection = (Connection) request.getAttribute("connection");		
		this.configuracaoDAO = new ConfiguracaoDAOImpl(request);
	}
	
	public boolean isValid(Autenticacao autenticacao) {
		if(ObjectUtils.isNullOrEmpty(autenticacao) && !autenticacao.isValida()) {
			return false;
		} 
		
		return configuracaoDAO.autenticarRequisicao(autenticacao);
	}

}
