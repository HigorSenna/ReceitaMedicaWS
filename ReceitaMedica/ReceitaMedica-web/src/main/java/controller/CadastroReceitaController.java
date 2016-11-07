package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.codehaus.jettison.json.JSONArray;

import com.google.gson.Gson;

import model.ReceitasMedica;

@ViewScoped
@Named
public class CadastroReceitaController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ReceitasMedica receitaMedica;
	
	public ReceitasMedica getReceitaMedica() {
		return receitaMedica;
	}
	
	public void salvar(){
		ReceitasMedica receita = new ReceitasMedica();
		receita.setNumReceita(185185);
		
		List<ReceitasMedica> r=  new ArrayList<>();
		r.add(receita);
		receita = new ReceitasMedica();
		receita.setNumReceita(6189619);
		r.add(receita);
		
		Gson gson = new Gson();
		String json = gson.toJson(r);
		
		
		System.out.println(json);
		
	}

	public void setReceitaMedica(ReceitasMedica receitaMedica) {
		this.receitaMedica = receitaMedica;
	}

}
