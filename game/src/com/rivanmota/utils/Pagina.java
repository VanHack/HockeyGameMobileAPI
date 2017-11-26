package com.rivanmota.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Pagina<T> implements Paginavel {

	private Collection<T> itens;
	private int pagAnterior;
	private int pagProxima;
	private int tamanhoPagina;
	private int totalPaginas;
	private boolean anterior;
	private boolean proxima;
	private long quantidadeRegistros;
	private int paginaDesejada;

	public Pagina(long quantidadeRegistros, int paginaDesejada, int tamanhoPagina, Collection<T> itens) {
		super();
		this.quantidadeRegistros = quantidadeRegistros;
		this.paginaDesejada = paginaDesejada;
		this.tamanhoPagina = tamanhoPagina;
		this.itens = itens;

		totalPaginas = (int) Math.ceil((double) quantidadeRegistros / tamanhoPagina);

		if (paginaDesejada <= 1) {
			setAnterior(false);
		} else {
			setPagAnterior(paginaDesejada - 1);
			setAnterior(true);
		}

		if (paginaDesejada + 1 <= totalPaginas) {
			setProxima(true);
			setPagProxima(paginaDesejada + 1);
		} else {
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

	public Collection<T> getItens() {
		return itens;
	}

	@SuppressWarnings("unused")
	private boolean isListEqual(final List<T> a, final List<T> b) {
		if (a == b || a.equals(b))
			return true;

		final Iterator<T> ia = a.iterator();
		final Iterator<T> ib = b.iterator();
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
	private int listHashCode(final List<T> a) {
		int result = 0;
		for (Iterator<T> iterator = a.iterator(); iterator.hasNext();) {
			final Object o = iterator.next();
			result = 29 * result + o.hashCode();
		}
		return result;
	}

	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result + ((itens == null) ? 0 : listHashCode(itens));
	// result = prime * result
	// + (int) (quantidadeRegistros ^ (quantidadeRegistros >>> 32));
	// result = prime * result + tamanhoPagina;
	// result = prime * result + totalPaginas;
	// return result;
	// }
	//
	// public boolean equals(Object obj) {
	// if (this == obj)
	// return true;
	// if (obj == null)
	// return false;
	// if (getClass() != obj.getClass())
	// return false;
	// @SuppressWarnings("unchecked")
	// Pagina<T> other = (Pagina<T>) obj;
	// if (itens == null) {
	// if (other.itens != null)
	// return false;
	// } else if (!isListEqual(itens, other.itens))
	// return false;
	// if (quantidadeRegistros != other.quantidadeRegistros)
	// return false;
	// if (tamanhoPagina != other.tamanhoPagina)
	// return false;
	// if (totalPaginas != other.totalPaginas)
	// return false;
	// return true;
	// }

	public String toString() {

		final StringBuilder sb = new StringBuilder();
		sb.append("Pagina ").append(paginaDesejada).append(" de ").append(totalPaginas);

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
	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagina other = (Pagina) obj;
		if (itens == null) {
			if (other.itens != null)
				return false;
		} else if (!itens.equals(other.itens))
			return false;
		return true;
	}

}
