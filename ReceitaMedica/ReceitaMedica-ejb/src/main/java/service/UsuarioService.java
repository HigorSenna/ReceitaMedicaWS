package service;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;

import DAO.UsuarioDAO;
import model.Usuario;

@Stateless
public class UsuarioService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject	
	private UsuarioDAO usuarioDAO;
	
	public Usuario buscarPor(String login,String senha) throws Exception{
		return usuarioDAO.buscarPor(login, senha);
	}
}
