package br.com.casadocodigo.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.AutorDao;
import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.Autor;
import br.com.casadocodigo.loja.models.Livro;

@Named
@RequestScoped
public class AdminLivrosBean {

	private Livro livro = new Livro();
	private List<Long> autoresId = new ArrayList<Long>();

	@Inject
	private LivroDao dao;

	@Inject
	private AutorDao autorDao;

	@Inject
	private FacesContext context;

	@Transactional
	public String salvar() {
		System.out.println("Livro cadastrado: " + livro);

		for (Long autorId : autoresId) {
			livro.add(new Autor(autorId));
		}

		dao.salvar(livro);
		/**
		 * Pega o escopo de flash, que percorre desde o contexto onde surgiu a
		 * requisição até o redirecionamento (próx contexto)
		 */
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Livro adicionado com sucesso!"));

		return "/livros/lista?faces-redirect=true";
	}

	public List<Autor> getAutores() {
		return autorDao.listar();
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Long> getAutoresId() {
		return autoresId;
	}

	public void setAutoresId(List<Long> autoresId) {
		this.autoresId = autoresId;
	}

}
