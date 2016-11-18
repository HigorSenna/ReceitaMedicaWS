package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

import org.primefaces.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import enums.TipoMensagemEnum;
import model.Medico;
import utils.JsonUtils;
import utils.MessagesUtils;
import utils.ParamUtils;
import utils.UrlUtils;

@ViewScoped
@Named
public class CadastroMedicoController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String CADASTRO_MEDICO = "ServicoMedico/medico/cadastroMedico";
	
	@Inject
	private Medico medico;
	
	private Client client;
	private WebResource webResource;
	private ClientResponse response;
	
	public void cadastrarMedico() {

		client = Client.create();
		try {
			String json = JsonUtils.parseJson(medico);

			webResource = client.resource(UrlUtils.getURL(CADASTRO_MEDICO));
			response = webResource.type(MediaType.APPLICATION_JSON)
					.post(ClientResponse.class, json);

			json = response.getEntity(String.class);
			System.out.println(json);
			
			JSONObject obj = JsonUtils.parseObject(json);
			
			MessagesUtils.exibirMensagemRedirect(obj.getString(ParamUtils.MESSAGE), "cadastro.xhtml", TipoMensagemEnum.SUCESSO);

		} catch (Exception e) {
			MessagesUtils.exibirMensagemRedirect("Falha ao cadastrar médico", "cadastro.xhtml", TipoMensagemEnum.ERRO);
		}
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
}
