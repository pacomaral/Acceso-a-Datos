package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Marshaller {

	// Objetos y variables necesarias
	private Document dom = null;
	private ArrayList<Libro> libros = null;

	
	// Le pasaremos la lista de libros con la que trabajar en el constructor
	public Marshaller(ArrayList<Libro> l) {
		this.libros = l;
	}

	//Método para crear instancia de DOM
	//Lanzamos excepciones para controlar en controlador
	public void crearDocumento() throws ParserConfigurationException {
		
		//Creamos dbuilderfactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		//DocumentBuilder
		DocumentBuilder db = dbf.newDocumentBuilder();

		//Creamos instancia de DOM
		dom = db.newDocument();
	}
	
	public void crearArbolDOM() {

		//Crearemos el elemento raíz del XML
		Element docEle = dom.createElement("libros");
		dom.appendChild(docEle);

		//Recorremos la lista de libros
		for(int i = 0; i<libros.size(); i++) {
			//Obtenemos el libro de la lista
			Libro l = libros.get(i);
			
			//Con este libro creamos elemento mediante método setLibro, y añadimos a la raíz
			Element libroEle = setLibro(l);
			docEle.appendChild(libroEle);
		}

	}
	
	private Element setLibro(Libro l) {
		
		//Creamos el elemento
		Element libroEle = dom.createElement("libro");
		
		//Vamos creando elementos
		Element tituloEle = dom.createElement("titulo");
		Element nombreEle = dom.createElement("nombre");
		Element nombre2Ele = dom.createElement("nombre");
		Element autorEle = dom.createElement("autor");
		Element editorEle = dom.createElement("editor");
		Element pagsEle = dom.createElement("paginas");
		//Creamos atributo
		Attr anyoAttr = dom.createAttribute("anyo");
		
		//Como hay que meter texto en los nodos, creamos los necesarios y los asignamos al elemento
		Text tituloTexto = dom.createTextNode(l.getTitulo());
		tituloEle.appendChild(tituloTexto);
		
		Text editorTexto = dom.createTextNode(l.getEditor());
		editorEle.appendChild(editorTexto);
		
		Text pagsTexto = dom.createTextNode(Integer.toString(l.getNumPaginas()));
		pagsEle.appendChild(pagsTexto);
		
		//Vamos a suponer que solo hay el nombre de un autor en el ArrayList
		Text nombreTexto = dom.createTextNode(l.getListaAutores().get(0));
		nombreEle.appendChild(nombreTexto);
		
		//Si tenemos dos nombres de autores, creamos el nodo adicional dentro del nodo <autor>
		if(l.getListaAutores().size() > 1) {
			Text nombreTexto2 = dom.createTextNode(l.getListaAutores().get(1));
			nombre2Ele.appendChild(nombreTexto2);
			autorEle.appendChild(nombreEle);
			autorEle.appendChild(nombre2Ele);
		}
		else {
			autorEle.appendChild(nombreEle);
		}
		
		Text anyoTexto = dom.createTextNode(Integer.toString(l.getAnyo()));
		anyoAttr.appendChild(anyoTexto);
		
		
		//Organizamos el árbol
		
		tituloEle.setAttributeNode(anyoAttr);
		
		
		libroEle.appendChild(tituloEle);
		libroEle.appendChild(autorEle);
		libroEle.appendChild(editorEle);
		libroEle.appendChild(pagsEle);
		
		return libroEle;
	}
	
	//Falta escribir el Document a XML y poder permitir al usuario introducir varios nombres

	public void escribirDocumentEnXML(File fich) throws TransformerException {
		
		//Creamos instancia de transformer
		Transformer transf = TransformerFactory.newInstance().newTransformer();
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		
		//Especificaremos donde lo escribimos y qué escribimos
		StreamResult resultado = new StreamResult(fich);
		DOMSource source = new DOMSource(dom);
		
		transf.transform(source, resultado);
	}
}
