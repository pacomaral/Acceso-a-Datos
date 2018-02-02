import java.util.ArrayList;
import java.util.List;

public class Asignatura {

	private int id, horas;
	private String nombre;
	private List temas = new ArrayList<Tema>();
	
	public Asignatura() {
		
	}
	
	public Asignatura(int horas, String nombre) {
		this.horas = horas;
		this.nombre = nombre;
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

	public List getTemas() {
		return temas;
	}

	public void setTemas(List temas) {
		this.temas = temas;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
	
}
