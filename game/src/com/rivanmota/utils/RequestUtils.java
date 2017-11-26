package com.rivanmota.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

	/**
	 * Retorna o ip de acesso do cliente que está realizando a solicitação
	 * @param request
	 * @return ip de onde está vindo a requisição.
	 */
	public static String getIpDeAcesso(HttpServletRequest request) {
		if (ObjectUtils.isNotNullAndNotEmpty(request.getHeader("x-forwarded-for")))
			return request.getHeader("x-forwarded-for");
		return request.getRemoteAddr();
	}
	
	public static String getUserAgent(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}
	
}
