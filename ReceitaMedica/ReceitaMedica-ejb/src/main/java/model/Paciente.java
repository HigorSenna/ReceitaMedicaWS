package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="pacientes")
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CPF_PACIENTE")
	private int cpfPaciente;

	@Column(name="NM_PACIENTE")
	private String nmPaciente;

	@OneToMany(mappedBy="paciente")
	private List<ReceitasMedica> receitasMedicas;
	
	public Paciente(int cpfPaciente, String nmPaciente) {
		this.cpfPaciente = cpfPaciente;
		this.nmPaciente = nmPaciente;
	}

	public Paciente() {
	}

	public int getCpfPaciente() {
		return this.cpfPaciente;
	}

	public void setCpfPaciente(int cpfPaciente) {
		this.cpfPaciente = cpfPaciente;
	}

	public String getNmPaciente() {
		return this.nmPaciente;
	}

	public void setNmPaciente(String nmPaciente) {
		this.nmPaciente = nmPaciente;
	}

	public List<ReceitasMedica> getReceitasMedicas() {
		return this.receitasMedicas;
	}

	public void setReceitasMedicas(List<ReceitasMedica> receitasMedicas) {
		this.receitasMedicas = receitasMedicas;
	}

	public ReceitasMedica addReceitasMedica(ReceitasMedica receitasMedica) {
		getReceitasMedicas().add(receitasMedica);
		receitasMedica.setPaciente(this);

		return receitasMedica;
	}

	public ReceitasMedica removeReceitasMedica(ReceitasMedica receitasMedica) {
		getReceitasMedicas().remove(receitasMedica);
		receitasMedica.setPaciente(null);

		return receitasMedica;
	}

}