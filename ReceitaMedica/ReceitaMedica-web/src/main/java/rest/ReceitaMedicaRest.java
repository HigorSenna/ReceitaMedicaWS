package rest;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import enums.StatusReceitaEnum;
import model.Medico;
import model.Paciente;
import model.ReceitasMedica;
import service.MedicoService;
import service.PacienteService;
import service.ReceitaService;
import utils.ParamUtils;

@Path("/ServicoReceitaMedica")
@ApplicationPath("/receita")
public class ReceitaMedicaRest extends Application implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ReceitaService receitaService;
	
	@Inject
	private PacienteService pacienteService;
	
	@Inject 
	private MedicoService medicoService;
	
	private ReceitasMedica receitaMedica;
	
	@GET
	@Path("/cadastroReceita")
	public void salvar(@QueryParam(ParamUtils.CRM)String crmMedico,@QueryParam(ParamUtils.CPF)String cpfPaciente) throws Exception{
		receitaService.salvar(getReceita(crmMedico, cpfPaciente));
	}
	
	private ReceitasMedica getReceita(String crmMedico,String cpfPaciente)throws Exception {
		receitaMedica = new ReceitasMedica(new Date(),StatusReceitaEnum.ATIVA.getValor());
		Medico medico = medicoService.buscarPorCrm(crmMedico);
		Paciente paciente = pacienteService.buscarPorCPF(cpfPaciente);
		
		receitaMedica.setMedico(medico);
		receitaMedica.setPaciente(paciente);
		
		return receitaMedica;
	}
	
	public String buscarTodas(@QueryParam(ParamUtils.NUM_RECEITA) int numReceita,@QueryParam("list")String lista) throws Exception{
		
//		JSONArray ja = new JSONArray(lista);
//		
//		JSONObject jo = ja.get(0);
//		
//		 obj.setId( jo.getInt("id") );
//		
//		List<String> json = lista;
//		return getJsonIdentado(json);
		return null;
		
	}
	
	private String getJsonIdentado(Object elemento) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true); 
	    return mapper.writeValueAsString(elemento);
	}
	
}
