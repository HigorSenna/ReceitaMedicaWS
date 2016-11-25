package rest;

import java.io.Serializable;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.primefaces.json.JSONObject;

import model.Farmacia;
import service.FarmaciaService;
import utils.JsonUtils;
import utils.MessagesWS;
import utils.ParamUtils;
import utils.UsuarioUtils;

@Path("/farmacia")
@ApplicationPath("/ServicoFarmacia")
public class FarmaciaRest extends Application implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private FarmaciaService farmaciaService;
	
	private Farmacia farmacia;	
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/cadastroFarmacia")
	public MessagesWS salvar(String jsonFarmacia){
		JSONObject obj = JsonUtils.parseObject(jsonFarmacia);
		Farmacia farmaciaParaSalvar = getFarmacia(obj);
		try {
			if(hasFarmaciaComCNPJ(farmaciaParaSalvar)){
				return new MessagesWS("A farmacia ja existe");
			}
			else{
				farmaciaService.salvar(farmaciaParaSalvar);
				return new MessagesWS("Farmacia inserida com sucesso");
			}
		} catch (Exception e) {
			return new MessagesWS("Falha ao inserir farmácia");
		}
	}
	
	private Farmacia getFarmacia(JSONObject objeto){
		farmacia = new Farmacia();
		farmacia.setCnpjFarmacia(objeto.getString(ParamUtils.CNPJ_FARMACIA));
		farmacia.setNmFarmacia(objeto.getString(ParamUtils.NOME_FARMACIA));
		
		farmacia.setUsuario(UsuarioUtils.getUsuarioFarmacia(farmacia));
		
		return farmacia;
	}
	
	private boolean hasFarmaciaComCNPJ(Farmacia farmacia) throws Exception{
		Farmacia far = farmaciaService.buscarPorCNPJ(farmacia.getCnpjFarmacia());
		return far != null;
	}	
}
