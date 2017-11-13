package principal;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.Parser;

public class Principal {

	
	private static String textoIntroducido;
	private static File file;
	private static Scanner teclado;
	private static Parser parser_libro;
	
	public static void main(String[] args) {
		
		
		Parser parser = new Parser();
		
		
		try {
			parser.crearDocumentDeXML("dir/fichero1.xml");
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
		
		parser.parsearDocument();
		
		parser.imprimirInfo();
	}
	

}
