package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import enums.TipoMensagemEnum;
import model.Medico;
import service.MedicoService;
import utils.MessagesUtils;
import utils.ParamUtils;
import utils.UrlUtils;

@ViewScoped
@Named
public class CadastroMedicoController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String CADASTRO_MEDICO = "ServicoMedico/medico/criar";
	private static final String BUSCAR_MEDICO = "ServicoMedico/medico/medicoCRM";
	
	@Inject
	private Medico medico;
	
	@Inject 
	MedicoService medicoService;
	
	private Client client;
	private WebResource webResource;
	private ClientResponse response;
	
	public void cadastrarMedico(){
		if(!isMedicoExistente()){
			client = Client.create();
			try {		
				client.resource(UrlUtils.getURL(CADASTRO_MEDICO))
						.queryParam(ParamUtils.NOME_MEDICO, medico.getNmMedico())
						.queryParam(ParamUtils.CRM, medico.getCrmMedico())
						.accept("application/json")
						.get(ClientResponse.class);
				
				MessagesUtils.exibirMensagemRedirect("Medico cadastrado com sucesso", "cadastro.xhtml", TipoMensagemEnum.SUCESSO);
				
			} catch (Exception e) {
				MessagesUtils.exibirMensagemRedirect("Falha ao cadastrar médico", "cadastro.xhtml", TipoMensagemEnum.ERRO);
			}
		}
		else{
			MessagesUtils.exibirMensagemRedirect("Médico ja existe", "cadastro.xhtml", TipoMensagemEnum.ERRO);
		}		
	}
	
	private boolean isMedicoExistente(){
		String json = null;
		client = Client.create();
		webResource = client.resource(UrlUtils.getURL(BUSCAR_MEDICO))
				.queryParam(ParamUtils.CRM, medico.getCrmMedico());		
		try {
			response = webResource.accept("application/json").get(ClientResponse.class);
			json = response.getEntity(String.class);			
		} catch (Exception e) {
			return true;
		}
		return !json.equals("null");
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
}
