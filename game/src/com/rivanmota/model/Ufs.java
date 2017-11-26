package com.rivanmota.model;

import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ufs")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ufs {

	@XmlElement(name="uf")
	private Collection<Uf> ufs;
	
	public Ufs() {
	}
	
	public Collection<Uf> getUfs() {
		return ufs;
	}

	public void setUfs(Collection<Uf> ufs) {
		this.ufs = ufs;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Uf {
	
		@XmlElement
		private Integer codigo;

		@XmlElement
		private String descricao;

		@XmlElement
		private String abreviatura;

		public Uf() {
		}

		public Integer getCodigo() {
			return codigo;
		}

		public void setCodigo(Integer codigo) {
			this.codigo = codigo;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getAbreviatura() {
			return abreviatura;
		}

		public void setAbreviatura(String abreviatura) {
			this.abreviatura = abreviatura;
		}

	}	
	
}