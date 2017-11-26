package com.rivanmota.business;

import javax.servlet.http.HttpServletRequest;

import com.rivanmota.dao.ConfiguracaoDAO;
import com.rivanmota.dao.impl.ConfiguracaoDAOImpl;
import com.rivanmota.model.ServiceRequest;
import com.rivanmota.utils.ObjectUtils;

public class RequestBO {

	private ConfiguracaoDAO configuracaoDAO;

	public RequestBO(HttpServletRequest request) {
		this.configuracaoDAO = new ConfiguracaoDAOImpl(request);
	}

	public boolean isRequestValida(ServiceRequest sr) {
		if (ObjectUtils.isNullOrEmpty(sr) || (ObjectUtils.isNullOrEmpty(sr.getAutenticacao()) || !sr.getAutenticacao().isValida())) {
			return false;
		}
		return configuracaoDAO.autenticarRequisicao(sr.getAutenticacao());
	}

}
