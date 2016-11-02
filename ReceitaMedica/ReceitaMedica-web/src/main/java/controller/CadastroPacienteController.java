package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import enums.TipoMensagemEnum;
import model.Paciente;
import service.PacienteService;
import utils.MessagesUtils;
import utils.ParamUtils;
import utils.UrlUtils;

@ViewScoped
@Named
public class CadastroPacienteController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final String CADASTRO_PACIENTE = "ServicoPaciente/paciente/cadastroPaciente";
	
	@Inject
	private PacienteService pacienteService;
	
	@Inject
	private Paciente paciente;
	
	private Client cliente;
	
	public void salvar(){
		if(!isPacienteExistene()){
			try {
				cliente =  ClientBuilder.newClient();
				cliente.target(UrlUtils.getURL(CADASTRO_PACIENTE))
				.queryParam(ParamUtils.NOME_PACIENTE, paciente.getNmPaciente())
				.queryParam(ParamUtils.CPF,paciente.getCpfPaciente())
				.request()
				.get();
				
				MessagesUtils.exibirMensagemRedirect("Paciente cadastrado com sucesso", "cadastro.xhtml", TipoMensagemEnum.SUCESSO);
			} catch (Exception e) {
				 MessagesUtils.exibirMensagemRedirect("Falha ao cadastrar paciente", "cadastro.xhtml", TipoMensagemEnum.ERRO);
			}
		}
		else{
			MessagesUtils.exibirMensagemRedirect("Paciente já existe", "cadastro.xhtml", TipoMensagemEnum.ERRO);
		}
	}
	
	private boolean isPacienteExistene() {
		Paciente pa;
		try {
			pa = pacienteService.buscarPorCPF(paciente.getCpfPaciente());
		} catch (Exception e) {
			return true;
		} 
		
		return pa != null;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}
