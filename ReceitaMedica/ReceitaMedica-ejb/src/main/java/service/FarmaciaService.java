package service;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;

import DAO.FarmaciaDAO;
import model.Farmacia;

@Stateless
public class FarmaciaService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private FarmaciaDAO farmaciaDAO;
	
	public Farmacia buscarPorCNPJ(String CNPJ) throws Exception{
		return farmaciaDAO.buscarPorCNPJ(CNPJ);
	}
	
	public void salvar(Farmacia farmacia) throws Exception{
		farmaciaDAO.salvar(farmacia);
	}
	
}
