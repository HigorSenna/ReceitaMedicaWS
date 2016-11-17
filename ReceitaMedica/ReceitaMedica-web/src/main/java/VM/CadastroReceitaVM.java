package VM;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import model.ItemReceita;
import model.ReceitasMedica;

public class CadastroReceitaVM implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ReceitasMedica receitaMedica;
	
	@Inject
	private ItemReceita itemReceita;
	
	private List<ItemReceita> itensReceita ;
	

	public ReceitasMedica getReceitaMedica() {
		return receitaMedica;
	}

	public void setReceitaMedica(ReceitasMedica receitaMedica) {
		this.receitaMedica = receitaMedica;
	}

	public List<ItemReceita> getItensReceita() {
		return itensReceita;
	}

	public void setItensReceita(List<ItemReceita> itensReceita) {
		this.itensReceita = itensReceita;
	}

	public ItemReceita getItemReceita() {
		return itemReceita;
	}

	public void setItemReceita(ItemReceita itemReceita) {
		this.itemReceita = itemReceita;
	}
}
