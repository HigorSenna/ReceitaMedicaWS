package service;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReceitaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public void conectar() throws IOException{
		
		URL url = new URL("http://localhost:10080/ReceitaMedica-web/servico");		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.connect();
	}
}
