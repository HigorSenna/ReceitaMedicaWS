package DAO;


import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.commons.ejb.dao.GenericoDAO;
import model.Medico;

public class MedicoDAO extends GenericoDAO<Medico, Integer>{

	private static final long serialVersionUID = 1L;
	
	public Medico buscarPorCRM(String CRM) throws Exception{
		
		String jpql = "SELECT m FROM Medico m WHERE m.crmMedico like :crmParam";
		Query q = (Query) getEntityManager().createQuery(jpql);
		q.setParameter("crmParam", CRM);
		
		 try {
			 Medico medico = (Medico) q.getSingleResult();
			return medico;
		} catch (NoResultException no) {
			return null;
		}
	}
}
