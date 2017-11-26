package com.rivanmota.services;

import static com.rivanmota.constants.MediaType.getMediaTypePorString;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rivanmota.business.QuizBO;
import com.rivanmota.business.RequestBO;
import com.rivanmota.model.Erro;
import com.rivanmota.model.ServiceRequest;
import com.rivanmota.utils.Mensagem;

@Path("/quiz")
public class QuizService {

	@Context
	private HttpServletRequest request;
	 
	public QuizService() {
		
	}	
	
	@POST
	@Path("/listar")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getQuiz(ServiceRequest sr) {
		if (!new RequestBO(request).isRequestValida(sr)) {
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.USUARIO_SENHA_INVALIDA);
			return Response.ok(erro, getMediaTypePorString(sr.getType())).build();	
		}
		return Response.ok(new QuizBO(this.request).listar(sr), getMediaTypePorString(sr.getType())).build();
	}
	
	@POST
	@Path("/responder")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response responder(ServiceRequest sr) {
		if (!new RequestBO(request).isRequestValida(sr)) {
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.USUARIO_SENHA_INVALIDA);
			return Response.ok(erro, getMediaTypePorString(sr.getType())).build();	
		}
		return Response.ok(new QuizBO(this.request).responder(sr), getMediaTypePorString(sr.getType())).build();
	}
	
	@POST
	@Path("/disponivel")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response disponivel(ServiceRequest sr) {
		if (!new RequestBO(request).isRequestValida(sr)) {
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.USUARIO_SENHA_INVALIDA);
			return Response.ok(erro, getMediaTypePorString(sr.getType())).build();	
		}
		return Response.ok(new QuizBO(this.request).isQuizRespondido(sr), getMediaTypePorString(sr.getType())).build();
	}
	
	@POST
	@Path("/resposta")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response buscar(ServiceRequest sr) {
		if (!new RequestBO(request).isRequestValida(sr)) {
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.USUARIO_SENHA_INVALIDA);
			return Response.ok(erro, getMediaTypePorString(sr.getType())).build();	
		}
		return Response.ok(new QuizBO(this.request).getQuizByHistoricoUsuario(sr), getMediaTypePorString(sr.getType())).build();
	}
	
}

