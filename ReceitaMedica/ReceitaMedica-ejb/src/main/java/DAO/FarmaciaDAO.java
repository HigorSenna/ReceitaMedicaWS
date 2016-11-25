package DAO;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.commons.ejb.dao.GenericoDAO;
import model.Farmacia;

public class FarmaciaDAO extends GenericoDAO<Farmacia, String>{

	
	private static final long serialVersionUID = 1L;
	
	public Farmacia buscarPorCNPJ(String CNPJ) throws Exception {

		String jpql = "SELECT f FROM Farmacia f WHERE f.cnpjFarmacia like :cnpjParam";
		Query q = (Query) getEntityManager().createQuery(jpql);
		q.setParameter("cnpjParam", CNPJ);

		try {
			Farmacia farmacia = (Farmacia) q.getSingleResult();
			return farmacia;
		} catch (NoResultException no) {
			return null;
		}
	}

}
