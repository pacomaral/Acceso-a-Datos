
public class Titulacion {

	private int id, horas;
	private String nombre, abreviatura;
	
	public Titulacion(int horas, String nombre, String abreviatura) {
		this.horas = horas;
		this.nombre = nombre;
		this.abreviatura = abreviatura;
	}

	public int getHoras() {
		return horas;
	}


	public void setHoras(int horas) {
		this.horas = horas;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getAbreviatura() {
		return abreviatura;
	}


	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
	
}
