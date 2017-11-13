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
	private ArrayList<Libro> libros = null;
	private NodeList nl1, nl2;
	
	private String titulo, editor;
	private ArrayList<String> autores;				//Para recoger los distintos autores
	private int numPag, anyo;

	public Parser() {
		//Aquí almacenaremos los libros recogidos del XML
		libros = new ArrayList<Libro>();
		
		//Aquí almacenaremos los nombres de los autores
		autores = new ArrayList<String>();
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
		Element docEle = dom.getDocumentElement();

		// Obtenemos el nodelist de elementos
		nl1 = docEle.getElementsByTagName("libro");
		
		//Si el nodelist no está vacío lo recorremos y recogemos los elementos
		if (nl1 != null && nl1.getLength() > 0) {
			for (int i = 0; i < nl1.getLength(); i++) {

				// Obtenemos el elemento del nodo
				Element el = (Element) nl1.item(i);

				//Obtenemos un libro
				Libro libro = getLibro(el);

				// lo añadimos al array
				libros.add(libro);
			}
		}
	}
	
	
	//Método para devolver un objeto Libro a partir de todos elementos hijos de libro
	private Libro getLibro(Element ElementoLibro){
		
		
		//Recogemos los datos de cada libro para crear el objeto Libro
		titulo = getTextValue(ElementoLibro, "titulo");
		editor = getTextValue(ElementoLibro, "editor");
		numPag = getIntValue(ElementoLibro, "paginas");
		anyo = Integer.parseInt(getAttribute(ElementoLibro, "titulo", "anyo"));
		
		
		NodeList nl_autores = ElementoLibro.getElementsByTagName("autor");
		
		if(nl_autores != null && nl_autores.getLength() > 0) {
			//Cogemos el elemento autores para pasarlo al método getAutores
			Element el = (Element)nl_autores.item(0);
			autores = getAutores(el);
		}
		
		//autores = getAutores(ElementoLibro);
		
		
		//Finalmente creamos el libro con los atributos leídos del elemento
		Libro libro = new Libro(anyo, numPag, titulo, autores, editor);
		
		return libro;		
		
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
	
	
	
	//Método para extraer el contenido de un elemento (Entero)
	private int getIntValue(Element ele, String tagName) {
		//Hacemos el getTextValue y parseamos a entero
		return Integer.parseInt(getTextValue(ele,tagName));
	}
	
	
	//Método para extraer elemento de un atributo
	private String getAttribute(Element ele, String nombreNodo, String atributo) {
		String attr = null;

		//Obtenemos nodelist
		NodeList nl = ele.getElementsByTagName(nombreNodo);
		if(nl != null && nl.getLength() > 0) {
			//Extraemos el atributo de ese elemento
			Element el = (Element)nl.item(0);
			attr = el.getAttribute(atributo);
		}
		
		return attr;
	}
	
	//Si hubiera otro nodo <nombre> por el XML, lo cogería también, no solo el de los autores
	//Habría que hacer el getElementsByTagName("nombre"), del elemento Autores. - HECHO
	public ArrayList<String> getAutores(Element el){

		ArrayList<String> lista = new ArrayList<String>();

		NodeList nl = el.getElementsByTagName("nombre");

		if(nl != null && nl.getLength() > 0) {
			for(int i=0; i<nl.getLength(); i++) {
				Element el2 = (Element) nl.item(i);
				lista.add(el2.getFirstChild().getNodeValue());
			}
		}
		
		return lista;
	}

	
	
	//Método para imprimir datos de cada libro obtenido y que tenemos en el ArrayList 'libros'
	public String getInfo(){
		
		StringBuilder str = new StringBuilder();
		
		for(int i=0; i<libros.size(); i++) {
			//Imprimimos información del libro
			str.append(libros.get(i).toString());
		}
		
		libros.clear();
		
		return str.toString();
	}
	
	

}