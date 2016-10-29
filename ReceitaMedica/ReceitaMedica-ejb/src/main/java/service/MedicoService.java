package service;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;

import DAO.MedicoDAO;
import model.Medico;

@Stateless
public class MedicoService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MedicoDAO medicoDAO;
	
	public void salvar(Medico medico) throws Exception{
		medicoDAO.salvar(medico);
	}
}
