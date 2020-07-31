package br.com.casadocodigo.loja.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.casadocodigo.loja.models.Autor;

@FacesConverter("autorConverter")
public class AutorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) return null;
		
		System.out.println("Convertendo para objeto");
		
		Autor autor = new Autor();
		autor.setId(Long.parseLong(value));
		
		return autor;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) return null;

		Autor autor = (Autor) value;
		return autor.getId().toString();
	}

}
