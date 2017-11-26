package com.rivanmota.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import com.rivanmota.dao.QuizDAO;
import com.rivanmota.model.Quiz;
import com.rivanmota.model.Resultado;
import com.rivanmota.model.ServiceRequest;
import com.rivanmota.utils.DataUtils;

public class QuizDAOImpl implements QuizDAO {

	private Connection connection;
	
	public QuizDAOImpl(Connection connection) {
		this.connection = connection;
	}
	
	public Quiz listar(ServiceRequest sr) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" q.id AS id, "); 
		sb.append(" q.descricao AS descricao, "); 
		sb.append(" q.opcao_a AS opcao_a, ");
		sb.append(" q.opcao_b AS opcao_b, ");
        sb.append(" q.opcao_c AS opcao_c, ");
        sb.append(" q.opcao_correta AS opcao_correta, "); 
        sb.append(" q.data_validade AS data_validade, ");
        sb.append(" q.available AS available ");
        sb.append("FROM ");
        sb.append("    quiz q ");             
        sb.append("WHERE  ");
        sb.append(" (ISNULL(q.removido) OR (q.removido = 0)) "); 
        sb.append(" AND (CAST(q.data_validade AS DATE) = CAST(NOW() AS DATE)) ");  
        sb.append(" AND available = 1  ");
        sb.append(" AND q.id NOT IN ");
        sb.append(" (SELECT qr.fk_quiz FROM quiz_respondido qr WHERE (CAST(qr.data_resposta AS DATE) = CAST(q.data_validade AS DATE)) AND qr.fk_usuario = ?) ");  
        sb.append("ORDER BY q.id LIMIT 1 "); 
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sb.toString());
			stmt.setInt(1, sr.getIdUsuario());
			ResultSet rs = stmt.executeQuery();
			
			Quiz quiz = null;
			if (rs.next()){
				quiz = new Quiz();
				quiz.setId(rs.getInt(1));
				quiz.setDescricao(rs.getString(2));
				quiz.setOpcaoA(rs.getString(3));
				quiz.setOpcaoB(rs.getString(4));
				quiz.setOpcaoC(rs.getString(5));
				quiz.setOpcaoCorreta(rs.getString(6));
				quiz.setDataValidade(DataUtils.javaSQLTimestampToCalendar(rs.getTimestamp(7)));
			}

			return quiz;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Quiz findById(Integer id) {
		String SQL = "SELECT q.id, q.descricao, q.opcao_a, q.opcao_b, q.opcao_c, q.opcao_correta, q.data_validade "
				+ "FROM quiz q WHERE (q.removido IS NULL OR q.removido = 0) AND id = ? LIMIT 1";
		try {
			PreparedStatement stmt = connection.prepareStatement(SQL);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			Quiz quiz = null;
			if (rs.next()){
				quiz = new Quiz();
				quiz.setId(rs.getInt("q.id"));
				quiz.setDescricao(rs.getString("q.descricao"));
				quiz.setOpcaoA(rs.getString("q.opcao_a"));
				quiz.setOpcaoB(rs.getString("q.opcao_b"));
				quiz.setOpcaoC(rs.getString("q.opcao_c"));
				quiz.setOpcaoCorreta(rs.getString("q.opcao_correta"));
				quiz.setDataValidade(DataUtils.javaSQLTimestampToCalendar(rs.getTimestamp(7)));
			}
			
			return quiz;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Resultado responder(ServiceRequest sr) {
		String SQL = "INSERT INTO quiz_respondido (opcao_escolhida, fk_quiz, fk_usuario, data_resposta) VALUES (?,?,?,now())";
		String SQL_HISTORICO = "";
		String pontos = "0";
		
		if (isRespostaCorreta(sr)){
//			if (countQuizByDate() <= 10){
//				pontos = "(SELECT pontos FROM status where id = 6)*2"; 
//			}else{
				pontos = "(SELECT pontos FROM status where id = 6)"; 
//			}
		}
		
		SQL_HISTORICO = "INSERT INTO historico_usuario (data, fk_usuario, fk_quiz_respondido, fk_status, pontos) VALUES (now(),?,?, 6, "+pontos+")";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, getStringOpcaoEscolhida(sr.getOpcaoEscolhida()));
			stmt.setInt(2, sr.getIdQuiz());
			stmt.setInt(3, sr.getIdUsuario());
			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();
			int key = 0;
			if (rs.next()) {
			    key = rs.getInt(1);
			}
			stmt.close();
			rs.close();
			
			PreparedStatement stmt_historico = connection.prepareStatement(SQL_HISTORICO);
			stmt_historico.setInt(1, sr.getIdUsuario());
			stmt_historico.setInt(2, key);
			stmt_historico.execute();
			stmt_historico.close();
			
			if (isRespostaCorreta(sr)){
				return new Resultado(1, "Resposta correta");
			}

			return new Resultado(2, "Resposta errada");
		} catch (SQLException e) {
			e.printStackTrace();
			return new Resultado(3, "Houve um erro");
		}
	}
	
	private boolean isRespostaCorreta(ServiceRequest sr) {
		String SQL = "SELECT q.opcao_correta FROM quiz q WHERE (q.removido IS NULL OR q.removido = 0) AND id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(SQL);
			stmt.setInt(1, sr.getIdQuiz());
			
			ResultSet rs = stmt.executeQuery();
			
			Quiz quiz = null;
			if (rs.next()){
				quiz = new Quiz();
				quiz.setOpcaoCorreta(rs.getString("q.opcao_correta"));
				
				if (quiz.getOpcaoCorreta().equalsIgnoreCase(getStringOpcaoEscolhida(sr.getOpcaoEscolhida()))){
					return true;
				}
			}
			
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isQuizRespondido(ServiceRequest sr) {
		String SQL = "SELECT q.id FROM quiz q where DATE(q.data_validade) = DATE(NOW()) AND NOT EXISTS("
				+ "SELECT id FROM historico_usuario hu WHERE DATE(hu.data) = DATE(NOW()) AND hu.fk_status = 6 AND fk_usuario = ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(SQL);
			stmt.setInt(1, sr.getIdUsuario());
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Quiz getQuizByHistoricoUsuario(ServiceRequest sr) {
		String SQL = "SELECT q.id, opcao_correta, opcao_a, opcao_b, opcao_c, descricao FROM historico_usuario hu INNER JOIN quiz_respondido qr ON hu.fk_quiz_respondido = qr.id INNER JOIN quiz q ON qr.fk_quiz = q.id "
				+ "WHERE hu.id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(SQL);
			stmt.setInt(1, sr.getIdHistoricoUsuario());
			
			ResultSet rs = stmt.executeQuery();
			
			Quiz quiz = null;
			if (rs.next()){
				quiz = new Quiz();
				quiz.setId(rs.getInt(1));
				quiz.setOpcaoCorreta(rs.getString(2));
				quiz.setOpcaoA(rs.getString(3));
				quiz.setOpcaoB(rs.getString(4));
				quiz.setOpcaoC(rs.getString(5));
				quiz.setDescricao(rs.getString(6));
				return quiz;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Long countQuizByDate() {
		String SQL = "SELECT COUNT(id) FROM historico_usuario hu WHERE DATE(hu.data) = DATE(NOW()) AND hu.fk_status = 6";
		try {
			PreparedStatement stmt = connection.prepareStatement(SQL);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()){
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0L;
	}

	public static String getStringOpcaoEscolhida(String[] value) {
		String out = "";
		Arrays.sort(value);
		for (String x : value) {
			out = out.concat(x);
		}
		return out;
	}
	
}
