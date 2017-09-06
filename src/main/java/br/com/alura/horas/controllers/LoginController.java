package br.com.alura.horas.controllers;

import javax.inject.Inject;

import br.com.alura.horas.anotations.Open;
import br.com.alura.horas.dao.UsuarioDAO;
import br.com.alura.horas.model.Usuario;
import br.com.alura.horas.seguranca.UsuarioLogado;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class LoginController {

	private final UsuarioDAO usuarioDao;
	private final Validator validator;
	private UsuarioLogado usuarioLogado;
	private final Result result;

	public LoginController() {
		this(null, null, null, null);
	}

	@Inject
	public LoginController(UsuarioDAO usuarioDao, UsuarioLogado usuarioLogado, Result result, Validator validator) {
		this.usuarioDao = usuarioDao;
		this.result = result;
		this.validator = validator;
		this.usuarioLogado = usuarioLogado;

	}

	@Open
	public void form() {
	}

	@Post
	@Open
	public void autenticar(String login, String senha) {
		Usuario usuario = usuarioDao.busca(login, senha);
		if (usuario != null) {
			usuarioLogado.fazLogin(usuario);
			result.redirectTo(IndexController.class).index();
		} else {
			validator.add(new SimpleMessage("login_invalido", "Login ou senha incorretos"));
			validator.onErrorRedirectTo(this).form();
		}
	}

	@Open
	public void desloga() {
		usuarioLogado.desloga();
		result.redirectTo(this).form();
	}
}
