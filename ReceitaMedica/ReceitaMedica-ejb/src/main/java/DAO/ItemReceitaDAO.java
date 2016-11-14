package DAO;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.commons.ejb.dao.GenericoDAO;
import model.ItemReceita;

public class ItemReceitaDAO extends GenericoDAO<ItemReceita, Integer>{

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public List<ItemReceita> buscarPorNumeroReceita(int numReceita) throws Exception{
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("SELECT item ")
		.append("FROM ItemReceita item ")
		.append(" INNER JOIN FETCH item.receitasMedica receita ")
		.append("WHERE receita.numReceita = ? ");
		
		Query q = getEntityManager().createQuery(stringBuilder.toString());
		
		q.setParameter(1, numReceita);
		
		try {
			return (List<ItemReceita>) q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
