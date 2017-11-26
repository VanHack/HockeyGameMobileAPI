package com.rivanmota.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.rivanmota.dao.ConfiguracaoDAO;
import com.rivanmota.model.Autenticacao;
import com.rivanmota.model.Configuracao;

public class ConfiguracaoDAOImpl implements ConfiguracaoDAO {

	private Connection connection;
	PreparedStatement stmt;
	
	public ConfiguracaoDAOImpl(HttpServletRequest request) {
		this.connection =  (Connection) request.getAttribute("connection");
	}
	
	public String getValorPorChave(String chave) {
		try {
			stmt = connection.prepareStatement("SELECT valor FROM configuracao where chave = ?");
			stmt.setString(1, chave);
			
			ResultSet row = stmt.executeQuery();
			row.next();
			return row.getString(1);
		} catch (SQLException e) {
			return null;
		}
	}
	
	public boolean autenticarRequisicao(Autenticacao autenticacao) {
		try {
			stmt = connection.prepareStatement("select exists (select * from configuracao where (chave = 'WS_SENHA' AND valor = ?) AND exists (select * from configuracao where chave = 'WS_USER' AND valor = ?))");
			stmt.setString(1, autenticacao.getSenha());
			stmt.setString(2, autenticacao.getUsuario());
			
			ResultSet row = stmt.executeQuery();
			row.next();
			return row.getBoolean(1);
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public Configuracao getByChave(String chave) {
		try {
			stmt = connection.prepareStatement("SELECT c.chave, c.valor FROM configuracao c WHERE c.chave = ?");
			stmt.setString(1, chave);
			
			ResultSet rs = stmt.executeQuery();
			Configuracao configuracao = null;
			if (rs.next()){
				configuracao = new Configuracao();
				configuracao.setChave(rs.getString("c.chave"));
				configuracao.setValor(rs.getString("c.valor"));
			}
			
			return configuracao;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
