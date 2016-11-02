package service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import DAO.MedicoDAO;
import model.Medico;

@Stateless
public class MedicoService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MedicoDAO medicoDAO;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(Medico medico) throws Exception{
		medicoDAO.salvar(medico);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Medico> buscarTodos() throws Exception{
		return medicoDAO.buscarTodos();
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Medico buscarPorCrm(int CRM) throws Exception{
		return medicoDAO.buscarPorCRM(CRM);
	}
}
