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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rivanmota.business.AutenticacaoBO;
import com.rivanmota.business.UsuarioBO;
import com.rivanmota.exception.ErrorException;
import com.rivanmota.model.Erro;
import com.rivanmota.model.Errors;
import com.rivanmota.model.HistoricoUsuarioFiltro;
import com.rivanmota.model.ServiceRequest;
import com.rivanmota.model.Usuario;
import com.rivanmota.utils.Mensagem;
import com.rivanmota.utils.ObjectUtils;

@Path("/usuario")
public class UsuarioService {

	@Context
	private HttpServletRequest request;
	private UsuarioBO usuarioBO;
	private Errors errors;
	
	public UsuarioService() {
		
	}
	
	@POST
	@Path("/cadastro")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response primeiroCadastro(Usuario usuario) throws JsonProcessingException {
		errors = new Errors();
		usuarioBO = new UsuarioBO(this.request, errors);
		if (!new AutenticacaoBO(this.request).isValid(usuario.getAutenticacao())) {
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.USUARIO_SENHA_INVALIDA);
			return Response.ok(erro, getMediaTypePorString(usuario.getType())).build();
		}
		if (ObjectUtils.isNullOrEmpty(usuario.getCelularCorporativo())){
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.INFORME_CELULAR_USUARIO);
			return Response.ok(erro, getMediaTypePorString(usuario.getType())).build();
		}
		if (ObjectUtils.isNullOrEmpty(usuario.getDataNascimento())){
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.INFORME_DATANASCIMENTO_USUARIO);
			return Response.ok(erro, getMediaTypePorString(usuario.getType())).build();
		}
		if (ObjectUtils.isNullOrEmpty(usuario.getGenero())){
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.INFORME_GENERO_USUARIO);
			return Response.ok(erro, getMediaTypePorString(usuario.getType())).build();
		}
		try {
			return Response.ok(usuarioBO.primeiroCadastro(usuario, request), getMediaTypePorString(usuario.getType())).build();
		} catch (ErrorException e) {
			return Response.ok(e.getErrors(), getMediaTypePorString(usuario.getType())).build();
		}
		
	}
	
	@POST
	@Path("/perfil")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response atualizarCadastro(Usuario usuario) throws JsonProcessingException {
		errors = new Errors();
		usuarioBO = new UsuarioBO(this.request, errors);
		if (!new AutenticacaoBO(this.request).isValid(usuario.getAutenticacao())) {
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.USUARIO_SENHA_INVALIDA);
			return Response.ok(erro, getMediaTypePorString(usuario.getType())).build();
		}
		if (ObjectUtils.isNullOrEmpty(usuario.getCelularCorporativo())){
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.INFORME_CELULAR_USUARIO);
			return Response.ok(erro, getMediaTypePorString(usuario.getType())).build();
		}
		if (ObjectUtils.isNullOrEmpty(usuario.getDataNascimento())){
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.INFORME_DATANASCIMENTO_USUARIO);
			return Response.ok(erro, getMediaTypePorString(usuario.getType())).build();
		}
		if (ObjectUtils.isNullOrEmpty(usuario.getGenero())){
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.INFORME_GENERO_USUARIO);
			return Response.ok(erro, getMediaTypePorString(usuario.getType())).build();
		}
		try {
			return Response.ok(usuarioBO.atualizarCadastro(usuario, request), getMediaTypePorString(usuario.getType())).build();
		} catch (ErrorException e) {
			return Response.ok(e.getErrors(), getMediaTypePorString(usuario.getType())).build();
		}
		
	}
	
	@POST
	@Path("/login")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response login(Usuario usuario) throws JsonProcessingException {
		errors = new Errors();
		usuarioBO = new UsuarioBO(this.request, errors);
		if (!new AutenticacaoBO(this.request).isValid(usuario.getAutenticacao())) {
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.USUARIO_SENHA_INVALIDA);
			return Response.ok(erro, getMediaTypePorString(usuario.getType())).build();
		}
		if (ObjectUtils.isNullOrEmpty(usuario.getEmailCorporativo()) && ObjectUtils.isNullOrEmpty(usuario.getSenha())){
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.INFORME_EMAIL_SENHA);
			return Response.ok(erro, getMediaTypePorString(usuario.getType())).build();
		}
		try {
			return Response.ok(usuarioBO.login(usuario, request), getMediaTypePorString(usuario.getType())).build();
		} catch (ErrorException e) {
			return Response.ok(e.getErrors(), getMediaTypePorString(usuario.getType())).build();
		}
	}
	
	@POST
	@Path("/esqueci-senha")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response esqueciSenha(Usuario usuario) throws JsonProcessingException {
		errors = new Errors();
		usuarioBO = new UsuarioBO(this.request, errors);
		if (!new AutenticacaoBO(this.request).isValid(usuario.getAutenticacao())) {
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.USUARIO_SENHA_INVALIDA);
			return Response.ok(erro, getMediaTypePorString(usuario.getType())).build();
		}
		try {
			return Response.ok(usuarioBO.getUsuarioByEmailEsqueciSenha(usuario, request), getMediaTypePorString(usuario.getType())).build();
		} catch (ErrorException e) {
			return Response.ok(e.getErrors(), getMediaTypePorString(usuario.getType())).build();
		}
	}
	
	public Response getUsuarioByEmailComStatusValido(Usuario usuario) throws JsonProcessingException {
		errors = new Errors();
		usuarioBO = new UsuarioBO(this.request, errors);
		if (!new AutenticacaoBO(this.request).isValid(usuario.getAutenticacao())) {
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.USUARIO_SENHA_INVALIDA);
			return Response.ok(erro, getMediaTypePorString(usuario.getType())).build();
		}
		try {
			return Response.ok(usuarioBO.getUsuarioByEmailComStatusValido(usuario, request), getMediaTypePorString(usuario.getType())).build();
		} catch (ErrorException e) {
			return Response.ok(e.getErrors(), getMediaTypePorString(usuario.getType())).build();
		}
	}
	
 	@POST
 	@Path("/ranking")
 	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
 	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
 	public Response ranking(ServiceRequest sr) throws JsonProcessingException {
 		errors = new Errors();
 		usuarioBO = new UsuarioBO(this.request, errors);
 		if (!new AutenticacaoBO(this.request).isValid(sr.getAutenticacao())) {
 			Erro erro = new Erro();
 			erro.setMensagem(Mensagem.USUARIO_SENHA_INVALIDA);
 			return Response.ok(erro, getMediaTypePorString(sr.getType())).build();
 		}
 		if (ObjectUtils.isNullOrEmpty(sr.getIdUsuario())){
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.INFORME_ID_USUARIO);
			return Response.ok(erro, getMediaTypePorString(sr.getType())).build();
		}
 		try {
 			return Response.ok(usuarioBO.ranking(sr, request), getMediaTypePorString(sr.getType())).build();
 		} catch (ErrorException e) {
 			return Response.ok(e.getErrors(), getMediaTypePorString(sr.getType())).build();
 		}
 		
 	}
 	
	@POST
	@Path("/historico")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response buscar(HistoricoUsuarioFiltro hf) {
		errors = new Errors();
		usuarioBO = new UsuarioBO(this.request, errors);
		if (!new AutenticacaoBO(this.request).isValid(hf.getAutenticacao())) {
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.USUARIO_SENHA_INVALIDA);
			return Response.ok(erro, getMediaTypePorString(hf.getType())).build();
		}
		if (ObjectUtils.isNullOrEmpty(hf.getLimit()) && ObjectUtils.isNullOrEmpty(hf.getOffset())) {
			hf.setLimit(0);
			hf.setOffset(1);
		}else if (ObjectUtils.isNullOrEmpty(hf.getLimit()) ^ ObjectUtils.isNullOrEmpty(hf.getOffset())){
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.INFORME_LIMIT_OFFSET);
			return Response.ok(erro, getMediaTypePorString(hf.getType())).build();
		}
		if (ObjectUtils.isNullOrEmpty(hf.getIdUsuario())){
			Erro erro = new Erro();
			erro.setMensagem(Mensagem.INFORME_ID_USUARIO);
			return Response.ok(erro, getMediaTypePorString(hf.getType())).build();
		}
		return Response.ok(usuarioBO.getUsuarioDAO().filtrar(hf), getMediaTypePorString(hf.getType())).build();
		
	}
	
//	@POST
//	@Path("/remover")
//	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response remover(Usuario usuario) throws JsonProcessingException {
//		errors = new Errors();
//		usuarioBO = new UsuarioBO(this.request, errors);
//		if (!new AutenticacaoBO(this.request).isValid(usuario.getAutenticacao())) {
//			Erro erro = new Erro();
//			erro.setMensagem("Usuário ou Senha inválida");
//			return Response.ok(erro, getMediaTypePorString(usuario.getType())).build();
//		}
//		if (ObjectUtils.isNullOrEmpty(usuario.getId())) {
//			Erro erro = new Erro();
//			erro.setMensagem("Informe o código do usuário");
//			return Response.ok(erro, getMediaTypePorString(usuario.getType())).build();
//		}
//		try {
//			return Response.ok(usuarioBO.remover(usuario, request), getMediaTypePorString(usuario.getType())).build();
//		} catch (ErrorException e) {
//			return Response.ok(e.getErrors(), getMediaTypePorString(usuario.getType())).build();
//		}
//		
//	}
	
}