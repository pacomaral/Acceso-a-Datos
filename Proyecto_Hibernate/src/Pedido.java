import java.util.ArrayList;
import java.util.List;

public class Pedido {
	
	//Variables
	private int id;
	private String fecha;
	private List items = new ArrayList<Item>();
	
	//Constructores
	public Pedido() {
		
	}
	
	public Pedido(int id, String fecha) {
		this.id = id;
		this.fecha = fecha;
	}
	
	
	//Getters y setters
	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	
}