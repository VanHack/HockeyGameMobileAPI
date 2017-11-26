package com.rivanmota.template;

public class EsqueciSenhaTemplate extends TemplateAbstract {

	private static final String FOLDER = "usuario/";

	private static final String NOME_USUARIO = "#NOME_USUARIO#";
	private static final String SENHA = "#SENHA#";
	private static final String TOKEN = "#TOKEN#";
	private static final String SITE_ROOT = "#SITE_ROOT#";
	
	private String nomeUsuario;
	private String senha;

	private enum Images {
		
		LOGO("#LOGO#", "img-logo.png"),
		BTN_CLIQUE("#BTN_CLIQUE#", "btn_senha.jpg");
		
		private String key;
		private String value;
		
		private Images(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return this.key;
		}
		
		public String getValue() {
			return this.value;
		}
		
	}
	
	public EsqueciSenhaTemplate(String url) {
		super(url);
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getHTMLString() {
		String htmlTemplate = super.readTemplate("esqueci-senha.html");
		
		for (Images image : Images.values()) {
			htmlTemplate = htmlTemplate.replaceAll(image.getKey(), url + FOLDER + image.getValue());
		}
		htmlTemplate = htmlTemplate.replaceAll(NOME_USUARIO, this.nomeUsuario);
		htmlTemplate = htmlTemplate.replaceAll(SENHA, this.senha);
//		htmlTemplate = htmlTemplate.replaceAll(TOKEN, this.token);
		htmlTemplate = htmlTemplate.replaceAll(SITE_ROOT, this.getSite());
		
		return htmlTemplate;
	}
	
}

