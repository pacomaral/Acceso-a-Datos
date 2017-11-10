package principal;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import controller.GestionEventos;
import model.Marshaller;
import model.Parser;
import view.Ventana;

public class Principal {

	
	private static String textoIntroducido;
	private static File file;
	private static Scanner teclado;
	private static Parser parser_libro;
	
	public static void main(String[] args) {
		
		/*parser_libro = new Parser();
		
		try {
			parser_libro.crearDocumentDeXML("dir/biblioteca.xml");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		parser_libro.parsearDocument();
		
		parser_libro.print();
		*/
		
		Parser parser = new Parser();
		
		Ventana vista = new Ventana();
		vista.setVisible(true);
		
		GestionEventos controlador = new GestionEventos(parser, vista);
		//Añadimos los listeners
		controlador.control();
	}
	
	
	
	//Método para realizar el ejercicio mediante un menú
	public static void implementarEjercicio() {

		teclado = new Scanner(System.in);

		//Creamos un "menú" por consola
		System.out.println("Elige lo que quieres hacer:");
		System.out.println("1. Parsear XML del directorio del proyecto");
		System.out.println("2. Salir");

		textoIntroducido = teclado.nextLine();

		//Comprobamos que se elija la opción correcta
		while(!textoIntroducido.equals("1") && !textoIntroducido.equals("2")) {
			System.out.println("Error. Introduce una opción válida:");
			System.out.println("1. Parsear XML del directorio del proyecto");
			System.out.println("2. Salir");
			textoIntroducido = teclado.nextLine();
		}

		switch(textoIntroducido) {
		case "1":
			//Si se ha elegido la opción 1, hacemos una lista de los ficheros del directorio del proyecto CON EXTENSIÓN .XML
			file = new File("dir");
			File[] ficheros = file.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".xml");
				}
			});

			//Los mostramos
			System.out.println("Elige un fichero a parsear: ");
			String[] valores = new String[ficheros.length];
			
			for(int i=0; i<ficheros.length; i++) {
				System.out.println((i+1) + ". " + ficheros[i].getName());
				valores[i] = ficheros[i].getName();
			}
			textoIntroducido = teclado.nextLine();
			
			int numFich=0;
			
			try {
				numFich = Integer.parseInt(textoIntroducido);
			}
			catch(Exception e) {
				System.out.println("No has elegido un valor válido");
			}
			
			parser_libro = new Parser();
			
			try {
				//Creamos DOM
				parser_libro.crearDocumentDeXML(valores[numFich]);
			} catch (ParserConfigurationException e) {
				System.out.println("Error parseando el documento");
			} catch (SAXException e) {
				e.printStackTrace();
				System.out.println("Error inesperado");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error.");
			}
			
			//Parseamos elementos del DOM
			parser_libro.parsearDocument();
			
			//Imprimimos 
			//parser_libro.print();
			
			break;

		case "2":
			System.out.println("Finalizando programa");
			System.exit(0);
			break;
		}
	}

}
