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

import model.Medico;
import service.MedicoService;
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
	public List<Medico> buscarMedicos() throws Exception{
		List<Medico> medicosRetornados = medicoService.buscarTodos();
		return medicosRetornados;
	}	
	
	@GET
	@Path("/criar")
	public void criarMedico(@QueryParam(ParamUtils.NOME_MEDICO) String nomeMedico,@QueryParam(ParamUtils.CRM) String crm) throws Exception{
		
		medico = new Medico();
		medico.setNmMedico(nomeMedico);
		medico.setCrmMedico(Integer.parseInt(crm));
		
		salvar(medico);
	}	
	
	private void salvar(Medico medico) throws Exception{
		this.medicoService.salvar(medico);
	}
}
