package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import enums.TipoMensagemEnum;
import model.Paciente;
import utils.JsonUtils;
import utils.MessagesUtils;
import utils.UrlUtils;

@ViewScoped
@Named
public class CadastroPacienteController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final String CADASTRO_PACIENTE = "ServicoPaciente/paciente/cadastroPaciente";
	
	@Inject
	private Paciente paciente;
	
	private Client client;
	private WebResource webResource;
	private ClientResponse response;
	
	public void cadastrarPaciente() {

		client = Client.create();
		
		try {
			String json = JsonUtils.parseJson(paciente);

			webResource = client.resource(UrlUtils.getURL(CADASTRO_PACIENTE));
			response = webResource.type(MediaType.APPLICATION_JSON)
					.post(ClientResponse.class, json);

			json = response.getEntity(String.class);
			System.out.println(json);

		} catch (Exception e) {
			MessagesUtils.exibirMensagemRedirect("Falha ao cadastrar médico", "cadastro.xhtml", TipoMensagemEnum.ERRO);
		}
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}
