package model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;

@Entity
@Table(name="medicos")
@XmlRootElement
public class Medico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CRM_MEDICO")
	private String crmMedico;

	@Column(name="NM_MEDICO")
	private String nmMedico;
	
	@JsonIgnore
	@OneToMany(mappedBy="medico",fetch=FetchType.LAZY)
	private List<ReceitasMedica> receitasMedicas;
	
	@OneToOne(cascade = CascadeType.ALL,fetch= FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="LOGIN", referencedColumnName="LOGIN",insertable = true , updatable = true)
	private Usuario usuario;

	public Medico() {
	}

	public String getCrmMedico() {
		return this.crmMedico;
	}

	public void setCrmMedico(String crmMedico) {
		this.crmMedico = crmMedico;
	}

	public String getNmMedico() {
		return this.nmMedico;
	}

	public void setNmMedico(String nmMedico) {
		this.nmMedico = nmMedico;
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