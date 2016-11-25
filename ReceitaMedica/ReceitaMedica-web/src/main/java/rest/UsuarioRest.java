package rest;

import java.io.Serializable;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.primefaces.json.JSONObject;

import model.Usuario;
import service.UsuarioService;
import utils.JsonUtils;
import utils.ParamUtils;

@Path("/usuario")
@ApplicationPath("/ServicoUsuario")
public class UsuarioRest extends Application implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioService usuarioService;
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/validarUsuario")
	public Usuario validarLogin(String json){
		
		JSONObject obj = JsonUtils.parseObject(json);
		String login = obj.getString(ParamUtils.LOGIN);
		String senha = obj.getString(ParamUtils.SENHA);
		
		try {
			return usuarioService.buscarPor(login, senha);
		} catch (Exception e) {
			return null;
		}
	}
}
