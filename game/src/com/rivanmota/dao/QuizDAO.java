package com.rivanmota.dao;

import com.rivanmota.model.Quiz;
import com.rivanmota.model.ServiceRequest;

public interface QuizDAO {

	Quiz listar(ServiceRequest sr);
	Quiz findById(Integer id);
	boolean isQuizRespondido(ServiceRequest sr);
	Quiz getQuizByHistoricoUsuario(ServiceRequest sr);
	
}
