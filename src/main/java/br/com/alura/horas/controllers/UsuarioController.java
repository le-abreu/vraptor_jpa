package br.com.alura.horas.controllers;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.alura.horas.dao.UsuarioDAO;
import br.com.alura.horas.model.Usuario;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class UsuarioController {
	private Result result;

	private UsuarioDAO usuarioDAO;

	private Validator validator;

	public UsuarioController() {
	}

	@Inject
	public UsuarioController(Result result, UsuarioDAO usuarioDAO, Validator validator) {
		this.result = result;
		this.usuarioDAO = usuarioDAO;
		this.validator = validator;
	}


	@Get("/usuario/form")
	public void form() {
	}

	@IncludeParameters
	@Post
	public void adiciona(@Valid Usuario usuario) {
		validator.onErrorRedirectTo(this).form();
		usuarioDAO.adiciona(usuario);
		result.redirectTo(this).lista();
	}

	@Get("/usuario/lista")
	public void lista() {
		result.include("usuarios", usuarioDAO.lista());
	}
}
