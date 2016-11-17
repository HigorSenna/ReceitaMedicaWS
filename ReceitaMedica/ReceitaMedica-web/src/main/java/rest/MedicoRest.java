package rest;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.primefaces.json.JSONObject;

import model.Medico;
import service.MedicoService;
import utils.JsonUtils;
import utils.MessagesWS;
import utils.ParamUtils;
import utils.UsuarioUtils;

@Path("/medico")
@ApplicationPath("/ServicoMedico")
public class MedicoRest extends Application implements Serializable{

	private static final long serialVersionUID = 1L;	

	private Medico medico;
	
	@Inject 
	MedicoService medicoService;
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/cadastroMedico")
	public MessagesWS salvar(String jsonMedico){
		JSONObject obj = JsonUtils.parseObject(jsonMedico);
		Medico medicoParaSalvar = getMedico(obj);
		try {
			if(hasMedicoComCrm(medicoParaSalvar)){
				return new MessagesWS("O médico ja existe");
			}
			else{
				medicoService.salvar(medicoParaSalvar);
				return new MessagesWS("Médico inserido com sucesso");
			}
		} catch (Exception e) {
			return new MessagesWS("Falha ao inserir médico");
		}
	}
	private boolean hasMedicoComCrm(Medico medico) throws Exception{
		Medico med = medicoService.buscarPorCrm(medico.getCrmMedico());
		return med != null;
	}
	
	private Medico getMedico(JSONObject objeto){
		medico = new Medico();
		medico.setCrmMedico(objeto.getString(ParamUtils.CRM));
		medico.setNmMedico(objeto.getString(ParamUtils.NOME_MEDICO));
		
		medico.setUsuario(UsuarioUtils.getUsuarioMedico(medico));
		
		return medico;
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/medicos")
	public String buscarMedicos() throws Exception{
		List<Medico> medicosRetornados = medicoService.buscarTodos();	    
		return getJsonIdentado(medicosRetornados);
	}
	
	private String getJsonIdentado(Object elemento) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true); 
	    return mapper.writeValueAsString(elemento);
	}
}
