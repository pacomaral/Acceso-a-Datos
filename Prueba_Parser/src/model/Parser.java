package model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {

	private Document dom = null;

	private NodeList nl1;
	
	private String nombreAccion, tipoOperacion;
	private String cantidadOperacion;
	private String precioOperacion;
	private ArrayList<Accion> acciones;

	public Parser() {
		acciones = new ArrayList<Accion>();
	}

	public void crearDocumentDeXML(String fichero) throws ParserConfigurationException, SAXException, IOException {
		
		// Creamos DocumentBuilderFactory para poder crear DocumentBuilder del que crearemos el dom
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder db = dbf.newDocumentBuilder();

		// Obtenemos el Document dom que utilizaremos para obtener los elementos del xml
		dom = db.parse(fichero);
	}

	public void parsearDocument() {
		
		//Obtenemos elemento raíz
		Element accionesEle = dom.getDocumentElement();

		// Obtenemos el nodelist de elementos
		nl1 = accionesEle.getElementsByTagName("accion");
		
		//Si el nodelist no está vacío lo recorremos y recogemos los elementos
		if (nl1 != null && nl1.getLength() > 0) {
			for (int i = 0; i < nl1.getLength(); i++) {

				// Para cada elemento acción
				Element el = (Element) nl1.item(i);

				
				
				//Cogemos el atributo y lo asignamos al objeto
				nombreAccion = getAttribute(el, "nombre");
				
				//Creamos objeto accion
				Accion a = new Accion(nombreAccion, getOperaciones(el));
				
				
				//Añadimos accion al ArrayList
				acciones.add(a);
			}
		}
	}
	
	private ArrayList<Operacion> getOperaciones(Element elementoAccion) {
		
		ArrayList<Operacion> operaciones = new ArrayList<Operacion>();
		
		Element elementoOperaciones=null;
		
		NodeList nlOperaciones = elementoAccion.getElementsByTagName("operaciones");
		
		//Puesto que solo hay un elemento <operaciones> dentro de <acción>, lo cogeremos y trabajaremos sobre este
		if(nlOperaciones != null && nlOperaciones.getLength() > 0) {
			elementoOperaciones = (Element) nlOperaciones.item(0);
		}
		
		//Con este ElementoOperaciones, recogemos las distintas operaciones de cada acción en el nodelist nlOperacion
		NodeList nlOperacion = elementoOperaciones.getElementsByTagName("operacion");
		if(nlOperacion != null && nlOperacion.getLength() > 0) {
			//Recorremos
			for(int i=0; i<nlOperacion.getLength(); i++) {
				
				Element el = (Element) nlOperacion.item(i);
				tipoOperacion = getTextValue(el, "tipo");
				cantidadOperacion = getTextValue(el, "cantidad");
				precioOperacion = getTextValue(el, "precio");
				
				//Creamos un objeto operacion con esos datos
				Operacion op = new Operacion(tipoOperacion, cantidadOperacion, precioOperacion);
				
				//Añadimos ese objeto a la lista que devolveremos
				operaciones.add(op);
			}
		}
		
		return operaciones;
	}
	
	
	
	//Método para extraer el contenido de un elemento (String)
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		
		//Obtenemos nodelist
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			//Extraemos el contenido de ese elemento
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}		
		return textVal;
	}
	
	
	//Método para extraer elemento de un atributo
	private String getAttribute(Element elementoAtributo, String atributo) {
		String attr = null;

		attr = elementoAtributo.getAttribute(atributo);
		
		return attr;
	}
	
	//Recorreremos la lista de acciones y imprimimos info de cada una
	public void imprimirInfo() {
		for(int i=0; i<acciones.size(); i++) {
			acciones.get(i).imprimirInfo();
		}
	}
	

	

	
	

}