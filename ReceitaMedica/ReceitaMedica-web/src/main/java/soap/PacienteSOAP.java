package soap;

import java.io.Serializable;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import model.Paciente;
import service.PacienteService;

@Path("/pacienteSOAP")
@ApplicationPath("/ServicoPacienteSOAP")
public class PacienteSOAP extends Application implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private PacienteService pacienteService;
	
	private Paciente paciente;
	
	@POST
	@Produces({MediaType.APPLICATION_XML})
	@Path("/cadastroPacienteSOAP")
	public Paciente cadastroPacienteSOAP(Paciente pacienteSOAP){
		
		paciente = new Paciente();
		
		paciente.setNmPaciente(pacienteSOAP.getNmPaciente());
//		paciente.setCpfPaciente(cpfPaciente);
		
		try {
			if(hasPacienteComCPF(paciente)){
				return null;
			}
			else{
				pacienteService.salvar(paciente);
				return paciente;
			}
		} catch (Exception e) {
			return paciente;
		}
	}
	
	private boolean hasPacienteComCPF(Paciente paciente) throws Exception{
		Paciente pac = pacienteService.buscarPorCPF(paciente.getCpfPaciente());
		return pac != null;
	}
}
