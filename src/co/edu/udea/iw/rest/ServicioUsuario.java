package co.edu.udea.iw.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.ExceptionPropia;
import co.edu.udea.iw.impl.UsuarioImpl;

@Path("/Usuario")
@Component
/**
 * @author Adriana Guzman - amarcela.guzman@udea.edu.co
 * @since Java 1.8
 * @version 1.0
 * 
 * Clase ServicioUsuario que contiene los servicios para la clase usuario
 * 
 */
public class ServicioUsuario {
	
	@Autowired
	private UsuarioImpl usuarioImpl;
	
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	/**
	 * Servicio POST que permite crear un nuevo usuario
	 * */
	public String crearUsuario(@QueryParam("login")String login, @QueryParam("nombres")String nombres, 
			@QueryParam("apellidos")String apellidos, @QueryParam("contrasena")String contrasena, 
			@QueryParam("rol")String rol) throws ExceptionPropia{
		
		try{
			usuarioImpl.crearUsuario(login, nombres, apellidos, contrasena, rol);			
		}catch(ExceptionPropia e){			
			return e.getMessage();
		}
		return "";
	}
	
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/ListaUsuarios")
	/**
	 * Servicio GET que permite obtener el listado de usuarios existentes
	 * */
	public List<Usuario> obtener() throws ExceptionPropia{
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		List<Usuario> listaUsuarios = null;
		
		try{
			
			listaUsuarios = usuarioImpl.obtener();
			
			for(Usuario usuario : listaUsuarios){
				
				Usuario usuario1 = new Usuario();
				
				usuario1.setNombres(usuario.getNombres());
				usuario1.setApellidos(usuario.getApellidos());
				usuario1.setLogin(usuario.getLogin());
				
				usuarios.add(usuario1);
				
			}
			
		}catch(ExceptionPropia e){
			throw new ExceptionPropia(e.getMessage());
			
		}
		return usuarios;
	}
	
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	@Path("/BuscarUsuario/{login}")
	/**
	 * Servicio GET que permite obtener un usuario dado su login
	 * */
	public String obtener(@PathParam("login") String login) throws ExceptionPropia{

		Usuario usuario = new Usuario();

		
		try{				
			usuario = usuarioImpl.obtener(login);
						
		}catch(ExceptionPropia e){
			throw new ExceptionPropia(e.getMessage());
		}
		
		return usuario.getRol().getCodigo();
	}
	
	
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	@Path("Validar/{login}/{clave}")
	/**
	 * Servicio GET que permite validar el login y clave de un usuario
	 * */
	public String validar(@PathParam("login")String login, @PathParam("clave")String clave) throws ExceptionPropia{
		try{
			
			usuarioImpl.validar(login, clave);
		}catch(ExceptionPropia e){
			
			return e.getMessage();
		}
		
		return "";
	}

}
