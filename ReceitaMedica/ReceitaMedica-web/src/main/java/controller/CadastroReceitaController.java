package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import model.ItemReceita;
import model.Medico;
import model.ReceitasMedica;
import utils.JsonUtils;
import utils.UrlUtils;

@ViewScoped
@Named
public class CadastroReceitaController implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final String CADASTRO_RECEITA = "ServicoReceitaMedica/receita/cadastroReceita";
	
	@Inject
	private ReceitasMedica receitaMedica;
	
	private Client client;
	private WebResource webResource;
	private ClientResponse response;
	
	public ReceitasMedica getReceitaMedica() {
		return receitaMedica;
	}
	
	public void salvar(){
		client = Client.create();
		Medico m = new Medico();
		m.setCrmMedico("13218");
		ReceitasMedica receita = new ReceitasMedica();		
		receita.setItensReceitas(new ArrayList<ItemReceita>());
		
		receita.setNumReceita(185185);
		receita.setMedico(m);
		
		ItemReceita item = new ItemReceita();
		item.setContraIndicacao("Contraindicado em casos de suspetia de dengue");
		item.setIdItem(0);
		item.setInstrucao("Duas vezes ao dia");
		item.setNmReceita("Receita medica");
		
		receita.getItensReceitas().add(item);
	
		
		String json = JsonUtils.parseJson(receita);
		
		webResource = client.resource(UrlUtils.getURL(CADASTRO_RECEITA));
		response = webResource.type(MediaType.APPLICATION_JSON)
					.post(ClientResponse.class,json);
		
		System.out.println(json);
	}

	public void setReceitaMedica(ReceitasMedica receitaMedica) {
		this.receitaMedica = receitaMedica;
	}

}
