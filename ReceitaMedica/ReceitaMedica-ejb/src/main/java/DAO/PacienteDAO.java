package DAO;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.commons.ejb.dao.GenericoDAO;
import model.Paciente;

public class PacienteDAO extends GenericoDAO<Paciente, Integer> {

	private static final long serialVersionUID = 1L;

	public Paciente buscarPorCPF(int CPF) throws NoResultException {

		String jpql = "SELECT p FROM Paciente p WHERE p.cpfPaciente =:cpfParam";
		Query q = (Query) getEntityManager().createQuery(jpql);
		q.setParameter("cpfParam", CPF);

		return (Paciente) q.getSingleResult();
	}

}
