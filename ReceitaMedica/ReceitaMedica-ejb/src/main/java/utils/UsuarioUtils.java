package utils;

import enums.TipoUsuarioEnum;
import model.Medico;
import model.Paciente;
import model.Usuario;

public abstract class UsuarioUtils {
	
	public static Usuario getUsuarioPaciente(Paciente paciente){
		
		Usuario usuario = new Usuario();
		usuario.setLogin(paciente.getCpfPaciente());
		usuario.setSenha(paciente.getCpfPaciente()+ 'U');
		
		usuario.setFlTipoUsuario(TipoUsuarioEnum.PACIENTE.getValor());
		
		return usuario;
	}
	
	public static Usuario getUsuarioMedico(Medico medico){
		Usuario usuario = new Usuario();
		usuario.setLogin(medico.getCrmMedico());
		usuario.setSenha(medico.getCrmMedico() + 'M');
		
		usuario.setFlTipoUsuario(TipoUsuarioEnum.MEDICO.getValor());
		
		return usuario;
	}
}
