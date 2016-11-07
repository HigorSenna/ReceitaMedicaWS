package rest;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import model.Medico;
import service.MedicoService;
import utils.MessagesWS;
import utils.ParamUtils;

@Path("/medico")
@ApplicationPath("/ServicoMedico")
public class MedicoRest extends Application implements Serializable{

	private static final long serialVersionUID = 1L;	

	private Medico medico;
	
	@Inject 
	MedicoService medicoService;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/medicos")
	public String buscarMedicos() throws Exception{
		List<Medico> medicosRetornados = medicoService.buscarTodos();	    
		return getJsonIdentado(medicosRetornados);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/medicoCRM")
	public String buscarMedico(@QueryParam(ParamUtils.CRM) String crm) throws Exception{
		Medico m = medicoService.buscarPorCrm(crm);
		return getJsonIdentado(m);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/criar")
	public MessagesWS criarMedico(@QueryParam(ParamUtils.NOME_MEDICO) String nomeMedico,@QueryParam(ParamUtils.CRM) String crm) {
		
		medico = new Medico();
		medico.setNmMedico(nomeMedico);
		medico.setCrmMedico(crm);
		MessagesWS mensagem = new MessagesWS();
		try {
			salvar(medico);
			mensagem.setMessage("Medico inserido");
			return mensagem;
		} catch (Exception e) {
			mensagem.setMessage("Falha ao salvar");
			return mensagem;
		}
	}	
	
	private void salvar(Medico medico) throws Exception {
		this.medicoService.salvar(medico);
	}
	
	private String getJsonIdentado(Object elemento) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true); 
	    return mapper.writeValueAsString(elemento);
	}
}
