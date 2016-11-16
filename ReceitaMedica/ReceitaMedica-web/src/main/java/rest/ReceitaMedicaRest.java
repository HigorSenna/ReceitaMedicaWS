package rest;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
import utils.MessagesWS;
import utils.ParamUtils;

@ApplicationPath("/ServicoReceitaMedica")
@Path("/receita")
public class ReceitaMedicaRest extends Application implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ReceitaService receitaService;
	
	@Inject
	private ItemReceitaService itemReceitaService;
	
	@Inject
	private MedicoService medicoService;
	
	@Inject
	private PacienteService pacienteService;
	
	private ReceitasMedica receitaMedica;
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/cancelarReceita")
	public MessagesWS cancelarReceita(String numReceita){
		JSONObject obj = JsonUtils.parseObject(numReceita);
		int numeroReceita = obj.getInt(ParamUtils.NUM_RECEITA);
		
		try {
			receitaMedica = receitaService.buscarPorNumero(numeroReceita);
			if(isReceitaValida(receitaMedica)){
				receitaMedica.setFlStatus(StatusReceitaEnum.CANCELADA.getValor());
				receitaService.atualizarReceita(receitaMedica);
			}
			else{
				return new MessagesWS("Essa receita ja foi utilizada ou cancelada");
			}
			
			return new MessagesWS("Receita cancelada com aucesso!!");
		} catch (Exception e) {
			return new MessagesWS("Falha ao cancelar receita!!");
		}
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/utilizarReceita")
	public MessagesWS utilizarReceita(String numReceita){
		JSONObject obj = JsonUtils.parseObject(numReceita);
		int numeroReceita = obj.getInt(ParamUtils.NUM_RECEITA);
		
		try {
			receitaMedica = receitaService.buscarPorNumero(numeroReceita);
			if(isReceitaValida(receitaMedica)){
				receitaMedica.setFlStatus(StatusReceitaEnum.UTILIZADA.getValor());
				receitaService.atualizarReceita(receitaMedica);
			}
			else{
				return new MessagesWS("Essa receita ja foi utilizada ou cancelada");
			}
			
			return new MessagesWS("Receita utilizada com sucesso!!");
		} catch (Exception e) {
			return new MessagesWS("Falha ao utilizar receita!!");
		}
	}
	
	private boolean isReceitaValida(ReceitasMedica receita){
		if(StatusReceitaEnum.CANCELADA.getValor().equals(receita.getFlStatus()) || StatusReceitaEnum.UTILIZADA.getValor().equals(receita.getFlStatus())){
			return false;
		}
		return true;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/buscarReceitaPorNum")
	public ReceitasMedica buscarPorNum(@QueryParam("numReceita") String nuReceita){
		JSONObject obj = JsonUtils.parseObject(nuReceita);
		int numeroReceita = obj.getInt(ParamUtils.NUM_RECEITA);
		
		try {
			receitaMedica = receitaService.buscarPorNumero(numeroReceita);
			if(receitaMedica != null){
				List<ItemReceita> itensReceita = itemReceitaService.buscarPorNumReceita(numeroReceita);
				receitaMedica.setItensReceitas(itensReceita);
			}
			
			return receitaMedica;
		} catch (Exception e) {
			return null;
		}
	}

	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/buscarReceitaPorNumero")
	public ReceitasMedica buscarPorNumero(String numReceita){	
		Gson gson = new GsonBuilder().create();
		String sytr=gson.fromJson(numReceita, String.class);
		
		JsonUtils.parseJson(numReceita);
		JSONObject obj = JsonUtils.parseObject(sytr);
		int numeroReceita = obj.getInt(ParamUtils.NUM_RECEITA);
		
		try {
			receitaMedica = receitaService.buscarPorNumero(numeroReceita);
			if(receitaMedica != null){
				List<ItemReceita> itensReceita = itemReceitaService.buscarPorNumReceita(numeroReceita);
				receitaMedica.setItensReceitas(itensReceita);
			}
			
			return receitaMedica;
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
			 if(hasPacienteCadastrado(getPaciente(obj)) || hasMedicoCadastrado(getMedico(obj))){
				 receita = receitaService.atualizarRetornando(receita);
			 }
			 else{
				 receitaService.salvar(receita);
			 }
			
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
	
	private ReceitasMedica getReceitaMedicoPaciente(JSONObject objeto) throws Exception{
		ReceitasMedica receita = new ReceitasMedica();
		
		receita.setData(new Date());
		receita.setMedico(getMedico(objeto));
		receita.setPaciente(getPaciente(objeto));
		receita.setFlStatus(StatusReceitaEnum.ATIVA.getValor());
		
		return receita;
		
	}
	
	private Medico getMedico(JSONObject objeto) throws Exception{
		Medico medico = null;
		JSONObject medicoJson = objeto.getJSONObject(ParamUtils.MEDICO);		
		
		medico = new Medico();
		medico.setNmMedico(medicoJson.getString(ParamUtils.NOME_MEDICO));
		medico.setCrmMedico(medicoJson.getString(ParamUtils.CRM));
	
		return medico;
	}
	
	private Paciente getPaciente(JSONObject objeto) throws Exception{
		Paciente paciente = new Paciente();
		
		JSONObject pacienteJson = objeto.getJSONObject(ParamUtils.PACIENTE);
		paciente.setCpfPaciente(pacienteJson.getString(ParamUtils.CPF));
		paciente.setNmPaciente(pacienteJson.getString(ParamUtils.NOME_PACIENTE));
		
		return paciente;
	}
	
	
	private String getJsonIdentado(Object elemento) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true); 
	    return mapper.writeValueAsString(elemento);
	}
	
	private boolean hasPacienteCadastrado(Paciente paciente) throws Exception{
		return pacienteService.buscarPorCPF(paciente.getCpfPaciente()) != null;
	}
	
	private boolean hasMedicoCadastrado(Medico medico) throws Exception{
		return medicoService.buscarPorCrm(medico.getCrmMedico()) != null;
	}
	
}
