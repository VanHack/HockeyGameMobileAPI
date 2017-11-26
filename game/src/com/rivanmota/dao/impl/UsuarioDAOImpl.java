package com.rivanmota.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rivanmota.dao.UsuarioDAO;
import com.rivanmota.model.HistoricoUsuarioFiltro;
import com.rivanmota.model.HistoricoUsuarioPaginado;
import com.rivanmota.model.ServiceRequest;
import com.rivanmota.model.Usuario;
import com.rivanmota.model.Usuario.Genero;
import com.rivanmota.model.Usuarios;
import com.rivanmota.model.enums.StatusUsuario;
import com.rivanmota.utils.DataUtils;
import com.rivanmota.utils.ObjectUtils;
import com.rivanmota.utils.StringUtils;

public class UsuarioDAOImpl implements UsuarioDAO {

	private Connection connection;
	
	public UsuarioDAOImpl(HttpServletRequest request) {
		this.connection =  (Connection) request.getAttribute("connection");
	}
	
	@Override
	public boolean cadastrar(Usuario usuario) {
		try {
			String sql = "INSERT INTO usuario(nome, email, documento, celular, data_nascimento, genero, uf, cidade, senha, status_cliente, data_cadastro, origem) VALUES(?,?,?,?,?,?,?,?,?,?, CURRENT_TIMESTAMP, 'APLICATIVO')";
			PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, usuario.getNome());
//				stmt.setString(2, usuario.getEmail());
//				stmt.setString(3, StringUtils.removerPontosETracosEBarraDoCPF_CNPJ(usuario.getDocumento()));
//				stmt.setString(4, usuario.getCelular());
//				stmt.setTimestamp(5, DataUtils.getTimestampFromCalendar(usuario.getDataNascimento()));
//				stmt.setString(6, usuario.getGenero().name());
//				stmt.setString(7, usuario.getUf());
//				stmt.setString(8, usuario.getCidade());
				stmt.setString(9, usuario.getSenha());
				stmt.setString(10, usuario.getStatusUsuario().name());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public boolean update(Usuario usuario) {
		try {
			String sql = "UPDATE usuario SET celular_corporativo = ?, data_nascimento = ?, genero = ?, concorda = ?, data_update = now() WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getCelularCorporativo());
			stmt.setTimestamp(2, DataUtils.getTimestampFromCalendar(usuario.getDataNascimento()));
			stmt.setString(3, usuario.getGenero().toString());
			stmt.setBoolean(4, usuario.isConcorda());
			stmt.setInt(5, usuario.getId());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public Usuario updateAndReturn(Usuario usuario) {
		try {
			String sql = "UPDATE usuario SET celular_corporativo = ?, data_nascimento = ?, genero = ?, concorda = ?, senha = ?, data_update = now() WHERE id = ?";
			if (usuario != null && usuario.getSenha() == null){
				sql = "UPDATE usuario SET celular_corporativo = ?, data_nascimento = ?, genero = ?, concorda = ?, data_update = now() WHERE id = ?";
			}
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getCelularCorporativo());
			
			stmt.setTimestamp(2, DataUtils.getTimestampFromCalendar(usuario.getDataNascimento()));
			stmt.setString(3, usuario.getGenero().toString());
			stmt.setBoolean(4, usuario.isConcorda());
			if (usuario != null && usuario.getSenha() == null){
				
				stmt.setInt(5, usuario.getId());
			}else{
				stmt.setString(5, usuario.getSenha());
				stmt.setInt(6, usuario.getId());
			}
			stmt.execute();
			return findById(usuario.getId());
		} catch (SQLException e) {
			return null;
		}
	}
	
	public Usuario findByEmailCorporativo(String emailCorporativo) {
		try {
			PreparedStatement stmt = null;
			stmt = connection.prepareStatement("SELECT u.id, u.nome, u.documento, u.email_corporativo, u.celular_corporativo, u.data_nascimento, u.genero, u.senha "
					+ "FROM usuario u WHERE u.email_corporativo = ? AND (u.removido IS NULL OR u.removido = 0)");
			stmt.setString(1, emailCorporativo);
			
			ResultSet rs = stmt.executeQuery();
			Usuario u = null;
			if(rs.next()) {
				u = new Usuario();
				u.setId(rs.getInt("u.id"));
				u.setNome(rs.getString("u.nome"));
				u.setDocumento(rs.getString("u.documento"));
				u.setEmailCorporativo(rs.getString("u.email_corporativo"));
				u.setCelularCorporativo(rs.getString("u.celular_corporativo"));
				u.setDataNascimento(DataUtils.javaSQLTimestampToCalendar(rs.getTimestamp("u.data_nascimento")));
				u.setGenero(this.verifyDatabaseGenero(rs.getString("u.genero")));
				u.setSenha(rs.getString("u.senha"));
			}
			return u;
			
		} catch (SQLException e) {
			return null;
		}
	}
	public Usuario findById(Integer id) {
		try {
			PreparedStatement stmt = null;
			stmt = connection.prepareStatement("SELECT u.id, u.nome, u.documento, u.email_corporativo, u.celular_corporativo, u.data_nascimento, u.genero, u.senha "
					+ "FROM usuario u WHERE u.id = ? AND (u.removido IS NULL OR u.removido = 0)");
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
			Usuario u = null;
			if(rs.next()) {
				u = new Usuario();
				u.setId(rs.getInt("u.id"));
				u.setNome(rs.getString("u.nome"));
				u.setDocumento(rs.getString("u.documento"));
				u.setEmailCorporativo(rs.getString("u.email_corporativo"));
				u.setCelularCorporativo(rs.getString("u.celular_corporativo"));
				u.setDataNascimento(DataUtils.javaSQLTimestampToCalendar(rs.getTimestamp("u.data_nascimento")));
				u.setGenero(this.verifyDatabaseGenero(rs.getString("u.genero")));
				u.setSenha(rs.getString("u.senha"));
			}
			return u;
			
		} catch (SQLException e) {
			return null;
		}
	}
	
	public Integer getIdUsuarioPorDocumento(String documento) {
		try {
			PreparedStatement stmt = null;
				stmt = connection.prepareStatement("SELECT u.id FROM cliente c WHERE u.documento = ? LIMIT 1");
				stmt.setString(1, StringUtils.removerPontosETracosEBarraDoCPF_CNPJ(documento));
//				stmt.setString(1, documento);
			
			ResultSet set = stmt.executeQuery();
				set.next();
			
			return set.getInt(1);
		} catch (SQLException e) {
			return null;
		}
	}

	
	private Genero verifyDatabaseGenero(String genero) {
		if(ObjectUtils.isNullOrEmpty(genero)) {
			return null;
		} else {
			return Usuario.Genero.valueOf(genero);
		}
	}
	
	private StatusUsuario verifyDatabaseStatus(String statusUsuario) {
		if(ObjectUtils.isNullOrEmpty(statusUsuario)) {
			return null;
		} else {
			return StatusUsuario.valueOf(statusUsuario);
		}
	}
	
	private int getValueByBoolean(boolean bool) {
		if(bool == true) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public Usuario getUsuarioByEmailAndSenha(String email, String senha) {
		try {
			String sql = "SELECT id, nome, email_corporativo, documento, celular_corporativo, data_nascimento, genero, fk_equipe, perfil "
					+ " FROM usuario u WHERE u.email_corporativo = ? and senha = ? AND (u.removido IS NULL OR u.removido = 0) ";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, email); 
			stmt.setString(2, senha);
				
			ResultSet rs = stmt.executeQuery();
			Usuario usuario = null;
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmailCorporativo(rs.getString("email_corporativo"));
				usuario.setDocumento(rs.getString("documento"));
				usuario.setCelularCorporativo(rs.getString("celular_corporativo"));
				Calendar c = null;
				if (rs.getTimestamp("data_nascimento") != null){
					c = DataUtils.javaSQLTimestampToCalendar(rs.getTimestamp("data_nascimento"));
//					c.add(Calendar.DAY_OF_MONTH, 1);
					
				}
				usuario.setDataNascimento(c);
				usuario.setGenero(this.verifyDatabaseGenero(rs.getString("genero")));
				
				usuario.setPerfil(rs.getString("perfil"));
				
//				usuario.setPerfil(perfil);
			}
			return usuario;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public Usuario getUsuarioByEmail(String email) {
		try {
			String sql = "SELECT id, nome, email_corporativo, documento, celular_corporativo, data_nascimento, genero, fk_equipe, senha "
					+ " FROM usuario u WHERE u.email_corporativo = ? AND (u.removido IS NULL OR u.removido = 0) ";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, email.trim());
			
			ResultSet rs = stmt.executeQuery();
			Usuario usuario = null;
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmailCorporativo(rs.getString("email_corporativo"));
				usuario.setDocumento(rs.getString("documento"));
				usuario.setCelularCorporativo(rs.getString("celular_corporativo"));
				usuario.setDataNascimento(DataUtils.javaSQLTimestampToCalendar(rs.getTimestamp("data_nascimento")));
				usuario.setGenero(this.verifyDatabaseGenero(rs.getString("genero")));
				
				usuario.setSenha(rs.getString("senha"));
			}
			return usuario;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Usuarios ranking(ServiceRequest sr) {
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT id, nome, pontos, @row:=@row + 1 AS ranking "
					+ "FROM (SELECT    u.id,   u.nome,   SUM(hu.pontos) pontos FROM "
					+ "    (SELECT @row:=0) ranking,    historico_usuario hu        INNER JOIN "
					+ "    usuario u ON hu.fk_usuario = u.id WHERE    (u.removido IS NULL OR u.removido = 0) GROUP BY u.id"
					+ "  ORDER BY SUM(hu.pontos) DESC) asd");
				
			ResultSet rs = stmt.executeQuery();
			Collection<Usuarios.Usuario> usuarios = new ArrayList<Usuarios.Usuario>();
			while (rs.next()) {
				Usuarios.Usuario u = new Usuarios.Usuario();
				u.setId(rs.getLong(1));
				u.setNome(rs.getString(2));
				u.setPontos(rs.getInt(3));
				u.setRanking(rs.getInt(4));
				
				usuarios.add(u);
			}
			
			stmt.close();
			rs.close();
//			stmt = connection.prepareStatement("SELECT ranking, pontos FROM (SELECT u.id, u.nome, SUM(hu.pontos) pontos, @row:=@row + 1 AS ranking "
//					+ "FROM (SELECT @row:=0) ranking, historico_usuario hu INNER JOIN usuario u ON hu.fk_usuario = u.id "
//					+ "WHERE (u.removido IS NULL OR u.removido = 0) GROUP BY u.id ORDER BY SUM(hu.pontos) DESC) a WHERE id = ?");
//			stmt.setInt(1, sr.getIdUsuario());
//			rs = stmt.executeQuery();
			int minhaPosicao = 0;
			int meusPontos = 0;
			for (Usuarios.Usuario u : usuarios) {
				if (u.getId().intValue() == sr.getIdUsuario().intValue()) {
					minhaPosicao = u.getRanking();
					meusPontos = u.getPontos();
					break;
				}
			}
//			if(rs.next()){
//				minhaPosicao = rs.getInt(1);
//				meusPontos = rs.getInt(2);
//			}
			
			Collection<Usuarios.Usuario> usuarios10Itens = new ArrayList<Usuarios.Usuario>();
			int i = 0;
			for (Usuarios.Usuario u : usuarios) {
				if (i >= 10) {
					break;
				}
				usuarios10Itens.add(u);
				i++;
			}
			
			Usuarios result = new Usuarios(usuarios10Itens);
			result.setMeusPontos(meusPontos);
			result.setMinhaPosicao(minhaPosicao);
			
			return result;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public HistoricoUsuarioPaginado filtrar(HistoricoUsuarioFiltro huf) {
		String selectCount = "SELECT COUNT(DISTINCT hu.id) ";
		String select = "SELECT hu.id, hu.data, hu.fk_usuario, hu.fk_cliente, hu.fk_status, hu.pontos, s.id, s.descricao, c.id, c.nome, c.empresa, c.celular,"
				+ " c.email, c.logradouro, c.numero, c.bairro, c.cidade ";
	
		String selectFrom = " FROM historico_usuario hu ";
			   selectFrom += " LEFT JOIN usuario u ON hu.fk_usuario = u.id LEFT JOIN cliente c ON hu.fk_cliente = c.id LEFT JOIN status s ON hu.fk_status = s.id ";
			   
		String whereClause = " WHERE hu.fk_usuario = ? ";
//		String clauseBegin = " WHERE ";
		String orderBy = " ORDER BY hu.data DESC ";
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
//		whereClause += clauseBegin + " (hu.removido = 0 OR hu.removido IS NULL) ";
//		clauseBegin = " AND ";
		
//		String whereLocalidadeOferta = "";
//		if (ObjectUtils.isNotNullAndNotEmpty(huf.getUf()) && !huf.getUf().equals("0")) {
//			whereLocalidadeOferta += " AND loj.uf = '"+huf.getUf()+"' ";
//		}
		
		PreparedStatement stmt = null;
		int quantidadeRegistros = 0;
		int totalNegocioFechado = 0;
		int totalNegocioFechadoGeral = 0;
		int totalNegocioFechadoGeralMes = 0;
		int totalPontos = 0;
		int totalPontosMes = 0;
		int metaMes = 0;
		int metaMesNegocioFechado = 0;
		String mes = "";
		java.util.List<HistoricoUsuarioPaginado.HistoricoUsuario> itens = new ArrayList<HistoricoUsuarioPaginado.HistoricoUsuario>();
		
		try {
			String limit = " LIMIT " + huf.getLimit();
			String offset = " OFFSET " + ((huf.getOffset() * huf.getLimit()) - huf.getLimit());
			
			if (huf.getLimit() == 0){
				huf.setOffset(1);
				stmt = connection.prepareStatement(select + selectFrom + whereClause + orderBy);
			}else{
				stmt = connection.prepareStatement(select + selectFrom + whereClause + orderBy + limit + offset);
			}
			stmt.setInt(1, huf.getIdUsuario());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				HistoricoUsuarioPaginado.HistoricoUsuario hu = new HistoricoUsuarioPaginado.HistoricoUsuario();
				hu.setId(rs.getInt("hu.id"));
				hu.setData(DataUtils.javaSQLTimestampToCalendar(rs.getTimestamp("hu.data")));
				hu.setPontos(rs.getInt("hu.pontos"));
				
//				HistoricoUsuarioPaginado.HistoricoUsuario.Usuario hhu = new HistoricoUsuarioPaginado.HistoricoUsuario.Usuario();
//					hhu.setId(rs1.getInt("u.id"));
//					hhu.setNome(rs1.getString("u.nome"));
//				hu.setUsuario(hhu);
				
				HistoricoUsuarioPaginado.HistoricoUsuario.Cliente hhc = new HistoricoUsuarioPaginado.HistoricoUsuario.Cliente();
					hhc.setId(rs.getInt("c.id"));
					hhc.setNome(rs.getString("c.nome"));
					hhc.setEmpresa(rs.getString("c.empresa"));
					hhc.setCelular(rs.getString("c.celular"));
					hhc.setEmail(rs.getString("c.email"));
						HistoricoUsuarioPaginado.HistoricoUsuario.Cliente.Endereco hhce = new HistoricoUsuarioPaginado.HistoricoUsuario.Cliente.Endereco();
						hhce.setLogradouro(rs.getString("c.logradouro"));
						hhce.setNumero(rs.getString("c.numero"));
						hhce.setBairro(rs.getString("c.bairro"));
						HistoricoUsuarioPaginado.HistoricoUsuario.Cliente.Endereco.Cidade hhcec = new HistoricoUsuarioPaginado.HistoricoUsuario.Cliente.Endereco.Cidade();
							hhcec.setNome(rs.getString("c.cidade"));
						hhce.setCidade(hhcec);
					hhc.setEndereco(hhce);
				hu.setCliente(hhc);
				
				HistoricoUsuarioPaginado.HistoricoUsuario.Status hhs = new HistoricoUsuarioPaginado.HistoricoUsuario.Status();
				hhs.setId(rs.getInt("s.id"));
				hhs.setDescricao(rs.getString("s.descricao"));
				hu.setStatus(hhs);
				
				itens.add(hu);
			}
			
			stmt.close();
			stmt = connection.prepareStatement(selectCount + selectFrom + whereClause);
			stmt.setInt(1, huf.getIdUsuario());
			rs.close();
			rs = stmt.executeQuery();
			rs.next();
			quantidadeRegistros = rs.getInt(1);
			
			stmt.close();
			stmt = connection.prepareStatement("SELECT SUM(hu.pontos) FROM historico_usuario hu WHERE hu.fk_usuario = ?");
			stmt.setInt(1, huf.getIdUsuario());
			rs.close();
			rs = stmt.executeQuery();
			rs.next();
			totalPontos = rs.getInt(1);
			
//			stmt.close();
//			stmt = connection.prepareStatement("SELECT SUM(hu.pontos) FROM historico_usuario hu WHERE hu.fk_usuario = ? AND MONTH(hu.data) = MONTH(now())");
//			stmt.setInt(1, huf.getIdUsuario());
//			rs.close();
//			rs = stmt.executeQuery();
//			rs.next();
//			totalPontosMes = rs.getInt(1);
			
			stmt.close();
			stmt = connection.prepareStatement("SELECT COUNT(hu.id) FROM historico_usuario hu WHERE hu.fk_usuario = ? AND MONTH(hu.data) = MONTH(now()) AND hu.fk_status = 5");
			stmt.setInt(1, huf.getIdUsuario());
			rs.close();
			rs = stmt.executeQuery();
			rs.next();
			totalNegocioFechado = rs.getInt(1);
			
			stmt.close();
			stmt = connection.prepareStatement("SELECT COUNT(hu.id) FROM historico_usuario hu WHERE hu.fk_status = 5");
			rs.close();
			rs = stmt.executeQuery();
			rs.next();
			totalNegocioFechadoGeral = rs.getInt(1);
			
			stmt.close();
			stmt = connection.prepareStatement("SELECT COUNT(hu.id) FROM historico_usuario hu WHERE MONTH(hu.data) = MONTH(now()) AND hu.fk_status = 5");
			rs.close();
			rs = stmt.executeQuery();
			rs.next();
			totalNegocioFechadoGeralMes = rs.getInt(1);
			
			stmt.close();
			stmt = connection.prepareStatement("SELECT m.meta_mes, m.meta_mes_negocio_fechado, m.descricao FROM meta_mensal m WHERE m.mes = MONTH(now())");
//			stmt = connection.prepareStatement("SELECT c.valor FROM configuracao c WHERE c.chave = 'META_MES'");
			rs.close();
			rs = stmt.executeQuery();
			rs.next();
			metaMes = rs.getInt(1);
			metaMesNegocioFechado = rs.getInt(2);
			mes = rs.getString(3);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		HistoricoUsuarioPaginado pagina =  new HistoricoUsuarioPaginado(quantidadeRegistros, huf.getOffset(), huf.getLimit(), itens);
		pagina.setTotalPontos(totalPontos);
		pagina.setTotalPontosMes(totalPontosMes);
		pagina.setTotalNegocioFechado(totalNegocioFechado);
		pagina.setTotalNegocioFechadoGeral(totalNegocioFechadoGeralMes);
		pagina.setTotalNegocioFechadoGeralMes(totalNegocioFechadoGeralMes);
		pagina.setMetaMes(metaMes);
		pagina.setMetaMesNegocioFechado(metaMesNegocioFechado);
		pagina.setMes(mes);
		return pagina;
	}

}