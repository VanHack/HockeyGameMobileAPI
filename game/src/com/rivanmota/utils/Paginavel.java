package com.rivanmota.utils;

public interface Paginavel {
	
	public boolean isProxima();

	public boolean isAnterior();

	public int getTamanhoPagina();

	public int getPagAnterior();

	public int getPagProxima();

	public int getTotalPaginas();
	
	public long getQuantidadeRegistros();
}
