package rest;

import java.io.Serializable;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;

import model.Paciente;
import service.PacienteService;
import utils.ParamUtils;

@Path("/paciente")
@ApplicationPath("ServicoPaciente")
public class PacienteRest extends Application implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PacienteService pacienteService;
	
	private Paciente paciente;
	
	@GET
	@Path("/cadastroPaciente")
	public void salvar(@QueryParam(ParamUtils.CPF) String CPF,@QueryParam(ParamUtils.NOME_PACIENTE) String nomePaciente) throws Exception{
		paciente = new Paciente(CPF,nomePaciente);
		pacienteService.salvar(paciente);
	}
}
