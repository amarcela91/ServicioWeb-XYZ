package co.edu.udea.iw.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.dto.Peticion;
import co.edu.udea.iw.exception.ExceptionPropia;
import co.edu.udea.iw.impl.PeticionImpl;

@Path("/Peticion")
@Component
/**
 * @author Adriana Guzman - amarcela.guzman@udea.edu.co
 * @since Java 1.8
 * @version 1.0
 * 
 * Clase ServicioPeticion que contiene los servicios que presta una petición
 * 
 */
public class ServicioPeticion {
	@Autowired
	private PeticionImpl peticionImpl;
	
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	@Path("/Crear/{codigo}/{texto}/{cliente}/{fechaCreacion}")
	/**
	 * Servicio GET que permite crear una nueva peticion
	 * */
	public String crearPeticion(@PathParam("codigo")Integer codigo, @PathParam("texto")String texto,
			@PathParam("cliente")String cliente, @PathParam("fechaCreacion") Date fechaCreacion) throws ExceptionPropia{
		
		try{
			peticionImpl.creaPeticion(codigo, texto, cliente, fechaCreacion);			
		}catch(ExceptionPropia e){			
			return e.getMessage();
		}
		
		return "";
	}
	
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/ListaPeticiones")
	/**
	 * Servicio GET que permite obtener el listado de peticiones
	 * */
	public List<Peticion> obtener() throws ExceptionPropia{
		
		List<Peticion> peticiones = new ArrayList<Peticion>();
		
		List<Peticion> listaPeticiones = null;
		try{
			
			listaPeticiones = peticionImpl.obtener();
			
			for(Peticion getPeticiones : listaPeticiones){
				

				Peticion peticion = new Peticion();
				
				peticion.setCodigo(getPeticiones.getPeticion2().getCodigo());
				peticion.setCliente(getPeticiones.getPeticion2().getCliente().getCedula());
				peticion.setFechaCreacion(getPeticiones.getFechaCreacion());
				peticion.setTexto(getPeticiones.getTexto());
				peticion.setResuelto(getPeticiones.getResuelto());
				peticiones.add(peticion);
				
			}
			
		}catch(ExceptionPropia e){
			throw new ExceptionPropia(e.getMessage());
			
		}
		return peticiones;
	}

}
