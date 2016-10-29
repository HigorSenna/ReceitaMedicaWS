package controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import enums.TipoMensagemEnum;
import service.ReceitaService;
import utils.MessagesUtils;

@ViewScoped
@Named
public class CadastroReceitaController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject 
	ReceitaService receitaService;
	
	public void conectar(){
		try {
			receitaService.conectar();
		} catch (IOException e) {
			MessagesUtils.exibirMensagemRedirect("Erro ao conectar", "login.xhtml", TipoMensagemEnum.ERRO);
		}
	}

}
