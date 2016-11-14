package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.hibernate.Hibernate;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import model.ReceitasMedica;
import utils.JsonUtils;
import utils.ParamUtils;
import utils.UrlUtils;

@Named
@ViewScoped
public class ConsultaReceitaController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String CONSULTA_RECEITA_POR_NUMERO = "ServicoReceitaMedica/receita/buscarReceitaPorNumero";
	private static final String UTILIZAR_RECEITA = "ServicoReceitaMedica/receita/utilizarReceita";
	private static final String CANCELAR_RECEITA = "ServicoReceitaMedica/receita/cancelarReceita";
	
	private Client client;
	private WebResource webResource;
	private ClientResponse response;
	
	public void cancelarReceita(){
		client = Client.create();
		String numReceita= new String(); 
		numReceita = "{'numReceita' : '2'}";
		String json = numReceita;
		
		webResource = client.resource(UrlUtils.getURL(CANCELAR_RECEITA ));
		response = webResource.type(MediaType.APPLICATION_JSON)
					.post(ClientResponse.class,json);
			
			json = response.getEntity(String.class);				
			System.out.println(json);
	}
	
	public void utilizarReceita(){
		client = Client.create();
		String numReceita= new String(); 
		numReceita = "{'numReceita' : '1'}";
		String json = numReceita;
		
		webResource = client.resource(UrlUtils.getURL(UTILIZAR_RECEITA));
		response = webResource.type(MediaType.APPLICATION_JSON)
					.post(ClientResponse.class,json);
			
			json = response.getEntity(String.class);				
			System.out.println(json);
	}
	
		
	public void buscarReceitaPorNumero(){
		client = Client.create();
		try {		
			String numReceita= new String(); 
			numReceita = "{'numReceita' : '1'}";
			String json = numReceita;
			
			webResource = client.resource(UrlUtils.getURL(CONSULTA_RECEITA_POR_NUMERO));
			response = webResource.type(MediaType.APPLICATION_JSON)
						.post(ClientResponse.class,json);
				
				json = response.getEntity(String.class);				
				System.out.println(json);
		}
		catch (Exception e) {
		}
	
	}
	
	private String getJsonIdentado(Object elemento) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true); 
	    return mapper.writeValueAsString(elemento);
	}

}
