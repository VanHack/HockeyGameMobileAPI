package com.rivanmota.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="historico-usuario-paginado")
@XmlAccessorType(XmlAccessType.FIELD)
public class HistoricoUsuarioPaginado {
	
	@XmlElement(name="historicoUsuarios")
	private Collection<HistoricoUsuario> historicoUsuarios;
	@XmlElement
	private int pagAnterior;
	@XmlElement
	private int pagProxima;
	@XmlElement
	private int tamanhoPagina;
	@XmlElement
	private int totalPaginas;
	@XmlElement
	private boolean anterior;
	@XmlElement
	private boolean proxima;
	@XmlElement
	private long quantidadeRegistros;
	@XmlElement
	private int paginaDesejada;
	@XmlElement
	private long totalNegocioFechado;
	@XmlElement
	private long totalNegocioFechadoGeral;
	@XmlElement
	private long totalNegocioFechadoGeralMes;
	@XmlElement
	private long totalPontos;
	@XmlElement
	private long totalPontosMes;
	@XmlElement
	private int metaMes;
	@XmlElement
	private int metaMesNegocioFechado;
	@XmlElement
	private String mes;
	
	public HistoricoUsuarioPaginado() {
	}
	
	public HistoricoUsuarioPaginado(long quantidadeRegistros, int paginaDesejada, int tamanhoPagina, Collection<HistoricoUsuario> historicoUsuarios) {
		super();
		this.quantidadeRegistros = quantidadeRegistros;
		this.paginaDesejada = paginaDesejada;
		this.tamanhoPagina = tamanhoPagina;
		this.historicoUsuarios = historicoUsuarios;
		
		if (tamanhoPagina != 0){
			totalPaginas = (int) Math.ceil( (double) quantidadeRegistros / tamanhoPagina);
		}

		if (paginaDesejada <= 1){
			setAnterior(false);	
		}else{
			setPagAnterior(paginaDesejada -1);
			setAnterior(true);
		}
		
		if (paginaDesejada + 1 <= totalPaginas){
			setProxima(true);
			setPagProxima(paginaDesejada + 1);
		}else{
			setProxima(false);
		}
	}

	public int getPagAnterior() {

		return pagAnterior;
	}

	public int getPagProxima() {
		return pagProxima;
	}

	public int getTamanhoPagina() {
		return tamanhoPagina;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public boolean isAnterior() {
		return anterior;
	}

	public boolean isProxima() {
		return proxima;
	}
	
	public long getQuantidadeRegistros() {
		return quantidadeRegistros;
	}

	protected void setAnterior(boolean anterior) {
		this.anterior = anterior;
	}

	protected void setPagAnterior(int pagAnterior) {
		this.pagAnterior = pagAnterior;
	}

	protected void setPagProxima(int pagProxima) {
		this.pagProxima = pagProxima;
	}

	protected void setProxima(boolean proxima) {
		this.proxima = proxima;
	}

	protected void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}
	
	public long getTotalNegocioFechado() {
		return totalNegocioFechado;
	}

	public void setTotalNegocioFechado(long totalNegocioFechado) {
		this.totalNegocioFechado = totalNegocioFechado;
	}

	public void setTotalNegocioFechado(int totalNegocioFechado) {
		this.totalNegocioFechado = totalNegocioFechado;
	}

	public long getTotalNegocioFechadoGeral() {
		return totalNegocioFechadoGeral;
	}

	public void setTotalNegocioFechadoGeral(long totalNegocioFechadoGeral) {
		this.totalNegocioFechadoGeral = totalNegocioFechadoGeral;
	}

	public long getTotalNegocioFechadoGeralMes() {
		return totalNegocioFechadoGeralMes;
	}

	public void setTotalNegocioFechadoGeralMes(long totalNegocioFechadoGeralMes) {
		this.totalNegocioFechadoGeralMes = totalNegocioFechadoGeralMes;
	}

	public long getTotalPontos() {
		return totalPontos;
	}

	public void setTotalPontos(long totalPontos) {
		this.totalPontos = totalPontos;
	}
 
	public long getTotalPontosMes() {
		return totalPontosMes;
	}

	public void setTotalPontosMes(long totalPontosMes) {
		this.totalPontosMes = totalPontosMes;
	}

	public int getMetaMes() {
		return metaMes;
	}

	public void setMetaMes(int metaMes) {
		this.metaMes = metaMes;
	}

	public int getMetaMesNegocioFechado() {
		return metaMesNegocioFechado;
	}

