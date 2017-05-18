package co.edu.udea.iw.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.dto.Rol;
import co.edu.udea.iw.exception.ExceptionPropia;
import co.edu.udea.iw.impl.RolImpl;

@Path("/Rol")
@Component
/**
 * @author Adriana Guzman - amarcela.guzman@udea.edu.co
 * @since Java 1.8
 * @version 1.0
 * 
 * Clase ServicioRol que contiene los servicios que se pueden prestar a la clase rol
 * 
 */
public class ServicioRol {
	
	@Autowired
	private RolImpl rolImpl;
	
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	@Path("Crear/{codigo}/{nombre}")
	/**
	 * Servicio GET que permite crear un nuevo rol
	 * */
	public String crearRol(@PathParam("codigo")String codigo, @PathParam("nombre")String nombre) throws ExceptionPropia{
		
		try{
			rolImpl.creaRol(codigo, nombre);			
		}catch(ExceptionPropia e){			
			return e.getMessage();
		}
		
		return "Se ha creado correctamente el rol de: " + nombre;
	}
	
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	@Path("Modificar/{codigo}/{nombre}")
	/**
	 * Servicio GET que permite modificar un rol existente
	 * */
	public String modificarRol(@PathParam("codigo")String codigo, @PathParam("nombre")String nombre) throws ExceptionPropia{
		
		try{
			rolImpl.modificaRol(codigo, nombre);			
		}catch(ExceptionPropia e){			
			return e.getMessage();
		}
		
		return "Se ha modificado el rol: " + codigo + " con el nuevo nombre: " + nombre;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/ListaRoles")
	/**
	 * Servicio GET que permite obtener el listado de roles existentes
	 * 
	 * */
	public List<Rol> obtener() throws ExceptionPropia{
		
		List<Rol> roles = new ArrayList<Rol>();
		
		List<Rol> listaRol = null;
		try{
			
			listaRol = rolImpl.obtener();
			
			for(Rol getRol : listaRol){
				
				Rol rol = new Rol();
				
				rol.setCodigo(getRol.getCodigo());
				rol.setNombre(getRol.getNombre());
								
				roles.add(rol);
				
			}
			
		}catch(ExceptionPropia e){
			throw new ExceptionPropia(e.getMessage());
			
		}
		
		return roles;
	}
	
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/BuscarRol/{codigo}")
	/**
	 * Servicio GET que permite obtener un rol dado su codigo
	 * */
	public String obtenerRol(@PathParam("codigo") String codigo) throws ExceptionPropia{

		Rol rol = new Rol();

		
		try{				
			rol = rolImpl.obtener(codigo);
						
		}catch(ExceptionPropia e){
			throw new ExceptionPropia(e.getMessage());
		}
		
		return "El codigo: " + rol.getCodigo() + " corresponde al rol:  " + rol.getNombre();
	}

}
