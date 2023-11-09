package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.User;

@Named
@SessionScoped
public class CadastroDeUserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private User usuario = new User();
	private List<User> usuarios = new ArrayList<User>();

    public List<User> getUsuarios() {
		return usuarios;
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

    public String cadastrar() {
        usuarios.add(usuario);
        System.out.println("Usuario " + usuario.getNome() + " cadastrado com sucesso");
        System.out.println("Usuario " + usuario.getEmail() + " cadastrado com sucesso");
        clear();
        return "";
    }
    
    public void redirecionar(String pagina) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
    }
    
    public void clear() {
    	this.usuario = new User();
    }
}
