package DAO;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.commons.ejb.dao.GenericoDAO;
import model.ReceitasMedica;

public class ReceitaDAO extends GenericoDAO<ReceitasMedica, Integer>{

	private static final long serialVersionUID = 1L;
	
	public ReceitasMedica buscarPorNumero(int numReceita) throws Exception{
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
					.append("SELECT receita ")
					.append("FROM ReceitasMedica receita ")
					.append("INNER JOIN FETCH receita.medico ")
					.append("INNER JOIN FETCH receita.paciente ")				
					.append("WHERE receita.numReceita = ? ");
		
		Query q = getEntityManager().createQuery(stringBuilder.toString());
		q.setParameter(1, numReceita);
		
		try {
			ReceitasMedica receita = (ReceitasMedica) q.getSingleResult(); 
			receita.setItensReceitas(null);
			return receita;
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public ReceitasMedica atualizarRetornando(ReceitasMedica receita) throws Exception{
		return getEntityManager().merge(receita);
	}
}
