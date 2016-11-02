package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import enums.TipoMensagemEnum;
import model.Medico;
import utils.MessagesUtils;
import utils.ParamUtils;

@ViewScoped
@Named
public class CadastroMedicoController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Medico medico;
	
	public void cadastrarMedico(){
		Client cliente = ClientBuilder.newClient();
		try {
			cliente.target("http://localhost:10080/ReceitaMedica-web/ServicoMedico/medico/criar")
			.queryParam(ParamUtils.NOME_MEDICO, medico.getNmMedico())
			.queryParam(ParamUtils.CRM, medico.getCrmMedico())
			.request()
			.get();
			
			MessagesUtils.exibirMensagemRedirect("Medico cadastrado com sucesso", "cadastro.xhtml", TipoMensagemEnum.SUCESSO);
			
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
