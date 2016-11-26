package rest;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.primefaces.json.JSONObject;

import enums.StatusItemEnum;
import model.ItemReceita;
import service.ItemReceitaService;
import utils.JsonUtils;
import utils.MessagesWS;
import utils.ParamUtils;

@Path("/itemReceita")
@ApplicationPath("/ServicoItemReceita")
public class ItemReceitaRest extends Application implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ItemReceitaService itemReceitaService;
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/venderItemReceita")
	public MessagesWS venderItem(String numItem){
		
		JSONObject obj = JsonUtils.parseObject(numItem);		
		
		try {
			ItemReceita item =  itemReceitaService.buscarPor(obj.getInt(ParamUtils.ID_ITEM));
			item.setStatus(StatusItemEnum.VENDIDO.getValor());
			
			itemReceitaService.atualizarItem(item);
			return new MessagesWS("Item vendido com sucesso!!");
			
		} catch (Exception e) {
			return new MessagesWS("Falha ao vender item!!");
		}
	}
	
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/buscarItensPorNumeroReceita")
	public List<ItemReceita> buscarPorNumReceita(String numReceita){
		
		JSONObject obj = JsonUtils.parseObject(numReceita);
		
		try {
			return itemReceitaService.buscarPorNumReceita(obj.getInt(ParamUtils.NUM_RECEITA));
		} catch (Exception e) {
			return null;
		}
	}
}
