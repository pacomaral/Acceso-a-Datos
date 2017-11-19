import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {


	public static void main(String[] args) {
		
		
		Document htmlDOM = null;
		
		try {
			//Creamos el document a partir de la conexión a una URL
			htmlDOM = Jsoup.connect("https://www.pccomponentes.com/").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Obtenemso una lista de los elementos que nos interesan
		Elements el = htmlDOM.getElementsByClass("GTM-productClick enlace-superpuesto");
		
		System.out.println("Destacados de la semana en PcComponentes: \n");
		
		//Recorremos la lista de Elements
		for(int i=0; i<el.size(); i++) {
			//De cada elemento de la lista Elements, obtenemos su info
			obtenerInfo(el.get(i));
		}
	}
	
	//Static para poder acceder desde el main
	//Método para mostrar la info que queremos de un determinado elemento
	public static void obtenerInfo(Element el) {
		
		String nombre = null;
		String precio = null;
		StringBuilder txt = new StringBuilder();
		
		//Obtenemos el nombre y el precio del producto
		nombre = el.attr("data-name");
		precio = el.attr("data-price");
		
		//Preparamos el texto
		txt.append("Nombre: " + nombre);
		txt.append("\n");
		txt.append("Precio: " + precio + " euros");
		txt.append("\n------------------------");
		
		//Lo imprimimos por pantalla
		System.out.println(txt.toString());
		
	}

}
