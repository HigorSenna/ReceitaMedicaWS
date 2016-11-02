package model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

@Entity
@Table(name="medicos")
@XmlRootElement
public class Medico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CRM_MEDICO")
	private int crmMedico;

	@Column(name="NM_MEDICO")
	private String nmMedico;

	@OneToMany(mappedBy="medico",fetch=FetchType.EAGER)
	private List<ReceitasMedica> receitasMedicas;

	public Medico() {
	}

	public int getCrmMedico() {
		return this.crmMedico;
	}

	public void setCrmMedico(int crmMedico) {
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

}