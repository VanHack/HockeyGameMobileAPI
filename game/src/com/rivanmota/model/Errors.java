package com.rivanmota.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.rivanmota.utils.ObjectUtils;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="errors")
public class Errors {
	
	@XmlElement(name="erro")
	private Collection<Erro> errors;
	
	public void addErro(Erro erro) {
		if(ObjectUtils.isCollectionNullOrEmpty(this.errors)) {
			this.errors = new ArrayList<Erro>();
		}
		this.errors.add(erro);
	}
	
	public Collection<Erro> getErrors() {
		return this.errors;
	}
	
	public boolean hasErrors() {
		if(!ObjectUtils.isCollectionNullOrEmpty(this.errors) && !this.errors.isEmpty()) {
			return true;
		}
		return false;
	}

}