package service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import DAO.ItemReceitaDAO;
import model.ItemReceita;

@Stateless
public class ItemReceitaService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ItemReceitaDAO itemReceitaDAO;
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ItemReceita> buscarPorNumReceita(int numReceita) throws Exception{
		return itemReceitaDAO.buscarPorNumeroReceita(numReceita);
	}
	
	public void salvar(List<ItemReceita> itens) throws Exception{
		for(ItemReceita item : itens){
			itemReceitaDAO.salvar(item);
		}
	}
}
