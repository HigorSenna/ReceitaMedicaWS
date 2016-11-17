package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="usuarios")
@Entity
public class Usuario  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="LOGIN",length = 35,nullable = false)
	private String login;
	
	@Column(name="SENHA",length = 35,nullable = false)
	private String senha;
	
	@Column(name="FL_TIPO_USUARIO",length = 1,nullable = false)
	private String flTipoUsuario;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFlTipoUsuario() {
		return flTipoUsuario;
	}

	public void setFlTipoUsuario(String flTipoUsuario) {
		this.flTipoUsuario = flTipoUsuario;
	}
}