	public void setMetaMesNegocioFechado(int metaMesNegocioFechado) {
		this.metaMesNegocioFechado = metaMesNegocioFechado;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Collection<HistoricoUsuario> getHistoricoUsuarios() {
		return historicoUsuarios;
	}

	public void setHistoricoUsuarios(Collection<HistoricoUsuario> historicoUsuarios) {
		this.historicoUsuarios = historicoUsuarios;
	}

	@SuppressWarnings("unused")
	private boolean isListEqual(final List<HistoricoUsuario> a, final List<HistoricoUsuario> b) {
		if (a == b || a.equals(b))
			return true;

		final Iterator<HistoricoUsuario> ia = a.iterator();
		final Iterator<HistoricoUsuario> ib = b.iterator();
		while (ia.hasNext() && ib.hasNext()) {
			final Object oa = ia.next();
			final Object ob = ib.next();
			if (!oa.equals(ob)) {
				return false;
			}
		}
		if (ia.hasNext() || ib.hasNext()) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unused")
	private int listHashCode(final List<HistoricoUsuario> a) {
		int result = 0;
		for (Iterator<HistoricoUsuario> iterator = a.iterator(); iterator.hasNext();) {
			final Object o = iterator.next();
			result = 29 * result + o.hashCode();
		}
		return result;
	}

	public String toString() {
		
		final StringBuilder sb = new StringBuilder();
		sb.append("Pagina ").append(paginaDesejada)
		.append(" de ").append(totalPaginas);
		
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((historicoUsuarios == null) ? 0 : historicoUsuarios.hashCode());
		return result;
	}

	@Override
//	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistoricoUsuarioPaginado other = (HistoricoUsuarioPaginado) obj;
		if (historicoUsuarios == null) {
			if (other.historicoUsuarios != null)
				return false;
		} else if (!historicoUsuarios.equals(other.historicoUsuarios))
			return false;
		return true;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class HistoricoUsuario {

		@XmlElement
		private Integer id;
		
		@XmlElement
		private Usuario usuario;
		
		@XmlElement
		private Cliente cliente;
		
		@XmlElement
		private Status status;
		
		@XmlElement
		private Calendar data;
		
		@XmlElement
		private Integer pontos;
		
		
		@XmlAccessorType(XmlAccessType.FIELD)
		public static class Usuario {
			
			@XmlElement
			private Integer id;
			
			@XmlElement
			private String nome;
			
			public Integer getId() {
				return id;
			}

			public void setId(Integer id) {
				this.id = id;
			}

			public String getNome() {
				return nome;
			}

			public void setNome(String nome) {
				this.nome = nome;
			}

		}
		
		@XmlAccessorType(XmlAccessType.FIELD)
		public static class Cliente {
			
			@XmlElement
			private Integer id;

			@XmlElement
			private String nome;
			
			@XmlElement
			private String empresa;
			
			@XmlElement
			private String celular;
			
			@XmlElement
			private String email;
			
			@XmlElement
			private Endereco endereco;
			
			@XmlAccessorType(XmlAccessType.FIELD)
			public static class Endereco {
				
				@XmlElement
				private Integer id;

				@XmlElement
				private String logradouro;
				
				@XmlElement
				private String numero;
				
				@XmlElement
				private String bairro;
				
				@XmlElement
				private Cidade cidade;
				
				@XmlAccessorType(XmlAccessType.FIELD)
				public static class Cidade {
					
					@XmlElement
					private Integer id;

					@XmlElement
					private String nome;

					public Integer getId() {
						return id;
					}

					public void setId(Integer id) {
						this.id = id;
					}

					public String getNome() {
						return nome;
					}

					public void setNome(String nome) {
						this.nome = nome;
					}
					
				}

				public Integer getId() {
					return id;
				}

				public void setId(Integer id) {
					this.id = id;
				}

				public String getLogradouro() {
					return logradouro;
				}

				public void setLogradouro(String logradouro) {
					this.logradouro = logradouro;
				}

				public String getNumero() {
					return numero;
				}

				public void setNumero(String numero) {
					this.numero = numero;
				}

				public String getBairro() {
					return bairro;
				}

				public void setBairro(String bairro) {
					this.bairro = bairro;
				}

				public Cidade getCidade() {
					return cidade;
				}

				public void setCidade(Cidade cidade) {
					this.cidade = cidade;
				}

			}

			public Integer getId() {
				return id;
			}

			public void setId(Integer id) {
				this.id = id;
			}

			public String getNome() {
				return nome;
			}

			public void setNome(String nome) {
				this.nome = nome;
			}

			public String getEmpresa() {
				return empresa;
			}

			public void setEmpresa(String empresa) {
				this.empresa = empresa;
			}

			public String getCelular() {
				return celular;
			}

			public void setCelular(String celular) {
				this.celular = celular;
			}

			public String getEmail() {
				return email;
			}

			public void setEmail(String email) {
				this.email = email;
			}

			public Endereco getEndereco() {
				return endereco;
			}

			public void setEndereco(Endereco endereco) {
				this.endereco = endereco;
			}
			
		}
		
		@XmlAccessorType(XmlAccessType.FIELD)
		public static class Status {
			
			@XmlElement
			private Integer id;
			
			@XmlElement
			private String descricao;

			public Integer getId() {
				return id;
			}

			public void setId(Integer id) {
				this.id = id;
			}

			public String getDescricao() {
				return descricao;
			}

			public void setDescricao(String descricao) {
				this.descricao = descricao;
			}
			
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
	
		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		public Status getStatus() {
			return status;
		}

		public void setStatus(Status status) {
			this.status = status;
		}

		public Calendar getData() {
			return data;
		}

		public void setData(Calendar data) {
			this.data = data;
		}

		public Integer getPontos() {
			return pontos;
		}

		public void setPontos(Integer pontos) {
			this.pontos = pontos;
		}

		
	}

}
