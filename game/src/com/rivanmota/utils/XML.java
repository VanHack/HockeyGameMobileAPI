package com.rivanmota.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@SuppressWarnings("unchecked")
public class XML<T> {

	private T object;
	private static JAXBContext context;

	public XML(Class<T> clazz) {
		try {
			context = JAXBContext.newInstance(clazz);
		} catch (JAXBException e) {
			context = null;
		}
	}
	
	public T readFromString(String string) {
		try {
			Unmarshaller unmarshaller = context.createUnmarshaller();
			StringReader reader = new StringReader(string);
			object = (T) unmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			Logger.getAnonymousLogger().warning("[ERRO AO LER DA STRING]" + e.getMessage());
		} 
		
		return object;
	}

	public T readFromStream(InputStream stream) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
			Unmarshaller unmarshaller = context.createUnmarshaller();
			object = (T) unmarshaller.unmarshal(reader);
		} catch (IOException | JAXBException e) {
			Logger.getAnonymousLogger().warning("[ERRO AO LER DO STREAM]" + e.getMessage());
		} 
		return object;
	}

	public T readFromURL(URL url) {
		try {
			Unmarshaller unmarshaller = context.createUnmarshaller();
			object = (T) unmarshaller.unmarshal(url.openStream());
		} catch (JAXBException | IOException e) {
			Logger.getAnonymousLogger().warning("[ERRO AO LER DA STRING]" + e.getMessage());
		} 
		
		return object;
	}


	public T readFromFile(File file) {
		try {
			Unmarshaller unmarshaller = context.createUnmarshaller();
			object = (T) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			Logger.getAnonymousLogger().warning("[ERRO AO LER DA STRING]" + e.getMessage());
		} 
		
		return object;
	}
	
}
