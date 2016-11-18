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
import model.ItemReceita;
import model.Medico;
import model.Paciente;
import model.ReceitasMedica;
import utils.JsonUtils;
import utils.ParamUtils;
import utils.UrlUtils;

@ViewScoped
@Named
public class CadastroReceitaController implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final String CADASTRO_RECEITA = "ServicoReceitaMedica/receita/cadastroReceita";
	private static final String BUSCAR_PACIENTE = "ServicoPaciente/paciente/buscarPacienteCPF";


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
		Medico m = new Medico();
		m.setCrmMedico("9nfa76");
		m.setNmMedico("NOME TESTE");
		
		Paciente p = new Paciente();
		p.setCpfPaciente("5924510wq");
		p.setNmPaciente("PACIENTE TESTE");		
		
		ReceitasMedica receita = new ReceitasMedica();		
		receita.setItensReceitas(new ArrayList<ItemReceita>());
		
		receita.setMedico(m);
		receita.setPaciente(p);
		
		ItemReceita item = new ItemReceita();
		item.setContraIndicacao("Contraindicado em casos de suspetia de dengue");
		item.setIdItem(0);
		item.setInstrucao("Duas vezes ao dia");
		item.setNmReceita("Receita medica");
		
		receita.getItensReceitas().add(item);
		
		item = new ItemReceita();
		item.setContraIndicacao("Contraindicacao");
		item.setIdItem(0);
		item.setInstrucao("quatro vezes ao dia");
		item.setNmReceita("Receita medica 2");
		
		receita.getItensReceitas().add(item);
	
		
		String json = JsonUtils.parseJson(receita);
		
		webResource = client.resource(UrlUtils.getURL(CADASTRO_RECEITA));
		response = webResource.type(MediaType.APPLICATION_JSON)
					.post(ClientResponse.class,json);
		
		json = response.getEntity(String.class);
		System.out.println(json);
	}

	public CadastroReceitaVM getCadastroReceitaVM() {
		return cadastroReceitaVM;
	}

	public void setCadastroReceitaVM(CadastroReceitaVM cadastroReceitaVM) {
		this.cadastroReceitaVM = cadastroReceitaVM;
	}
}
