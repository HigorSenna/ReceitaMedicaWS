package utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import enums.TipoMensagemEnum;

public class MessagesUtils {
	
	private static void manterMensagemAoRedirecionar(){
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);		
	}
	
	public static void exibirMensagemRedirect(String msg,String paginaRedirecionar,TipoMensagemEnum tipoMensagem){
		FacesMessage formatoMensagem;
		
		if(tipoMensagem.getValor().equals("S")){
			formatoMensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,"",msg);	
		}
		else if(tipoMensagem.getValor().equals("E")){
			formatoMensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR,"",msg);		
		}
		else{
			formatoMensagem = new FacesMessage(FacesMessage.SEVERITY_WARN,"",msg);
		}
			
		manterMensagemAoRedirecionar();
		 FacesContext.getCurrentInstance().addMessage(null,formatoMensagem);
		redirecionarPara(paginaRedirecionar);
	}
	
	public static void redirecionarPara(String pagina){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}