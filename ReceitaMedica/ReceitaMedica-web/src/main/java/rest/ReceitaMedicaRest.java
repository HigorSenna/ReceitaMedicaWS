package rest;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import org.hibernate.Hibernate;
import org.primefaces.json.JSONObject;

import enums.StatusReceitaEnum;
import model.ItemReceita;
import model.Medico;
import model.Paciente;
import model.ReceitasMedica;
import model.ReciboReceita;
import service.ItemReceitaService;
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
	
	@Inject
	private ItemReceitaService itemReceitaService;
	
	private ReceitasMedica receitaMedica;
	

	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/buscarReceitaPorNumero")
	public ReceitasMedica buscarPorNumero(String numReceita){
		JSONObject obj = JsonUtils.parseObject(numReceita);
		int numeroReceita = obj.getInt(ParamUtils.NUM_RECEITA);
		
		try {
			ReceitasMedica  receita = receitaService.buscarPorNumero(numeroReceita);
			List<ItemReceita> itensReceita = itemReceitaService.buscarPorNumReceita(numeroReceita);
			receita.setItensReceitas(new ArrayList<ItemReceita>());
			receita.getItensReceitas().addAll(itensReceita);
			
			System.out.println("receita : " + receita + "itensReceita " + itensReceita);
			return receita;
		} catch (Exception e) {
			return null;
		}
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/cadastroReceita")
	public ReciboReceita salvar(String jsonReceita){
		JSONObject obj = JsonUtils.parseObject(jsonReceita);
		ReceitasMedica receita = null;
		try {
			 receita = getReceitaMedicoPaciente(obj);		
			receitaService.salvar(receita);
			
			List<ItemReceita> itensReceita = getListItens(obj,receita);
			itemReceitaService.salvar(itensReceita);
			
			System.out.println(getJsonIdentado(receita));
			
			return new ReciboReceita(
					receita.getMedico().getCrmMedico(), 
					receita.getPaciente().getCpfPaciente(),
					receita.getNumReceita(), 
					new SimpleDateFormat("dd/MM/yyyy").format(receita.getData()), 
					ParamUtils.MSG_CADASTRO_RECEITA);
			
		} catch (Exception e) {
			
			return null; 
		}
	}
	
	private List<ItemReceita> getListItens(JSONObject objetoJson,ReceitasMedica receita){
		List<ItemReceita> itensReceita = new ArrayList<>();		
		ItemReceita item;
		for(Object j : objetoJson.getJSONArray("itensReceitas")){
			JSONObject jo = (JSONObject) j;
			
			item = new ItemReceita();
			item.setContraIndicacao(jo.getString(ParamUtils.CONTRA_INDICACAO));
			item.setRegAnvisa(jo.getInt(ParamUtils.REG_ANVISA));
			item.setInstrucao(jo.getString(ParamUtils.INSTRUCAO));
			item.setUso(String.valueOf(jo.getInt(ParamUtils.REG_ANVISA)));
			item.setNmReceita(jo.getString(ParamUtils.NM_RECEITA));
			
			item.setReceitasMedica(receita);
			
			itensReceita.add(item);
		}
		return itensReceita;
	}
	
	private ReceitasMedica getReceitaMedicoPaciente(JSONObject objeto){
		ReceitasMedica receita = new ReceitasMedica();
		
		receita.setData(new Date());
		receita.setMedico(getMedico(objeto));
		receita.setPaciente(getPaciente(objeto));
		receita.setFlStatus(StatusReceitaEnum.ATIVA.getValor());
		
		return receita;
		
	}
	
	private Medico getMedico(JSONObject objeto){
		Medico medico = new Medico();
		
		JSONObject medicoJson = objeto.getJSONObject(ParamUtils.MEDICO);		
		medico.setNmMedico(medicoJson.getString(ParamUtils.NOME_MEDICO));
		medico.setCrmMedico(medicoJson.getString(ParamUtils.CRM));
		
		return medico;
	}
	
	private Paciente getPaciente(JSONObject objeto){
		Paciente paciente = new Paciente();
		
		JSONObject pacienteJson = objeto.getJSONObject(ParamUtils.PACIENTE);
		paciente.setCpfPaciente(pacienteJson.getString(ParamUtils.CPF));
		paciente.setNmPaciente(pacienteJson.getString(ParamUtils.NOME_PACIENTE));
		
		return paciente;
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
