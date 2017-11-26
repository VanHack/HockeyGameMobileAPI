package com.rivanmota.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class TemplateAbstract {

	protected static final String FALE_CONOSCO = "#FALE_CONOSCO#";
	protected static final String DUVIDAS_FREQUENTES = "#DUVIDAS_FREQUENTES#";
	protected static final String SOBRE_CLUBE = "#SOBRE_CLUBE#";
	protected static final String SITE = "#SITE#";
	protected static final String PRODUTOS_BRADESCO_SEGUROS = "#PRODUTOS_BRADESCO_SEGUROS#";
	
	protected String url;
	private String site;

	public TemplateAbstract(String url) {
		this.url = url;
	}
	
	protected String readTemplate(String filename) {
		StringBuffer templateHTML = new StringBuffer();
		
		URL url = TemplateAbstract.class.getResource("/br/com/gameconecta/template/html/" + filename);
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
			String linha = reader.readLine();
			while (linha != null) {
				templateHTML.append(linha);
				linha = reader.readLine();
			}
			return getTemplateComURLsDefault(templateHTML.toString());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String getTemplateComURLsDefault(String htmlTemplate) {
		htmlTemplate = htmlTemplate.replaceAll(SITE, getSite());
		return htmlTemplate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
	
}
