package com.rivanmota.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.rivanmota.utils.ObjectUtils;

public class Periodo {

	public enum Data { INICIO, FIM }
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private String dataInicio;
	private String dataFim;
	
	public Periodo() {
	}
	
	public Periodo(String dataInicio, String dataFim) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}
	
	public Periodo(Calendar dataInicio, Calendar dataFim) {
		this.dataInicio = dataInicio != null ? sdf.format(dataInicio.getTime()) : "";
		this.dataFim = dataFim != null ? sdf.format(dataFim.getTime()) : "";
	}	

	public String getDataInicio() {
		return dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}
	
	public boolean isNull() {
		return ObjectUtils.isNullOrEmpty(dataInicio) && ObjectUtils.isNullOrEmpty(dataFim);
	}
	
	public Calendar getDataAsCalendar(Data data) {
		Calendar calendar = Calendar.getInstance();
		try {
			if (data.equals(Data.INICIO)) {
				calendar.setTime(sdf.parse(dataInicio));
			} else if (data.equals(Data.FIM)) {
				calendar.setTime(sdf.parse(dataFim));
			}
		} catch (ParseException e) {
			return null;
		}
		return calendar;
	}

}
