package co.edu.udea.iw.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Adriana Guzman - amarcela.guzman@udea.edu.co
 * @since Java 1.8
 * @version 1.0
 * 
 * La clase ClienteWS corresponde al DTO Para la tabla clientes, solo con los atributos que requerimos y sus
 * metodos get y set
 */
@XmlRootElement
public class ClienteWS {

	/**
	 * Cedula del cliente
	 */
	private String cedula;
	/**
	 * Nombre del cliente
	 */
	private String nombres;
	/**
	 * Apellidos completos del cliente
	 */
	private String apellidos;
	/**
	 * Dirección de correo electrónico del cliente
	 */
	private String email;
	
	/**
	 * Metodo get para atributo cedula - permite obtener la cedula de un cliente
	 * @return la cedula del cliente
	 * */
	public String getCedula() {
		return cedula;
	}
	
	/**
	 * Metodo set para atributo cedula - permite almacenar la cedula de un cliente
	 * @param la cedula del cliente
	 * 
	 * */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	/**
	 * Metodo get para atributo nombre - permite obtener el nombre de un cliente
	 * @return el nombre del cliente
	 * */
	public String getNombres() {
		return nombres;
	}
	
	/**
	 * Metodo set para atributo nombre - permite almacenar el nombre de un cliente en 
	 * la variable nombres
	 * @param el nombre del cliente
	 * 
	 * */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	/**
	 * Metodo get para atributo apellido - permite obtener el apellido de un cliente
	 * @return el apellido del cliente
	 * */
	public String getApellidos() {
		return apellidos;
	}
	
	/**
	 * Metodo set para atributo apellido - permite almacenar el apellido de un cliente en 
	 * la variable apellidos
	 * @param el apellido del cliente
	 * 
	 * */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	/**
	 * Metodo get para atributo email - permite obtener el email de un cliente
	 * @return el email del cliente
	 * */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Metodo set para atributo del correo - permite almacenar el correo de un cliente en 
	 * la variable email
	 * @param correo del cliente
	 * 
	 * */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
