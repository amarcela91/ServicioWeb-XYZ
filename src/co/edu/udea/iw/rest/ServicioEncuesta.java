package co.edu.udea.iw.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.dto.Encuesta;
import co.edu.udea.iw.exception.ExceptionPropia;
import co.edu.udea.iw.impl.EncuestaImpl;

@Path("/Encuesta")
@Component
/**
 * @author Adriana Guzman - amarcela.guzman@udea.edu.co
 * @since Java 1.8
 * @version 1.0
 * 
 * Clase ServicioEncuesta que contiene los servicios para la clase encuesta
 * 
 */
public class ServicioEncuesta {
	@Autowired
	private EncuestaImpl encuestaImpl;
	
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	@Path("Crear/{codigo}/{pregunta1}/{pregunta2}/{pregunta3}/{pregunta4}")
	/**
	 * Servicio GET que permite crear una nueva encuesta
	 * */
	public String creaEncuesta(@PathParam("codigo")Integer codigo, @PathParam("pregunta1")Integer pregunta1, 
			@PathParam("pregunta2")Integer pregunta2, @PathParam("pregunta3")Integer pregunta3,
			@PathParam("pregunta4")Integer pregunta4) throws ExceptionPropia{
		
		try{
			encuestaImpl.creaEncuesta(codigo, pregunta1, pregunta2, pregunta3, pregunta4);			
		}catch(ExceptionPropia e){			
			return e.getMessage();
		}
		
		return "Se ha creado la encuesta con id: " + codigo ;
	}
	
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/BuscarEncuesta/{codigo}")
	/**
	 * Servicio GET que permite obtener la información de una encuesta dado su codigo
	 * */
	public String obtenerInfo(@PathParam("codigo") Integer codigo) throws ExceptionPropia{

		Encuesta encuesta = new Encuesta();

		
		try{				
			encuesta = encuestaImpl.obtener(codigo);
						
		}catch(ExceptionPropia e){
			throw new ExceptionPropia(e.getMessage());
		}
		
		return "El cliente de la encuesta No: "+ encuesta.getCodigo() + "Evalua la encuesta así: " + encuesta.getPregunta1() + " " + encuesta.getPregunta2() + " " + encuesta.getPregunta3() + " " +encuesta.getPregunta4();
	}

}
