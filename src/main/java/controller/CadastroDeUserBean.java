package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.mindrot.jbcrypt.BCrypt;

import model.User;

@Named
@SessionScoped
public class CadastroDeUserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private User usuario = new User();
	private List<User> usuarios = new ArrayList<User>();
	private EntityManagerFactory emf;
	private EntityManager em;
	private Long userId;
	private String navegarParaPagina;

	public void init() {
		String userIdParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("userId");

		if (userIdParam != null) {
			Long userId = Long.parseLong(userIdParam);

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-persistence");
			EntityManager em = emf.createEntityManager();

			try {
				em.getTransaction().begin();
				User foundUser = em.find(User.class, userId);
				em.getTransaction().commit();
				usuario = foundUser;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				em.close();
				emf.close();
			}
		}

	}

	@SuppressWarnings("unchecked")
	public List<User> getUsuarios() {
		emf = Persistence.createEntityManagerFactory("jpa-persistence");
		em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			String jpql = "SELECT u FROM User u";
			Query query = em.createQuery(jpql);
			usuarios = query.getResultList();
			em.getTransaction().commit();
			return usuarios;
		} catch (Exception e) {

			e.printStackTrace();
			return Collections.emptyList();
		} finally {
			em.close();
			emf.close();
		}
	}

	public void setUsuarios(List<User> usuarios) {
		this.usuarios = usuarios;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public User getUsuario() {
		return usuario;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNavegarParaPagina() {
		return navegarParaPagina;
	}

	public void setNavegarParaPagina(String navegarParaPagina) {
		this.navegarParaPagina = navegarParaPagina;
	}

	public void deleteUser(Long id) {
		emf = Persistence.createEntityManagerFactory("jpa-persistence");
		em = emf.createEntityManager();
		try {
			em.getTransaction().begin();

			usuario = em.find(User.class, id);

			if (usuario != null) {
				em.remove(usuario);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário excluído com sucesso"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário não encontrado"));
			}

			em.getTransaction().commit();
		} catch (PersistenceException e) {

			e.printStackTrace();

			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {

			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}

	}

	public String cadastrar() {
		emf = Persistence.createEntityManagerFactory("jpa-persistence");
		em = emf.createEntityManager();
		try {

			String senhaHashed = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
			usuario.setSenha(senhaHashed);
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
			clear();
			return "index.xhtml?faces-redirect=true";
		} catch (PersistenceException e) {

			e.printStackTrace();

			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {

			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
		return "";

	}

	public String atualizarUser() {
		emf = Persistence.createEntityManagerFactory("jpa-persistence");
		em = emf.createEntityManager();
		try {
			if (usuario.getSenha() != null) {
				String senhaHashed = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
				usuario.setSenha(senhaHashed);
			}
			em.getTransaction().begin();

			usuario = em.merge(usuario);

			em.getTransaction().commit();

			return "index.xhtml?faces-redirect=true";
		} catch (PersistenceException e) {

			e.printStackTrace();

			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return null;
		} finally {

			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
	}

	public void clear() {
		this.usuario = new User();
	}

	public String navegarParaPagina() {
		clear();
		return "index.xhtml?faces-redirect=true";
	}

}
