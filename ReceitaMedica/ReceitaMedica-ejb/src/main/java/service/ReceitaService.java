package service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import DAO.ReceitaDAO;
import model.ReceitasMedica;

@Stateless
public class ReceitaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ReceitaDAO receitaDAO;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(ReceitasMedica receita) throws Exception{
		receitaDAO.salvar(receita);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ReceitasMedica> buscarTodas() throws Exception{
		return receitaDAO.buscarTodos();
	}
}
