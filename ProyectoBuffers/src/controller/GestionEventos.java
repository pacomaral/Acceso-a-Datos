package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.*;
import view.*;

public class GestionEventos {

	private GestionDatos model;
	private LaunchView view;
	private ActionListener actionListener_comparar, actionListener_buscar, actionListener_copiar, actionListener_ordenar, actionListener_anyadir, actionListener_recuperarLibro, actionListener_recuperarTodos;
	private ActionListener actionListener_rotarFoto, actionListener_crearEspejo;
	
	//Actividad 1
	private ActionListener actionListener_cambiarAnyo;
	
	//Ejercicio 2
	private ActionListener actionListener_numPalabrasLongitudMenor;
	
	private String fichero1, fichero2, fichero3;
	private boolean resultado;
	private int posicion;
	private String palabra;

	public GestionEventos(GestionDatos model, LaunchView view) {
		this.model = model;
		this.view = view;
	}

	public void control() {
		//LISTENER BOTON DE COMPARAR
		//-----------------------------------------
		actionListener_comparar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// TODO: Llamar a la función call_compararContenido
				int s = call_compararContenido();
			}
		};
		view.getComparar().addActionListener(actionListener_comparar);
		
		//LISTENER BOTON DE BUSCAR
		//-----------------------------------------
		actionListener_buscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// TODO: Llamar a la función call_buscarPalabra
				int c = call_buscarPalabra();
			}
		};
		view.getBuscar().addActionListener(actionListener_buscar);
		
		//LISTENER BOTON DE COPIAR
		//-----------------------------------------
		actionListener_copiar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// Llamamos al método de copiarFichero
				int c = call_copiarFichero();
			}
		};
		view.getCopiar().addActionListener(actionListener_copiar);
		
		//LISTENER BOTON DE ORDENAR
		//-----------------------------------------
		actionListener_ordenar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// Llamamos al método ordenarContenido
				int c = call_ordenarFichero();
			}
		};
		view.getOrdenar().addActionListener(actionListener_ordenar);
		
		//LISTENER BOTON DE AÑADIRLIBRO
		//-----------------------------------------
		actionListener_anyadir = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// Llamamos al método
				try {
					int c = call_guardarLibro();
				} 
				//Excepciones del método existeId y existeNombre de GestionDatos
				catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		view.getAnyadirLibro().addActionListener(actionListener_anyadir);
		
		//LISTENER BOTON DE RECUPERARLIBRO
		//-----------------------------------------
		actionListener_recuperarLibro = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// Llamamos al método
				int c = call_recuperarLibro();
			}
		};
		view.getRecuperarLibro().addActionListener(actionListener_recuperarLibro);
		
		//LISTENER BOTON DE RECUPERARTODOS
		//-----------------------------------------
		actionListener_recuperarTodos = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// Llamamos al método
				int c = call_recuperarTodos();
			}
		};
		view.getRecuperarTodos().addActionListener(actionListener_recuperarTodos);
		
		//LISTENER BOTON DE ROTARFOTO
		//-----------------------------------------
		actionListener_rotarFoto = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// Llamamos al método
				int c = call_rotarImagen();
			}
		};
		view.getRotarImagen().addActionListener(actionListener_rotarFoto);
		
		//LISTENER BOTON DE CREARESPEJO
		//-----------------------------------------
		actionListener_crearEspejo = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// Llamamos al método
				int c = call_crearEspejo();
			}
		};
		view.getCrearEspejo().addActionListener(actionListener_crearEspejo);
		
		
		//EJERCICIO 1
		
		//LISTENER BOTON DE CAMBIAR AÑO
		//-----------------------------------------
		actionListener_cambiarAnyo = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// Llamamos al método
				int c = call_modificarAnyo();
			}
		};
		view.getCambiarAnyo().addActionListener(actionListener_cambiarAnyo);
		
		
		//EJERCICIO 2
		
		//LISTENER BOTON DE BUSCAR NUM PALABRAS....
		//-----------------------------------------
		actionListener_numPalabrasLongitudMenor = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// Llamamos al método
				int c = call_numPalabrasLongitudMenor();
			}
		};
		view.getBotonLongitud().addActionListener(actionListener_numPalabrasLongitudMenor);
	}

	private int call_compararContenido() {
		
		fichero1 = view.getFichero1().getText();
		fichero2 = view.getFichero2().getText();
		
		//Si al llamar alguna de las cajas está vacía, mostraremos un error
		if(fichero1.isEmpty() | fichero2.isEmpty()) {
			view.showError("Debes escribir el nombre de dos ficheros para compararlos");
		}
		//Si se ha escribido el nombre de los ficheros, llamamos a la función 
		else {
			try {
				resultado = model.compararContenido(fichero1, fichero2);
			} catch (IOException e) {
				//Mostramos error
				view.showError("No se ha encontrado alguno o ambos ficheros");
			}
			
			if (resultado) {
				view.getTextArea().setText("Los ficheros son iguales");
			}
			else {
				view.getTextArea().setText("Los ficheros no son iguales");
			}
		}
	
		return 1;
	}

	private int call_buscarPalabra() {

		//Obtenemos el nombre del fichero en el que buscar
		fichero1 = view.getFichero1().getText();
		palabra = view.getPalabra().getText();
		
		//Comprobamos que se haya escrito un fichero en el que buscar y una palabra que buscar
		if(fichero1.isEmpty()) {
			view.showError("Debes escribir el nombre de un fichero en la caja fichero1");
		}
		else if(palabra.isEmpty()) {
			view.showError("Debes introducir una palabra a buscar");
		}
		else {
			//Llamamos al método controlando los errores que puedan suceder
			try {
				posicion = model.buscarPalabra(fichero1, view.getPalabra().getText(), view.primeraAparicion());
			}
			catch(IOException e) {
				view.showError("No se ha encontrado el fichero " + fichero1);
			}
			
			//Según el resultado mostramos la información por el área de texto
			if(posicion > 0) {
				view.getTextArea().setText("La palabra ha sido encontrada en la línea: " + posicion);
			}
			else if(posicion < 0){
				view.getTextArea().setText("La palabra no ha sido encontrada en el fichero");
			}
		}
		return 1;
	}
	
	private int call_copiarFichero() {
		
		//Obtenemos nombre del fichero a copiar y nombre deseado del destino
		fichero1 = view.getFicheroOrigen().getText();
		fichero2 = view.getFicheroDestino().getText();
		
		File fich = new File(fichero2);
		
		//Comprobamos posibles errores
		if(fich.exists()) {
			view.showError("El fichero destino ya existe");
		}
		else if(fichero1.isEmpty()) {
			view.showError("Debes escribir un fichero para copiar");
		}
		else if(fichero2.isEmpty()) {
			view.showError("Debes escribir el nombre del fichero donde se copiará el contenido");
		}
		else {
			try {
				model.copiarFichero(fichero1, fichero2);
				view.mostrarMensaje("El fichero ha sido copiado con éxito");
			} catch (IOException e) {
				view.showError("No se ha encontrado " + fichero1);
			}
		}
		return 1;
	}
	
	private int call_ordenarFichero() {
		
		//Obtenemos nombre del fichero a ordenar y el nombre del fichero donde se mostrará el contenido ordenado
		fichero1 = view.getFicheroOrigen().getText();
		fichero2 = view.getFicheroDestino().getText();
					
		//Creamos los objetos File para poderlos introducir como parámetro en el método ordenarFichero de GestionDatos
		File fichOrigen = new File(fichero1);
		File fichDestino = new File(fichero2);
					
		//Comprobamos posibles errores
		if(fichDestino.exists()) {
			view.showError("El fichero destino ya existe");
		}
		else if(fichero1.isEmpty()) {
			view.showError("Debes escribir un fichero para copiar");
		}
		else if(fichero2.isEmpty()) {
				view.showError("Debes escribir el nombre del fichero donde se copiará el contenido");
		}
		else {
			try {
				model.ordenarFichero(fichOrigen, fichDestino, view.ordenNatural());
				view.mostrarMensaje("El fichero " + fichero1 + " ha sido copiado con éxito en el nuevo fichero " + fichDestino);
			} catch (IOException e) {
				view.showError("No se ha encontrado " + fichero1);
			}
		}
		return 1;
	}
	
	//Las excepciones que lanza son necesarias del método existeId y existeNombre
	private int call_guardarLibro() throws NumberFormatException, FileNotFoundException, ClassNotFoundException, IOException {
		
		//Controlamos que se hayan introducido todos los datos del libro (Se podría controlar que se introdujeran numeros en las cajas correspondientes, pero lo hacemos más genérico)
		if(view.getCajaAnyo().getText().isEmpty() || view.getCajaAutor().getText().isEmpty() || view.getCajaEditor().getText().isEmpty() || view.getCajaId().getText().isEmpty() || view.getCajaNumPags().getText().isEmpty() || view.getCajaTitulo().getText().isEmpty()){
			view.showError("Debes introducir toda la información del libro");
		}
		//Comprobamos que ningún libro tenga esa ID
		else if(model.existeId(Integer.valueOf(view.getCajaId().getText()))) {
			view.showError("Ya existe un libro con ese id. Escoge uno diferente");
		}
		//Comprobamos que ningún libro tiene el mismo nombre
		else if(model.existeTitulo(view.getCajaTitulo().getText())) {
			view.showError("Ya existe un libro con ese nombre. Escoge uno diferente");
		}
		else {
			//En caso de que esté todo correcto, creamos objeto libro asignandole como parámetros 
			Libro libro = new Libro(Integer.valueOf(view.getCajaId().getText()), Integer.valueOf(view.getCajaAnyo().getText()), Integer.valueOf(view.getCajaNumPags().getText()), view.getCajaTitulo().getText(), view.getCajaAutor().getText(), view.getCajaEditor().getText());
			//Llamamos a la función
			try {
				model.guardarLibro(libro);
				view.mostrarMensaje("Libro guardado en el fichero: " + libro.getTitulo() + ".dat");
			} catch (FileNotFoundException e) {
				view.showError("Ha ocurrido un error con el fichero.");
			} catch (IOException e) {
				view.showError("Ha ocurrido un error del tipo IO");
			}
		}
		return 1;
	}
	
	private int call_recuperarLibro() {
		
		//Comprobamos que se haya introducido un título para poder buscar
		if(view.getCajaTitulo().getText().isEmpty()){
			view.showError("Debes introducir un título para poder buscar");
		}
		else {
			//Si está bien, llamamos a la función
			try {
				Libro libro_recuperado = model.recuperarLibro(view.getCajaTitulo().getText());
				//Mostramos información por el textArea
				view.getTextArea().setText(libro_recuperado.toString());
			} 
			//Capturamos los errores (en teoría solo podría darse el error si no encontrara el libro)
			catch (FileNotFoundException e) {
				view.showError("No se ha encontrado ningún libro con ese título en el directorio");
			} catch (ClassNotFoundException e) {
				view.showError("No se ha encontrado ningún libro con ese título en el directorio");
			} catch (IOException e) {
				view.showError("No se ha encontrado ningún libro con ese título en el directorio");
			}
		}
		return 1;
	}
	
	private int call_recuperarTodos() {
		
		try {
			ArrayList<Libro> libros = model.recuperarTodos();
			if(!libros.isEmpty()) {
				view.getTextArea().setText("");
				view.mostrarMensaje("Libros recuperados con éxito");
				view.getTextArea().setText("LIBROS RECUPERADOS DEL DIRECTORIO 'LIBROS'\n");
				for(int i = 0; i < libros.size(); i++) {
					//Falta arreglar para que al presionar 2 veces no repita los libros
					view.getTextArea().setText(view.getTextArea().getText() + "\t-" + libros.get(i).getTitulo() + "\n");
				}
				libros.clear();
			}
			else {
				//Si el array no contiene elementos, mostramos error porque no hay libros
				view.showError("No se han encontrado libros en el directorio");
			}
		}
		//Controlamos el único error que puede suceder (en teoría)
		catch (FileNotFoundException e) {
			e.printStackTrace();
			view.showError("Ha ocurrido un error");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			view.showError("Ha ocurrido un error");
		} catch (IOException e) {
			view.showError("Ha ocurrido un error");
		}
		
		return 1;
	}
	
	private int call_rotarImagen() {
		
		if(view.getFicheroOrigen().getText().isEmpty() || view.getFicheroDestino().getText().isEmpty()) {
			view.showError("Debes escribir una imagen para rotar (fich. origen) y el nombre del ficheor nuevo (fich. destino");
		}
		else{
			try {
				model.rotarImagen(view.getFicheroOrigen().getText(), view.getFicheroDestino().getText());
				view.mostrarMensaje("Imagen rotada con éxito: " + view.getFicheroDestino().getText());
			} catch (IOException e) {
				view.showError("Ha ocurrido un error con el fichero");
			}
		}
		return 1;
	}
	
	private int call_crearEspejo() {
		
		if(view.getFicheroOrigen().getText().isEmpty() || view.getFicheroDestino().getText().isEmpty()) {
			view.showError("Debes escribir una imagen para crear el espejo (fich. origen) y el nombre del ficheor nuevo (fich. destino");
		}
		else{
			try {
				model.crearEspejo(view.getFicheroOrigen().getText(), view.getFicheroDestino().getText());
				view.mostrarMensaje("Espejo creado: " + view.getFicheroDestino().getText());
			} catch (IOException e) {
				view.showError("Ha ocurrido un error con el fichero");
			}
		}
		return 1;
	}
	
	//EJERCICIO 1
	private int call_modificarAnyo() {
		//Si están los dos campos vacíos, mostramos info
		if(view.getCajaTitulo().getText().isEmpty() && view.getCajaAnyo().getText().isEmpty()){
			view.mostrarMensaje("Debes escribir el titulo del libro a modificar y el nuevo año");
		}
		//Si algún campo está vacío, mostramos info
		else if(view.getCajaAnyo().getText().isEmpty() || view.getCajaTitulo().getText().isEmpty()) {
			view.mostrarMensaje("Te falta escribir el título del libro o el año nuevo");
		}
		else {
			//Si el libro no existe, mostramos error
			try {
					model.modificarAnyo(view.getCajaTitulo().getText(), Integer.parseInt(view.getCajaAnyo().getText()));
					view.mostrarMensaje("Año del libro: " + view.getCajaTitulo().getText() + " modificado con éxito");
				}
			//Excepciones a controlar
			catch (NumberFormatException e) {
				e.printStackTrace();
				view.showError("No has introducido un formato de año válido");
			} catch (FileNotFoundException e) {
				view.showError("El libro " + view.getCajaTitulo().getText() + " no existe.");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return 1;
	}
	
	//EJERCICIO 2
	private int call_numPalabrasLongitudMenor() {
		
		if(view.getCajaLongitud().getText().isEmpty() || view.getFichero1().getText().isEmpty()) {
			view.showError("Debes introducir un fichero a analizar (fichero 1) y la longitud máxima que tienen que tener las palabras");
		}
		else {
			int c = 0;
			try {
				//Llamamos al método si está todo correcto y recuperamos el num de palabras que coinciden en variable 'c'
				c = model.numPalabrasLongitudMenor(view.getFichero1().getText(), Integer.parseInt(view.getCajaLongitud().getText()));
			} catch (NumberFormatException e) {
				view.showError("No has introducido una longitud válida");
			} catch (IOException e) {
				view.showError("No se ha encontrado el fichero " + view.getFichero1().getText());
			}
			//Si ha devuelto -1, es que ninguna palabra coincide
			if(c == -1) {
				view.showError("No existe ninguna palabra que tenga menos de " + view.getCajaLongitud().getText() + " carácteres");
			}
			else {
				view.getTextArea().setText( c + " palabras tienen menos de " + view.getCajaLongitud().getText() + " carácteres en el fichero");
			}
		}
		
		return 1;
	}
	
}
