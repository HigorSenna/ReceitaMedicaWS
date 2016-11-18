package controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

import org.primefaces.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import VM.CadastroReceitaVM;
import enums.StatusReceitaEnum;
import enums.TipoMensagemEnum;
import model.ItemReceita;
import model.Medico;
import model.Paciente;
import model.ReceitasMedica;
import model.ReciboReceita;
import utils.JsonUtils;
import utils.MessagesUtils;
import utils.ParamUtils;
import utils.UrlUtils;

@ViewScoped
@Named
public class CadastroReceitaController implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final String CADASTRO_RECEITA = "ServicoReceitaMedica/receita/cadastroReceita";
	private static final String BUSCAR_PACIENTE = "ServicoPaciente/paciente/buscarPacienteCPF";
	private static final String BUSCAR_MEDICO = "ServicoMedico/medico/buscarMedicoCRM";


	@Inject
	private CadastroReceitaVM cadastroReceitaVM;
	
	private Client client;
	private WebResource webResource;
	private ClientResponse response;
	
	@PostConstruct
	public void init(){
		cadastroReceitaVM.setItensReceita(new ArrayList<ItemReceita>());
	}
	
	public void addItemReceita(){
		ItemReceita item = cadastroReceitaVM.getItemReceita();
		cadastroReceitaVM.getItensReceita().add(item);
		cadastroReceitaVM.setItemReceita(new ItemReceita());
	}
	
	public void getMedicoCRM(){
		if(cadastroReceitaVM.getMedico() != null){
			client = Client.create();
			
			String json = JsonUtils.parseJson(cadastroReceitaVM.getMedico());
			
			webResource = client.resource(UrlUtils.getURL(BUSCAR_MEDICO));
			response = webResource.type(MediaType.APPLICATION_JSON)
						.post(ClientResponse.class,json);
			try {
				json = response.getEntity(String.class);
				
				JSONObject obj= JsonUtils.parseObject(json);
				
				Medico medico = new Medico();
				medico.setCrmMedico(obj.getString(ParamUtils.CRM));
				medico.setNmMedico(obj.getString(ParamUtils.NOME_MEDICO));
				
				cadastroReceitaVM.setMedico(medico);
				
			} catch (Exception e) {
				cadastroReceitaVM.setMedico(new Medico());
			}
		}
	}
	
	public void getPacienteCPF(){
		if(cadastroReceitaVM.getPaciente() != null){
			client = Client.create();
			
			String json = JsonUtils.parseJson(cadastroReceitaVM.getPaciente());
			
			webResource = client.resource(UrlUtils.getURL(BUSCAR_PACIENTE));
			response = webResource.type(MediaType.APPLICATION_JSON)
						.post(ClientResponse.class,json);
			try {
				json = response.getEntity(String.class);
				
				JSONObject obj= JsonUtils.parseObject(json);
				
				Paciente paciente = new Paciente();
				paciente.setCpfPaciente(obj.getString(ParamUtils.CPF));
				paciente.setNmPaciente(obj.getString(ParamUtils.NOME_PACIENTE));
				
				cadastroReceitaVM.setPaciente(paciente);
				
			} catch (Exception e) {
				cadastroReceitaVM.setPaciente(new Paciente());
			}
		}	
	}	
	
	public void salvar(){
		client = Client.create();
				
		ReceitasMedica receita = new ReceitasMedica();		
		receita.setItensReceitas(new ArrayList<ItemReceita>());
		
		receita.setItensReceitas(cadastroReceitaVM.getItensReceita());
		receita.setFlStatus(StatusReceitaEnum.ATIVA.getValor());
		receita.setMedico(cadastroReceitaVM.getMedico());
		receita.setPaciente(cadastroReceitaVM.getPaciente());
				
		String json = JsonUtils.parseJson(receita);
		
		webResource = client.resource(UrlUtils.getURL(CADASTRO_RECEITA));
		response = webResource.type(MediaType.APPLICATION_JSON)
					.post(ClientResponse.class,json);
		
		json = response.getEntity(String.class);
		JSONObject obj = JsonUtils.parseObject(json);
		ReciboReceita recibo = new ReciboReceita(
				obj.getString(ParamUtils.CRM),
				obj.getString(ParamUtils.CPF),
				obj.getInt(ParamUtils.NUM_RECEITA),
				obj.getString(ParamUtils.DATA_RECEITA), 
				obj.getString(ParamUtils.MSG)
			);

		MessagesUtils.exibirMensagemRedirect(getMessageRecibo(recibo), "cadastro.xhtml", TipoMensagemEnum.SUCESSO);
		System.out.println(json);
	}
	private String getMessageRecibo(ReciboReceita recibo){
		StringBuilder sb = new StringBuilder();
		sb.append("CRM: \n")
		.append(recibo.getCrmMedico() + "\n")
		.append("CPF: ")
		.append(recibo.getCpfPaciente() + "\n")
		.append("Data: ").append(recibo.getData() + "\n")
		.append(recibo.getMsg());
		
		return sb.toString();
	}

	public CadastroReceitaVM getCadastroReceitaVM() {
		return cadastroReceitaVM;
	}

	public void setCadastroReceitaVM(CadastroReceitaVM cadastroReceitaVM) {
		this.cadastroReceitaVM = cadastroReceitaVM;
	}
}
