package com.rivanmota.dto;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class VendasDTOPaginado {
	
	private Collection<VendasDTO> itens;
	private int pagAnterior;
	private int pagProxima;
	private int tamanhoPagina;
	private int totalPaginas;
	private boolean anterior;
	private boolean proxima;
	private long quantidadeRegistros;
	private int paginaDesejada;
	
	public VendasDTOPaginado() {
	}
	
	public VendasDTOPaginado(long quantidadeRegistros, int paginaDesejada, int tamanhoPagina, Collection<VendasDTO> itens) {
		super();
		this.quantidadeRegistros = quantidadeRegistros;
		this.paginaDesejada = paginaDesejada;
		this.tamanhoPagina = tamanhoPagina;
		this.itens = itens;
		
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
	
	public int getPaginaDesejada() {
		return paginaDesejada;
	}

	public Collection<VendasDTO> getItens() {
		return itens;
	}

	public void setItens(Collection<VendasDTO> itens) {
		this.itens = itens;
	}

	public void setQuantidadeRegistros(long quantidadeRegistros) {
		this.quantidadeRegistros = quantidadeRegistros;
	}

	@SuppressWarnings("unused")
	private boolean isListEqual(final List<VendasDTO> a, final List<VendasDTO> b) {
		if (a == b || a.equals(b))
			return true;

		final Iterator<VendasDTO> ia = a.iterator();
		final Iterator<VendasDTO> ib = b.iterator();
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
	private int listHashCode(final List<VendasDTO> a) {
		int result = 0;
		for (Iterator<VendasDTO> iterator = a.iterator(); iterator.hasNext();) {
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
		result = prime * result + ((itens == null) ? 0 : itens.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VendasDTOPaginado other = (VendasDTOPaginado) obj;
		if (itens == null) {
			if (other.itens != null)
				return false;
		} else if (!itens.equals(other.itens))
			return false;
		return true;
	}
	
}
