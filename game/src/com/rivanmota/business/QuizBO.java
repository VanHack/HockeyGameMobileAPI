package com.rivanmota.business;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import com.rivanmota.dao.LogDAO;
import com.rivanmota.dao.QuizDAO;
import com.rivanmota.dao.impl.LogDAOImpl;
import com.rivanmota.dao.impl.QuizDAOImpl;
import com.rivanmota.model.Log;
import com.rivanmota.model.Quiz;
import com.rivanmota.model.Resultado;
import com.rivanmota.model.ServiceRequest;
import com.rivanmota.model.enums.TipoLog;

public class QuizBO {

	private Connection connection;
	private LogDAO logDAO;

	public QuizBO(HttpServletRequest request) {
		this.connection = (Connection) request.getAttribute("connection");
		logDAO = new LogDAOImpl(request);
	}
	
	public Quiz listar(ServiceRequest sr) {
		QuizDAO dao = new QuizDAOImpl(this.connection);
//		boolean result = dao.isQuizRespondido(sr);
//		if (!result)
//			return null;
		return dao.listar(sr);
	}

	public Resultado responder(ServiceRequest sr) {
		Resultado result = new QuizDAOImpl(this.connection).responder(sr); 
		Log log = new Log(sr.getIdUsuario(), TipoLog.RESPONDER_QUIZ);
		log.setDescricao(result.getMensagem() +", Opção escolhida: " + QuizDAOImpl.getStringOpcaoEscolhida(sr.getOpcaoEscolhida()) + ", idQuiz: " + sr.getIdQuiz());
		logDAO.registrar(log);
		
		return result;
	}
	
	public boolean isQuizRespondido(ServiceRequest sr){
		QuizDAO dao = new QuizDAOImpl(this.connection);
		return dao.isQuizRespondido(sr);
	}
	
	public Quiz getQuizByHistoricoUsuario(ServiceRequest sr){
		QuizDAO dao = new QuizDAOImpl(this.connection);
		return dao.getQuizByHistoricoUsuario(sr);
	}
}
