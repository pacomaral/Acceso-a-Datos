
public class Tema {

	private String titulo, duracion;
	
	public Tema() {
		
	}
	
	public Tema(String titulo, String duracion) {
		this.titulo = titulo;
		this.duracion = duracion;
	}
	

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	
	
}
