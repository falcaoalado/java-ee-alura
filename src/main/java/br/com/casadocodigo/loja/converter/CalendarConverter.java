package br.com.casadocodigo.loja.converter;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Calendar.class)
public class CalendarConverter implements Converter {
	
	private DateTimeConverter converter;
	
	public CalendarConverter() {
		converter = new DateTimeConverter();
		converter.setPattern("dd/MM/yyyy");
		converter.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Date date = (Date) converter.getAsObject(context, component, value);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return calendar;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value == null) return null;
		
		Calendar calendar = (Calendar) value;
		return converter.getAsString(context, component, calendar.getTime());
	}

}
