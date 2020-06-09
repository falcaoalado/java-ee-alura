package br.com.casadocodigo.loja.confs;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class FacesContextProducer {

	/**
	 * A partir dessas duas anotações informo que é um objeto que 
	 * será injetado, e informo que seu escopo é request
	 */
	@RequestScoped
	@Produces
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
}
