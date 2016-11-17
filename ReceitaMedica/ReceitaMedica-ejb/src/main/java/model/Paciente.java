package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="pacientes")
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CPF_PACIENTE")
	private String cpfPaciente;

	@Column(name="NM_PACIENTE")
	private String nmPaciente;

	@JsonIgnore
	@OneToMany(mappedBy="paciente",fetch = FetchType.LAZY)
	private List<ReceitasMedica> receitasMedicas;
	
	@OneToOne(cascade = CascadeType.ALL,fetch= FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="LOGIN",referencedColumnName="LOGIN",insertable = true,updatable = true)
	private Usuario usuario;
	
	public Paciente(String cpfPaciente, String nmPaciente) {
		this.cpfPaciente = cpfPaciente;
		this.nmPaciente = nmPaciente;
	}

	public Paciente() {
	}

	public String getCpfPaciente() {
		return this.cpfPaciente;
	}

	public void setCpfPaciente(String cpfPaciente) {
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}