package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

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
				client.resource(UrlUtils.getURL(CADASTRO_PACIENTE))
							.queryParam(ParamUtils.NOME_PACIENTE, paciente.getNmPaciente())
							.queryParam(ParamUtils.CPF,paciente.getCpfPaciente())
							.accept("application/json")
							.get(ClientResponse.class);
				
				MessagesUtils.exibirMensagemRedirect("Paciente cadastrado com sucesso", "cadastro.xhtml", TipoMensagemEnum.SUCESSO);
			} catch (Exception e) {
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
		
		return !json.equals("null");
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}
