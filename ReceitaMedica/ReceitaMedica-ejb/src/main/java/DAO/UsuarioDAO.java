package DAO;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.commons.ejb.dao.GenericoDAO;
import model.Usuario;

public class UsuarioDAO extends GenericoDAO<Usuario, String>{	

	private static final long serialVersionUID = 1L;

	public Usuario buscarPor(String login, String senha) throws Exception{
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("SELECT usuario ")
			.append("FROM Usuario usuario ")
			.append("WHERE usuario.login = ? ")
			.append("AND usuario.senha = ? ");
		
		Query query = getEntityManager().createQuery(stringBuilder.toString());
		
		query.setParameter(1 , login);
		query.setParameter(2, senha);
		
		try {
			return (Usuario) query.getSingleResult();
		} catch (NoResultException no) {
			 return null;
		}
	}
}
