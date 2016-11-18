package VM;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import model.ItemReceita;
import model.ReceitasMedica;

public class ConsultaReceitaVM implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ReceitasMedica receita;
	
	private List<ItemReceita> itensReceita;
	
	private int numReceita;

	public ReceitasMedica getReceita() {
		return receita;
	}

	public void setReceita(ReceitasMedica receita) {
		this.receita = receita;
	}

	public List<ItemReceita> getItensReceita() {
		return itensReceita;
	}

	public void setItensReceita(List<ItemReceita> itensReceita) {
		this.itensReceita = itensReceita;
	}

	public int getNumReceita() {
		return numReceita;
	}

	public void setNumReceita(int numReceita) {
		this.numReceita = numReceita;
	}
}
