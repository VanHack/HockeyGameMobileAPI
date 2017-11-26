package com.rivanmota.readers;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import com.rivanmota.utils.XML;

@Provider
@Consumes("application/xml")
public class GenericReader<T extends Object> implements MessageBodyReader<T> {
	
	public GenericReader() {
	}
	
	@Override
	public boolean isReadable(Class<?> classe, Type type, Annotation[] annotations, javax.ws.rs.core.MediaType mediaType) {
		return true;
	}

	@Override
	public T readFrom(Class<T> classe, Type type, Annotation[] annotations, javax.ws.rs.core.MediaType mediaType, MultivaluedMap<String, String> map, InputStream is) throws IOException, WebApplicationException {
		return new XML<T>(classe).readFromStream(is);
	}

}
