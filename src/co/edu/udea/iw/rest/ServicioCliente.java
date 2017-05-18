package co.edu.udea.iw.rest;

import co.edu.udea.iw.impl.ClienteImpl;

import java.rmi.RemoteException;
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

import co.edu.udea.iw.dto.Cliente;
import co.edu.udea.iw.dto.ClienteWS;
import co.edu.udea.iw.exception.ExceptionPropia;

@Path("/Clientes")
@Component
/**
 * @author Adriana Guzman - amarcela.guzman@udea.edu.co
 * @since Java 1.8
 * @version 1.0
 * 
 * Clase ServicioCliente que contiene los servicios para un Cliente
 * 
 */
public class ServicioCliente {
	
	@Autowired
	private ClienteImpl clienteImpl;
	
	
	@Produces(MediaType.TEXT_PLAIN)
	@POST 
	/*
	@Path("Crea/{cedula}/{nombres}/{apellidos}/{email}/{telefono}/{direccion}")*/
	/**
	 * Servicio POST que permite guardar un cliente en la Base de datos
	 * */
	public String guardaCliente(@QueryParam("cedula")String cedula, @QueryParam("nombres")String nombres, 
			@QueryParam("apellidos")String apellidos, @QueryParam("email")String email, 
			@QueryParam("telefono")String telefono,@QueryParam("direccion")String direccion) throws ExceptionPropia{
		
		try{
			clienteImpl.guardaCliente(cedula, nombres, apellidos, email, telefono, direccion);			
		}catch(ExceptionPropia e){			
			return e.getMessage();
		}
			
		
		return "Se ha creado el cliente " + nombres + " " + apellidos + " con cedula " + cedula;
	}
	
	
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	@Path("Actualizar/{cedula}/{nombres}/{apellidos}/{email}/{telefono}/{direccion}")
	/**
	 * Servicio GET que permite actualizar un cliente en la Base de datos
	 * */
	public String actualizarCliente(@PathParam("cedula")String cedula, @PathParam("nombres")String nombres, 
			@PathParam("apellidos")String apellidos, @PathParam("email")String email, 
			@PathParam("telefono")String telefono, @PathParam("direccion")String direccion) throws ExceptionPropia{
		
		try{
			clienteImpl.actualizarCliente(cedula, nombres, apellidos, email, telefono, direccion);			
		}catch(ExceptionPropia e){			
			return e.getMessage();
		}
					
		return "Cliente actualizado: " + nombres + " " + apellidos + " con cedula: " + cedula;
	}
	
	
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/ListaClientes")
	/**
	 * Servicio GET que permite obtener el listado de clientes 
	 * */
	public List<ClienteWS> obtener() throws RemoteException{
		
		List<ClienteWS> clientes = new ArrayList<ClienteWS>();
		
		List<Cliente> listaClientes = null;
		try{
			
			listaClientes = clienteImpl.obtener();
			
			for(Cliente cliente : listaClientes){
				
				ClienteWS clienteWS = new ClienteWS();
				
				clienteWS.setCedula(cliente.getCedula());
				clienteWS.setNombres(cliente.getNombres());
				clienteWS.setApellidos(cliente.getApellidos());
				clienteWS.setEmail(cliente.getEmail());;				
				
				clientes.add(clienteWS);
				
			}
			
		}catch(ExceptionPropia e){
			throw new RemoteException(e.getMessage());
			
		}
		return clientes;
	}
	
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	@Path("/BuscaCliente/{cedula}")
	/**
	 * Servicio GET que permite obtener un cliente dada su cedula 
	 * */
	public String obtener(@PathParam("cedula") String cedula) throws RemoteException, ExceptionPropia{

		Cliente cliente = new Cliente();
		
		try{				
			cliente = clienteImpl.obtener(cedula);
			
		}catch(ExceptionPropia e){
			throw new RemoteException(e.getMessage());			
		}
			
		
		return cliente.getEmail();
	}
	
	

}
