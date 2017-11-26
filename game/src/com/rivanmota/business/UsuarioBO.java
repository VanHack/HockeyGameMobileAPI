package com.rivanmota.business;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import com.rivanmota.dao.ConfiguracaoDAO;
import com.rivanmota.dao.LogDAO;
import com.rivanmota.dao.UsuarioDAO;
import com.rivanmota.dao.impl.ConfiguracaoDAOImpl;
import com.rivanmota.dao.impl.LogDAOImpl;
import com.rivanmota.dao.impl.UsuarioDAOImpl;
import com.rivanmota.exception.ErrorException;
import com.rivanmota.model.Erro;
import com.rivanmota.model.Errors;
import com.rivanmota.model.Log;
import com.rivanmota.model.ServiceRequest;
import com.rivanmota.model.Usuario;
import com.rivanmota.model.Usuarios;
import com.rivanmota.model.enums.Arquivo;
import com.rivanmota.model.enums.StatusUsuario;
import com.rivanmota.model.enums.TipoLog;
import com.rivanmota.template.EsqueciSenhaTemplate;
import com.rivanmota.utils.Email;
import com.rivanmota.utils.ObjectUtils;

public class UsuarioBO {
	
	private Errors errors;
	private LogDAO logDAO;
	private UsuarioDAO usuarioDAO;
	private ConfiguracaoDAO configuracaoDAO; 
	private HttpServletRequest request;
	
	public UsuarioBO(HttpServletRequest request, Errors errors) {
		this.request = request;
		this.errors = errors;
		logDAO = new LogDAOImpl(request);
		usuarioDAO = new UsuarioDAOImpl(request);
		configuracaoDAO = new ConfiguracaoDAOImpl(request);
	}
	
	public Usuario primeiroCadastro(Usuario usuario, HttpServletRequest request) throws ErrorException {
		
		if(this.errors.hasErrors()) {
			throw new ErrorException(this.errors);
		} else {
			Usuario retorno = null;
			Usuario c = usuarioDAO.findById(usuario.getId());
			if(ObjectUtils.isNotNullAndNotEmpty(c)) {
//				usuario.getDataNascimento().add(Calendar.DAY_OF_YEAR, 1);
				retorno = usuarioDAO.updateAndReturn(usuario);
				
				Log log = new Log(usuario.getId(),TipoLog.REALIZAR_CADASTRO);
				log.setDescricao("Usuário atualizado com sucesso");
				logDAO.registrar(log);
				
				if(ObjectUtils.isNotNullAndNotEmpty(c)) {
					return retorno; 
				} 
			}
		}
		Log log = new Log(usuario.getId(),TipoLog.REALIZAR_CADASTRO);
		log.setDescricao("Erro ao atualizar o usuário");
		logDAO.registrar(log);
		return null;
	}
	
	public Usuario atualizarCadastro(Usuario usuario, HttpServletRequest request) throws ErrorException {
//		this.validar(usuario);
		
		if(this.errors.hasErrors()) {
			throw new ErrorException(this.errors);
		} else {
			Usuario retorno = null;
			Usuario c = usuarioDAO.findById(usuario.getId());
			if(ObjectUtils.isNotNullAndNotEmpty(c)) {
//				usuario.getDataNascimento().add(Calendar.DAY_OF_YEAR, 1);
				retorno = usuarioDAO.updateAndReturn(usuario);
				
				Log log = new Log(usuario.getId(),TipoLog.ATUALIZAR_CADASTRO);
				log.setDescricao("Usuário atualizado com sucesso");
				logDAO.registrar(log);
				
				retorno.getDataNascimento().add(Calendar.DAY_OF_MONTH, 1);
				return retorno;
			}
		}
		Log log = new Log(usuario.getId(),TipoLog.ATUALIZAR_CADASTRO);
		log.setDescricao("Erro ao atualizar o usuário");
		logDAO.registrar(log);
		return null;
	}
	
	private void checarSeUsuarioEstaEmEstadoInvalido(Usuario usuario) {	
		if (isUsuarioExistenteNaBaseComAlgumDosStatus(usuario, new StatusUsuario[] {StatusUsuario.INVALIDADO})) {
			this.errors.addErro(new Erro("cpf", "CPF inválidado."));
		}
	}
	
	private boolean isUsuarioExistenteNaBaseComAlgumDosStatus(Usuario usuario, StatusUsuario[] status) {
		for (StatusUsuario sc : status) {
			if (usuario.getStatusUsuario().equals(sc)) {
				return true;
			}
		}
		return false;
	}

