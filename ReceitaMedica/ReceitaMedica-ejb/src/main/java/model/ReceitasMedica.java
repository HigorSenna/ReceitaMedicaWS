package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="receitas_medicas")
public class ReceitasMedica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NUM_RECEITA")
	private int numReceita;

	@Temporal(TemporalType.DATE)
	private Date data;

	@Column(name="FL_STATUS")
	private String flStatus;

	@OneToMany(mappedBy="receitasMedica")
	private List<ItemReceita> itensReceitas;

	@ManyToOne
	@JoinColumn(name="CRM_MEDICO")
	private Medico medico;

	@ManyToOne
	@JoinColumn(name="CPF_PACIENTE")
	private Paciente paciente;

	public ReceitasMedica(Date data, String flStatus) {
		super();
		this.data = data;
		this.flStatus = flStatus;
	}

	public ReceitasMedica() {
	}

	public int getNumReceita() {
		return this.numReceita;
	}

	public void setNumReceita(int numReceita) {
		this.numReceita = numReceita;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getFlStatus() {
		return this.flStatus;
	}

	public void setFlStatus(String flStatus) {
		this.flStatus = flStatus;
	}

	public List<ItemReceita> getItensReceitas() {
		return this.itensReceitas;
	}

	public void setItensReceitas(List<ItemReceita> itemReceita) {
		this.itensReceitas = itemReceita;
	}

	public ItemReceita addItensReceita(ItemReceita itemReceita) {
		getItensReceitas().add(itemReceita);
		itemReceita.setReceitasMedica(this);

		return itemReceita;
	}

	public ItemReceita removeItensReceita(ItemReceita itemReceita) {
		getItensReceitas().remove(itemReceita);
		itemReceita.setReceitasMedica(null);

		return itemReceita;
	}

	public Medico getMedico() {
		return this.medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}