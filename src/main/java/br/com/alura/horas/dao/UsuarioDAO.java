package br.com.alura.horas.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.alura.horas.model.Usuario;

@RequestScoped
public class UsuarioDAO {
	private EntityManager manager;

	public UsuarioDAO() {
	}

	@Inject
	public UsuarioDAO(EntityManager manager) {
		this.manager = manager;
	}

	public void adiciona(Usuario usuario) {
		manager.getTransaction().begin();
		manager.persist(usuario);
		manager.getTransaction().commit();
	}

	public List<Usuario> lista() {
		String jpql = "select u from Usuario u";
		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
		return query.getResultList();
	}

	public Usuario busca(String login, String senha){
		try{
			String jpql = "select u from Usuario u where u.login = :login and u.senha = :senha";
			TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			Usuario usuario = query.getSingleResult();
			return usuario;
		}catch (NoResultException e) {
			
		}
		return null;
	}
}
