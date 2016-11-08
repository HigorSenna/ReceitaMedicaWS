package rest;

import java.io.Serializable;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.primefaces.json.JSONObject;

import model.Paciente;
import service.PacienteService;
import utils.JsonUtils;
import utils.ParamUtils;

@Path("/paciente")
@ApplicationPath("ServicoPaciente")
public class PacienteRest extends Application implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PacienteService pacienteService;
	
	private Paciente paciente;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/cadastroPaciente")
	public void salvar(String pa) throws Exception{
		
		JSONObject jo = JsonUtils.parseObject(pa);
		paciente = new Paciente(jo.getString(ParamUtils.CPF),jo.getString(ParamUtils.NOME_PACIENTE));
		
//		List<String> json = lista;
//		return getJsonIdentado(json);
//		paciente = new Paciente(CPF,nomePaciente);
//		pacienteService.salvar(paciente);
		System.out.println(paciente);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/pacienteCPF")
	public String buscarPaciente(@QueryParam(ParamUtils.CPF)String CPF) throws Exception{
		Paciente pac = pacienteService.buscarPorCPF(CPF);
		return getJsonIdentado(pac);
	}
	
	private String getJsonIdentado(Object elemento) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true); 
	    return mapper.writeValueAsString(elemento);
	}
}
