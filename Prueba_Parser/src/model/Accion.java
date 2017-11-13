package model;

import java.util.ArrayList;

public class Accion {

	//Cada acción tiene su nombre, y unas cuantas operaciones
	private String nombre;
	private ArrayList<Operacion> operaciones;
	
	//Constructor
	public Accion(String nombre, ArrayList<Operacion> operaciones) {
		this.nombre = nombre;
		this.operaciones = operaciones;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Operacion> getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(ArrayList<Operacion> operaciones) {
		this.operaciones = operaciones;
	}


	public void imprimirInfo() {
		StringBuilder str = new StringBuilder();
		
		str.append("Acción: " + nombre);
		str.append("\n\n");
		
		//Para cada operacion, mostramos su info
		for(int i=0; i<operaciones.size(); i++) {
			str.append("Operación nº" + (i+1));
			str.append(operaciones.get(i).toString());
			str.append("\n\n");
		}
		
		str.append("\n---------------------------");
		
		
		System.out.println(str.toString());
	}

}
