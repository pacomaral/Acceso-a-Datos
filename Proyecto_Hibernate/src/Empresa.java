public class Empresa {
	
	//Variables
	private String cif;
	private String nombre;
	private int empleados;
	private String direccion;
	
	//Constructor
	public Empresa(String cif, String nombre, int empleados, String direccion) {
		this.cif = cif;
		this.nombre = nombre;
		this.empleados = empleados;
		this.direccion = direccion;
	}

	//Getters y setters
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEmpleados() {
		return empleados;
	}
	public void setEmpleados(int empleados) {
		this.empleados = empleados;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	

}