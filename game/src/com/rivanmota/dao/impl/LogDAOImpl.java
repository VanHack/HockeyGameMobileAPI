 package com.rivanmota.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.rivanmota.dao.LogDAO;
import com.rivanmota.model.Log;
import com.rivanmota.utils.RequestUtils;

public class LogDAOImpl implements LogDAO {

	private Connection connection;
	private HttpServletRequest request;
	
	public LogDAOImpl(HttpServletRequest request) {
		this.connection = (Connection) request.getAttribute("connection");
		this.request = request;
	}
	
	@Override
	public void registrar(Log log) {
		try {
			String sql = "INSERT INTO log_game_conecta_app (descricao, fk_usuario, data, ip, navegador, log_tipo) VALUES(?,?,CURRENT_TIMESTAMP,?,?,?)";		
			PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, log.getDescricao());
				stmt.setInt(2, log.getIdUsuario() != null?log.getIdUsuario():0);
				stmt.setString(3, RequestUtils.getIpDeAcesso(request));
				stmt.setString(4, RequestUtils.getUserAgent(request));
				stmt.setString(5, log.getTipoLog().name());
			stmt.execute();			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
}