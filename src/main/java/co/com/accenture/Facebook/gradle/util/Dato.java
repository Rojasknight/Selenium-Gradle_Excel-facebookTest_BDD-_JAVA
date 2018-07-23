package co.com.accenture.Facebook.gradle.util;

public class Dato {
	
	private String nombre;

	
	
	
	
	public Dato(String nombre) {
	
		this.nombre = nombre;

	}
	public Dato() {
		nombre="";
	
		// TODO Auto-generated constructor stub
	}
	
	public boolean isEmpty() {
		
		return nombre.isEmpty();
		
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "Dato [nombre=" + nombre + "]";
	}
	
	
	
}
