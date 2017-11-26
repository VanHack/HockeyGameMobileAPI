package com.rivanmota.dao;

import com.rivanmota.model.HistoricoUsuarioFiltro;
import com.rivanmota.model.HistoricoUsuarioPaginado;
import com.rivanmota.model.ServiceRequest;
import com.rivanmota.model.Usuario;
import com.rivanmota.model.Usuarios;

public interface UsuarioDAO {

	boolean cadastrar(Usuario usuario);
	boolean update(Usuario usuario);
	Usuario getUsuarioByEmailAndSenha(String email, String senha);
	Usuario getUsuarioByEmail(String email);
	Usuarios ranking(ServiceRequest sr);
	HistoricoUsuarioPaginado filtrar(HistoricoUsuarioFiltro huf);
	Usuario findById(Integer id);
	Usuario updateAndReturn(Usuario usuario);
	Usuario findByEmailCorporativo(String emailCorporativo);
	
}