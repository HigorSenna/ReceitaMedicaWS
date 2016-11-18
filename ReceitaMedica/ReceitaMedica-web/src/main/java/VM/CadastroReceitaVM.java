package VM;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import model.ItemReceita;
import model.Medico;
import model.Paciente;
import model.ReceitasMedica;

public class CadastroReceitaVM implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ReceitasMedica receitaMedica;
	
	@Inject
	private ItemReceita itemReceita;
	
	@Inject
	private Paciente paciente;
	
	@Inject
	private Medico medico;
	
	private List<ItemReceita> itensReceita ;

	public ReceitasMedica getReceitaMedica() {
		return receitaMedica;
	}

	public void setReceitaMedica(ReceitasMedica receitaMedica) {
		this.receitaMedica = receitaMedica;
	}

	public ItemReceita getItemReceita() {
		return itemReceita;
	}

	public void setItemReceita(ItemReceita itemReceita) {
		this.itemReceita = itemReceita;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<ItemReceita> getItensReceita() {
		return itensReceita;
	}

	public void setItensReceita(List<ItemReceita> itensReceita) {
		this.itensReceita = itensReceita;
	}
}
