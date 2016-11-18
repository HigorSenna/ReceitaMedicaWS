package controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import VM.ConsultaReceitaVM;
import enums.TipoMensagemEnum;
import model.ItemReceita;
import model.Medico;
import model.Paciente;
import model.ReceitasMedica;
import utils.JsonUtils;
import utils.MessagesUtils;
import utils.ParamUtils;
import utils.UrlUtils;

@Named
@ViewScoped
public class ConsultaReceitaController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String CONSULTA_RECEITA_POR_NUMERO = "ServicoReceitaMedica/receita/buscarReceitaPorNumero";
	private static final String UTILIZAR_RECEITA = "ServicoReceitaMedica/receita/utilizarReceita";
	private static final String CANCELAR_RECEITA = "ServicoReceitaMedica/receita/cancelarReceita";
	
	@Inject
	private ConsultaReceitaVM consultaReceitaVM;
	
	private Client client;
	private WebResource webResource;
	private ClientResponse response;
	
	@PostConstruct
	public void init(){
		consultaReceitaVM.setItensReceita(new ArrayList<ItemReceita>());
	}
	
	public void cancelarReceita(){
		client = Client.create();
		String json = null;
		
		JsonObject jo = new JsonObject();
		jo.addProperty(ParamUtils.NUM_RECEITA, consultaReceitaVM.getNumReceita());
		
		webResource = client.resource(UrlUtils.getURL(CANCELAR_RECEITA ));
		response = webResource.type(MediaType.APPLICATION_JSON)
					.post(ClientResponse.class,jo.toString());
			
			json = response.getEntity(String.class);				
			System.out.println(json);
			JSONObject obj = JsonUtils.parseObject(json);
			MessagesUtils.exibirMensagemRedirect(obj.getString(ParamUtils.MESSAGE), "consultar.xhtml", TipoMensagemEnum.ALERTA);
	}
	
	public void utilizarReceita(){
		client = Client.create();
		String json = null;
		
		JsonObject jo = new JsonObject();
		jo.addProperty(ParamUtils.NUM_RECEITA, consultaReceitaVM.getNumReceita());
		
		webResource = client.resource(UrlUtils.getURL(UTILIZAR_RECEITA));
		response = webResource.type(MediaType.APPLICATION_JSON)
					.post(ClientResponse.class,jo.toString());
			
		json = response.getEntity(String.class);				
		System.out.println(json);
		JSONObject obj = JsonUtils.parseObject(json);
		MessagesUtils.exibirMensagemRedirect(obj.getString(ParamUtils.MESSAGE), "consultar.xhtml", TipoMensagemEnum.ALERTA);
	}
	
		
	public void buscarReceitaPorNumero(){
		client = Client.create();
		try {		
			String json = null;
			
			JsonObject jo = new JsonObject();
			jo.addProperty(ParamUtils.NUM_RECEITA, consultaReceitaVM.getNumReceita());
			
			webResource = client.resource(UrlUtils.getURL(CONSULTA_RECEITA_POR_NUMERO));
			response = webResource.type(MediaType.APPLICATION_JSON)
						.post(ClientResponse.class,jo.toString());
			
				json = response.getEntity(String.class);				
				System.out.println(json);
				
				montarReceitaRetornada(json);
		}
		catch (Exception e) {
			consultaReceitaVM.setReceita(new ReceitasMedica());
			consultaReceitaVM.setItensReceita(new ArrayList<ItemReceita>());
		}
	}
	
	private void montarReceitaRetornada(String json){
		JSONObject objGeral = JsonUtils.parseObject(json);
		
		Medico medico = getMedicoJson(objGeral);
		Paciente paciente = getPacienteJson(objGeral);
		
		JSONArray arrayItens = objGeral.getJSONArray(ParamUtils.ITENS_RECEITAS);
		
		consultaReceitaVM.getReceita().setMedico(medico);
		consultaReceitaVM.getReceita().setPaciente(paciente);
		
		for(Object j: arrayItens){
			JSONObject objeto = (JSONObject) j;
			
			ItemReceita item = montarItemReceita(objeto);
		
			consultaReceitaVM.getItensReceita().add(item);
		}
	}
	
	private ItemReceita montarItemReceita(JSONObject objeto){
		ItemReceita item = new ItemReceita();
		
		item.setContraIndicacao(objeto.getString(ParamUtils.CONTRA_INDICACAO));
		item.setRegAnvisa(objeto.getInt(ParamUtils.REG_ANVISA));
		item.setInstrucao(objeto.getString(ParamUtils.INSTRUCAO));
		item.setUso(String.valueOf(objeto.getInt(ParamUtils.REG_ANVISA)));
		item.setNmReceita(objeto.getString(ParamUtils.NM_RECEITA));
		
		return item;
	}
	
	private Paciente getPacienteJson(JSONObject objeto){
		Paciente paciente = new Paciente();
		
		JSONObject objPaciente = objeto.getJSONObject(ParamUtils.PACIENTE);
		paciente.setCpfPaciente(objPaciente.getString(ParamUtils.CPF));
		paciente.setNmPaciente(objPaciente.getString(ParamUtils.NOME_PACIENTE));
		
		return paciente;
	}
	
	private Medico getMedicoJson(JSONObject objeto){
		Medico medico = new Medico();
		
		JSONObject objMedico = objeto.getJSONObject(ParamUtils.MEDICO);
		medico.setCrmMedico(objMedico.getString(ParamUtils.CRM));
		medico.setNmMedico(objMedico.getString(ParamUtils.NOME_MEDICO));
		
		return medico;
	}

	public ConsultaReceitaVM getConsultaReceitaVM() {
		return consultaReceitaVM;
	}

	public void setConsultaReceitaVM(ConsultaReceitaVM consultaReceitaVM) {
		this.consultaReceitaVM = consultaReceitaVM;
	}
}
