package com.rivanmota.constants;

public enum MediaType {

	JSON(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=UTF-8"),
	XML(javax.ws.rs.core.MediaType.APPLICATION_XML + ";charset=UTF-8");
	
	private String mediaType;
	
	private MediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
	public String getMediaType() {
		return this.mediaType;
	}
	
	public static String getMediaTypePorString(String mediaType) {
		for (MediaType mt : MediaType.values()) {
			if (mt.name().toLowerCase().equals(mediaType.toLowerCase())) return mt.getMediaType();
		}
		return "plain/text";
	}
	
}
