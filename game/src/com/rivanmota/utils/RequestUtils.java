package com.rivanmota.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

	/**
	 * Retorna o ip de acesso do cliente que est� realizando a solicita��o
	 * @param request
	 * @return ip de onde est� vindo a requisi��o.
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
