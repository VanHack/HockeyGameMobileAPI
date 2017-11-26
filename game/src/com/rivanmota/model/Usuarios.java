package com.rivanmota.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="usuarios")
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuarios {

	@XmlElement(name="usuarios")
	private Collection<Usuario> usuarios;
	
	@XmlElement
	private Integer meusPontos;
	
	@XmlElement
	private Integer minhaPosicao;
	
	public Usuarios() {
	}
	
	public Usuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Usuario {
		@XmlElement
		private Long id;
		
		@XmlElement
		private String nome;
		
		@XmlElement
		private Integer pontos;
		
		@XmlElement
		private Integer ranking;
		
		public Usuario() {
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public Integer getPontos() {
			return pontos;
		}

		public void setPontos(Integer pontos) {
			this.pontos = pontos;
		}

		public Integer getRanking() {
			return ranking;
		}

		public void setRanking(Integer ranking) {
			this.ranking = ranking;
		}
		
	}

	public Integer getMeusPontos() {
		return meusPontos;
	}

	public void setMeusPontos(Integer meusPontos) {
		this.meusPontos = meusPontos;
	}

	public Integer getMinhaPosicao() {
		return minhaPosicao;
	}

	public void setMinhaPosicao(Integer minhaPosicao) {
		this.minhaPosicao = minhaPosicao;
	}
	
}
