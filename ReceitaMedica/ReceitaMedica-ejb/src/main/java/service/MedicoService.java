package service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import DAO.MedicoDAO;
import model.Medico;

@Stateless
public class MedicoService {
	
	@Inject
	private MedicoDAO medicoDAO;
	
	public void salvar(Medico medico) throws Exception{
		medicoDAO.salvar(medico);
	}
}
