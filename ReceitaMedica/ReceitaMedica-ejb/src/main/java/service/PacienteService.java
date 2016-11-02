package service;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import DAO.PacienteDAO;
import model.Paciente;

@Stateless
public class PacienteService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PacienteDAO pacienteDAO;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(Paciente paciente) throws Exception{
		pacienteDAO.salvar(paciente);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Paciente buscarPorCPF(String CPF) throws Exception{
		return pacienteDAO.buscarPorCPF(CPF);
	}
}
