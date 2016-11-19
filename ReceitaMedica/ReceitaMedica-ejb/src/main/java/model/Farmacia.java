package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="farmacias")
public class Farmacia implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CNPJ")
	private String cnpjFarmacia;
	
	@Column(name="NM_FARMACIA")
	private String nmFarmacia;
	
	@OneToOne(cascade = CascadeType.ALL,fetch= FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="LOGIN", referencedColumnName="LOGIN",insertable = true , updatable = true)
	private Usuario usuario;

	public String getCnpjFarmacia() {
		return cnpjFarmacia;
	}

	public void setCnpjFarmacia(String cnpjFarmacia) {
		this.cnpjFarmacia = cnpjFarmacia;
	}

	public String getNmFarmacia() {
		return nmFarmacia;
	}

	public void setNmFarmacia(String nmFarmacia) {
		this.nmFarmacia = nmFarmacia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
