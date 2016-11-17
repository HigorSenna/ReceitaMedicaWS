package rest;

import java.io.Serializable;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.primefaces.json.JSONObject;

import model.Paciente;
import service.PacienteService;
import utils.JsonUtils;
import utils.MessagesWS;
import utils.ParamUtils;
import utils.UsuarioUtils;

@Path("/paciente")
@ApplicationPath("ServicoPaciente")
public class PacienteRest extends Application implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PacienteService pacienteService;
	
	private Paciente paciente;
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/cadastroPaciente")
	public MessagesWS salvar(String jsonPaciente){
		
		JSONObject obj = JsonUtils.parseObject(jsonPaciente);
		Paciente pacienteParaSalvar = getPaciente(obj);
		
		try {
			if(hasPacienteComCPF(pacienteParaSalvar)){
				return new MessagesWS("O paciente ja existe");
			}
			else{
				pacienteService.salvar(pacienteParaSalvar);
				return new MessagesWS("Paciente inserido com sucesso");
			}
		} catch (Exception e) {
			return new MessagesWS("Falha ao inserir paciente");
		}
	}
	private boolean hasPacienteComCPF(Paciente paciente) throws Exception{
		Paciente pac = pacienteService.buscarPorCPF(paciente.getCpfPaciente());
		return pac != null;
	}
	
	private Paciente getPaciente(JSONObject objeto){
		paciente = new Paciente();
		paciente.setCpfPaciente(objeto.getString(ParamUtils.CPF));
		paciente.setNmPaciente(objeto.getString(ParamUtils.NOME_PACIENTE));
		
		paciente.setUsuario(UsuarioUtils.getUsuarioPaciente(paciente));
		
		return paciente;
	}
	
	private String getJsonIdentado(Object elemento) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true); 
	    return mapper.writeValueAsString(elemento);
	}
}
