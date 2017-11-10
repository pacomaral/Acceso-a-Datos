package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import model.*;
import view.*;

public class GestionEventos {

	private Parser parser;
	private Marshaller marshaller;					//El objeto marshaller lo crearemos cuando sea necesario utilizarlo, no en el constructor
	private Ventana vista;
	private ActionListener listener_botonParsear, listener_botonAnyadir, listener_botonCrearXML;
	
	private ArrayList<Libro> libros = new ArrayList<Libro>();
	

	public GestionEventos(Parser parser, Ventana vista) {
		this.parser = parser;
		this.vista = vista;
	}

	
	//Método para asignar todos los listeners y llamar a los métodos necesarios
	public void control() {
		
		//LISTENERS PARA ELEMENTOS VENTANA
		//-----------------------------------------
		listener_botonParsear = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				
				parsearLibro();
				
			}
			
		};
		vista.getBotonParsear().addActionListener(listener_botonParsear);
		
		
		//Boton de añadir libro a la lista del xml
		listener_botonAnyadir = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				anyadirLibro();
			}
		};
		vista.getBotonAnyadirLibro().addActionListener(listener_botonAnyadir);
		
		//Boton de crear el XML
		listener_botonCrearXML = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				crearXML();
			}
		};
		vista.getBotonCrearXML().addActionListener(listener_botonCrearXML);
		
	}

	private void parsearLibro() {
		
		
		if(vista.getCajaFichero().getText().isEmpty()){
			vista.mostrarMensaje("No has introducido ningún fichero para parsear");
		}
		else {
			//Creamos file para comprobar si existe
			File file = new File("dir/" + vista.getCajaFichero().getText());

			if(!file.exists()) {
				vista.mostrarError("El fichero que has introducido no existe. Asegurate de que el XML esté en el directorio 'dir' del proyecto.");
			}
			else {
				try {
					parser.crearDocumentDeXML(file.getAbsolutePath());
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				parser.parsearDocument();
				
				vista.getAreaTexto().setText(parser.getInfo());
			}
		}
	}
	
	private void anyadirLibro() {
		
		ArrayList<String> autores = new ArrayList<String>();
		
		
		//Si falta algo por introducir lo advertimos
		if(vista.getCajaTitulo().getText().isEmpty() || vista.getCajaAnyo().getText().isEmpty() || vista.getCajaAutor().getText().isEmpty() || vista.getCajaEditor().getText().isEmpty() || vista.getCajaNumPags().getText().isEmpty()){
			vista.mostrarMensaje("Te falta introducir algún dato del libro");
		}
		else {
			
			
			autores.add(vista.getCajaAutor().getText());
			
			if(!vista.getCajaAutor2().getText().isEmpty()) {
				autores.add(vista.getCajaAutor2().getText());
			}
			
			//Constriumos un objeto libro con los datos introducidos por el usuario
			Libro libro = new Libro(Integer.valueOf(vista.getCajaAnyo().getText()), Integer.valueOf(vista.getCajaNumPags().getText()), vista.getCajaTitulo().getText(), autores, vista.getCajaEditor().getText());
			
			//Añadimos el libro
			libros.add(libro);
			
			//Mostramos info de que se ha añadido el libro
			vista.mostrarMensaje("Libro añadido a la lista con éxito");
			
			//Limpiamos todos los campos una vez se ha añadido el libro
			vista.getCajaTitulo().setText("");
			vista.getCajaAutor().setText("");
			vista.getCajaAutor2().setText("");
			vista.getCajaEditor().setText("");
			vista.getCajaNumPags().setText("");
			vista.getCajaAnyo().setText("");
			
			//Sumamos uno al contador de la ventana y cambiamos txt etiqueta
			vista.setContador(vista.getContador() + 1);
			vista.getEtiquetaContadorLibros().setText(vista.getContador() + " libros introducidos");
		}
	}
	
	private void crearXML() {
		
		
		if(vista.getCajaFicheroXML().getText().isEmpty()) {
			vista.mostrarMensaje("Debes introducir el nombre del fichero XML a crear");
		}
		else if(libros.isEmpty()) {
			vista.mostrarError("No has añadido ningún libro por lo que no se puede crear un XML");
		}
		else {
			//Creamos el objeto con la lista de los libros introducidos como parámetros
			marshaller = new Marshaller(libros);
			
			//Creamos un objeto file con el nombre del nuevo fichero
			File fichero = new File("dir/"+vista.getCajaFicheroXML().getText());
			
			//Comenzamos el proceso de creación del fichero mediante el Marshaller
			try {
				marshaller.crearDocumento();
			} catch (ParserConfigurationException e) {
				vista.mostrarError("Ha ocurrido un error escribiendo el XML");
			}
			
			marshaller.crearArbolDOM();
			
			try {
				marshaller.escribirDocumentEnXML(fichero);
			} catch (TransformerException e) {
				vista.mostrarError("Ha ocurrido un error al crear el fichero");
			}
			
			//Mostramos mensaje de éxito
			vista.mostrarMensaje("XML creado en el directorio 'dir' del proyecto");
		}
	}
	
}
