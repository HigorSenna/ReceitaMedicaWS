package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import enums.TipoMensagemEnum;
import model.Paciente;
import utils.MessagesUtils;
import utils.ParamUtils;
import utils.UrlUtils;

@ViewScoped
@Named
public class CadastroPacienteController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final String CADASTRO_PACIENTE = "ServicoPaciente/paciente/cadastroPaciente";
	private static final String BUSCAR_PACIENTE = "ServicoPaciente/paciente/pacienteCPF";
	
	@Inject
	private Paciente paciente;
	
	private Client client;
	private WebResource webResource;
	private ClientResponse response;
	
	public void salvar(){
		if(!isPacienteExistente()){
	        client = Client.create();
			try {

				Gson gson = new Gson();
				String json = gson.toJson(paciente);
				webResource = client.resource(UrlUtils.getURL(CADASTRO_PACIENTE));
				response = webResource.type(MediaType.APPLICATION_JSON)
							.post(ClientResponse.class,json);
				
				MessagesUtils.exibirMensagemRedirect("Paciente cadastrado com sucesso", "cadastro.xhtml", TipoMensagemEnum.SUCESSO);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				 MessagesUtils.exibirMensagemRedirect("Falha ao cadastrar paciente", "cadastro.xhtml", TipoMensagemEnum.ERRO);
			}
		}
		else{
			MessagesUtils.exibirMensagemRedirect("Paciente já existe", "cadastro.xhtml", TipoMensagemEnum.ERRO);
		}
	}
	
	private boolean isPacienteExistente() {
		String json = null;
		client = Client.create();
		webResource = client.resource(UrlUtils.getURL(BUSCAR_PACIENTE))
				.queryParam(ParamUtils.CPF, paciente.getCpfPaciente());	
		
		try {
			response = webResource.accept("application/json").get(ClientResponse.class);
			json = response.getEntity(String.class);
		} catch (Exception e) {
			return true;
		} 
		
		//JSONObject jsonObj = new JSONObject(json);

		 // Get 'libraryname' ...

		 //String libraryName = jsonObj.getString(ParamUtils.NOME_PACIENTE);

		 // Get 'mymusic' details ...

		 /*final JSONArray music = json.getJSONArray("mymusic");
		 final JSONObject entry = music.get(0);
		 final String song = entry.getString("song");*/
		
		return !json.equals("null");
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}
