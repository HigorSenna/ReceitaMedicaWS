package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.commons.ejb.dao.GenericoDAO;
import model.ReceitasMedica;

public class ReceitaDAO extends GenericoDAO<ReceitasMedica, Integer>{

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public List<ReceitasMedica> buscarPorCpfPaciente(String cpf) throws Exception{
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("SELECT receita ")
			.append("FROM ReceitasMedica receita ")
			.append("INNER JOIN FETCH receita.paciente paciente ")	
			.append("INNER JOIN FETCH receita.medico medico ")
			.append("WHERE paciente.cpfPaciente = ? ");
	
		
		Query query = getEntityManager().createQuery(stringBuilder.toString());
		query.setParameter(1, cpf);
		
		try {
			 List<ReceitasMedica> receitas = query.getResultList();
			 List<ReceitasMedica> receitasSemItens = new ArrayList<>();
			 
			 for(ReceitasMedica receita : receitas){
				 receita.setItensReceitas(null);
				 receita.getPaciente().setReceitasMedicas(null);
				 receita.getPaciente().setUsuario(null);
				 receita.getMedico().setReceitasMedicas(null);
				 receita.getMedico().setUsuario(null);
				 receitasSemItens.add(receita);
			 }
			
			 return receitasSemItens;
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
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
