package com.rivanmota.utils;

import java.util.UUID;

public final class StringUtils {

	public static String randomUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String getExtensaoArquivo(String completeFilename) {
		String extensionName = "";
		int posicaoUltimoPonto = completeFilename.lastIndexOf('.');

		// Pega a posicao do ponto da extensao para separar a extens�o.
		if (completeFilename.length() - posicaoUltimoPonto == 4
				|| completeFilename.length() - posicaoUltimoPonto == 5) {
			extensionName = completeFilename.substring(posicaoUltimoPonto + 1, completeFilename.length());
		}

		return extensionName;
	}

	public static String removerPontosETracosEBarraDoCPF_CNPJ(String cpfCnpj) {

		if (cpfCnpj == null || cpfCnpj.trim().isEmpty()) {
			return null;
		}

		return cpfCnpj.replaceAll("[.//-]", "").trim();
	}
}
