package rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.primefaces.json.JSONObject;

import enums.StatusReceitaEnum;
import model.ItemReceita;
import model.Medico;
import model.Paciente;
import model.ReceitasMedica;
import service.MedicoService;
import service.PacienteService;
import service.ReceitaService;
import utils.JsonUtils;
import utils.ParamUtils;

@ApplicationPath("/ServicoReceitaMedica")
@Path("/receita")
public class ReceitaMedicaRest extends Application implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ReceitaService receitaService;
	
	@Inject
	private PacienteService pacienteService;
	
	@Inject 
	private MedicoService medicoService;
	
	private ReceitasMedica receitaMedica;
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/cadastroReceita")
	public void salvar(String jsonReceita) throws Exception{
		JSONObject obj = JsonUtils.parseObject(jsonReceita);
		
//		receitaService.salvar(getReceita(crmMedico, cpfPaciente));
		System.out.println(obj);
		
	}
	
	private List<ItemReceita> getListItens(JSONObject objetoJson){
		List<ItemReceita> itensReceita = new ArrayList<>();
		ItemReceita item;
		for(Object j : objetoJson.getJSONArray("itensReceitas")){
			JSONObject jo = (JSONObject) j;
			item = new ItemReceita();
//			aa
//			item.setContraIndicacao();
		}
		return null;
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