	public Usuario login(Usuario usuario, HttpServletRequest request) throws ErrorException {
		
		if(this.errors.hasErrors()) {
			throw new ErrorException(this.errors);
		} else {
			Usuario u = usuarioDAO.getUsuarioByEmailAndSenha(usuario.getEmailCorporativo(), usuario.getSenha());
			if(ObjectUtils.isNotNullAndNotEmpty(u)) {
				Log log = new Log(u.getId(),TipoLog.LOGIN);
				log.setDescricao("Usuário autenticado com sucesso");
				logDAO.registrar(log);
				if (u.getDataNascimento() != null){
					u.getDataNascimento().add(Calendar.DAY_OF_MONTH, 1);
				}
				return u; // Usuário autenticado com sucesso
			} else{
				Log log = new Log();
				log.setTipoLog(TipoLog.LOGIN);
				log.setDescricao("Erro ao autenticar o colaborador: " + usuario.getEmailCorporativo());
				logDAO.registrar(log);
				
				return null; // Erro ao autenticar o colaborador
			}
		}
	}
	
	public void validarAutenticar(Usuario usuario) {
		if(ObjectUtils.isNullOrEmpty(usuario.getDocumento())) {
			errors.addErro(new Erro("documento","Informe o documento"));
		}
//		if(ObjectUtils.isNullOrEmpty(usuario.getSenha())) {
//			errors.addErro(new Erro("senha","Informe a senha."));
//		} else if(usuario.getSenha().length() < 6) {
//			errors.addErro(new Erro("senha","A senha deve possuir no mínimo 6 caracteres"));
//		}
	}
	
	public Usuario getUsuarioByEmailComStatusValido(Usuario usuario, HttpServletRequest request) throws ErrorException {
		if(ObjectUtils.isNullOrEmpty(usuario.getEmailCorporativo())) {
			errors.addErro(new Erro("email","Informe o email corporativo"));
		}
		if(this.errors.hasErrors()) {
			throw new ErrorException(this.errors);
		} else {
			Usuario c = usuarioDAO.getUsuarioByEmail(usuario.getEmailCorporativo());
			if(ObjectUtils.isNotNullAndNotEmpty(c)) {
				
				return c;
			} else{
				return null;
			}
		}
	}
	
	public Usuario getUsuarioByEmailEsqueciSenha(Usuario usuario, HttpServletRequest request) throws ErrorException {
		if(ObjectUtils.isNullOrEmpty(usuario.getEmailCorporativo())) {
			errors.addErro(new Erro("email","Informe o email corporativo"));
		}
		if(this.errors.hasErrors()) {
			throw new ErrorException(this.errors);
		} else {
			Usuario c = usuarioDAO.getUsuarioByEmail(usuario.getEmailCorporativo());
			if(ObjectUtils.isNotNullAndNotEmpty(c)) {
				
				enviarEmailRecuperarSenha(usuario.getEmailCorporativo(), request);
				
				return c;
			} else{
				return null;
			}
		}
	}
	
	public void enviarEmailRecuperarSenha(final String emailUsuario, HttpServletRequest request) {
		final Usuario usuario = usuarioDAO.getUsuarioByEmail(emailUsuario);

		String siteRoot = configuracaoDAO.getByChave(Arquivo.SITE_ROOT.getChave()).getValor();		
		
		EsqueciSenhaTemplate est = new EsqueciSenhaTemplate(configuracaoDAO.getByChave(Arquivo.IMG_TEMPLATES.getChave()).getValor());
		est.setNomeUsuario(usuario.getNome());
		est.setSenha(usuario.getSenha());
		
		Log log = new Log(usuario.getId(),TipoLog.SOLICITAR_RECUPERACAO_SENHA);
		logDAO.registrar(log);
		
//		usuario.setToken(usuario.generateToken());
//		usuarioDAO.save(usuario);
//		est.setToken(usuario.getToken());
		
		est.setSite(siteRoot);
		
		final Email email = new Email();
		final String message = est.getHTMLString();
		class SendEmail implements Runnable{
			@Override
			public void run() {
				email.enviarEmail(message, emailUsuario, "Esqueci senha - Game Conecta");
			}
		}
		new Thread(new SendEmail()).start();
	}
	
	
	public Usuarios ranking(ServiceRequest sr, HttpServletRequest request) throws ErrorException {
		return usuarioDAO.ranking(sr);
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	
}