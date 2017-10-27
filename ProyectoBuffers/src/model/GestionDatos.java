package model;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;

import view.LaunchView;

public class GestionDatos {

	private LaunchView view;
	private FileReader fr1, fr2, fr3;
	private BufferedReader br1, br2, br3;
	private StringBuilder contenido1, contenido2;
	
	private String cadena;
	
	//Variables necesarias para método buscarPalabra
	private int contador, posicion;
	private boolean solucion;
	private ArrayList<Integer> listaPosiciones = new ArrayList<Integer>(); //Aquí iremos almacenando los nums de las lineas donde se encuentra la palabra a buscar
	
	//Objetos y variables necesarias para el método ordenarFichero
	private FileReader fr4=null;
	private BufferedReader br4=null;
	private BufferedWriter bw=null;
	private ArrayList<String> listaPalabras;
	private String[] arrayPalabras;
	private String palabra;
	private int numElementos, contador3;
	
	//Objetos y variables necesarias para el método copiarFichero
	private FileInputStream fis=null;
	private FileOutputStream fos=null;
	private byte[] buffer;
	private int numBytes, contador2, c;
	
	//Objetos y variables necesarias para el métodos del libro
	private ObjectOutputStream ous=null;
	private ObjectInputStream ois=null;
	private Libro libro=null;				//Lo utilizaremos para retornarlo en un método
	private ArrayList<Libro> listaLibros = new ArrayList();
	
	//Objetos y variables necesarias para método de rotar imagen
	private BufferedImage bi;
	
	public GestionDatos() {

	}

	//TODO: Implementa una función para abrir ficheros
	public Object abrirFichero(String fichero, int tipo_objeto) throws FileNotFoundException {
		
		//Creamos el objeto primer, inicializandolo a null
		Object obj = null;
		
		//Si el parámetro es 1, devolveremos un FileReader
		if(tipo_objeto == 1) {
			obj = (FileReader) new FileReader(fichero);
		}
		
		//Si el parámetro es 2, creamos FileInputStream
		if(tipo_objeto == 2) {
			obj = (FileInputStream) new FileInputStream(fichero);
		}
		
		//Si el parámetro es 3, creamos FileOutputStream
		if(tipo_objeto == 3) {
			obj = (FileOutputStream) new FileOutputStream(fichero);
		}
		
		//Devolvemos el filereader
		return obj;
	}
	
	public BufferedReader crearBuffer(FileReader fr) throws FileNotFoundException {
		//Creamos el bufferedReader
		BufferedReader br = new BufferedReader(fr);
		
		//Devolvemos el BufferedReader
		return br;
	}
	
	
	
	//TODO: Implementa una función para cerrar ficheros
	public void cerrar(Closeable c) throws IOException {
		try {
			c.close();
		}
		catch(Exception e) {
			view.showError("Error al cerrar el flujo");
		}
	}
	
	
	public boolean compararContenido (String fichero1, String fichero2) throws IOException{
		
		//Llamamos a los métodos para crear filereader y bufferedreader
		fr1 = (FileReader) abrirFichero(fichero1, 1);
		fr2 = (FileReader) abrirFichero(fichero2, 1);
		br1 = crearBuffer(fr1);
		br2 = crearBuffer(fr2);
		
		//Creamos dos stringbuilder para almacenar el contenido de los ficheros
		contenido1 = new StringBuilder();
		contenido2 = new StringBuilder();
		
		//Guardamos el contenido de los ficheros
		while( (cadena = br1.readLine()) != null ) {
			contenido1.append(cadena);
			contenido1.append("\n");
			
		}
		while( (cadena = br2.readLine()) != null) {
			contenido2.append(cadena);
			contenido2.append("\n");
		}
		
		//Cerramos el fichero y el flujo de datos
		cerrar(fr1);
		cerrar(fr2);
		cerrar(br1);
		cerrar(br2);
		
		
		
		//Comparamos el contenido de los ficheros
		if(contenido1.toString().equals(contenido2.toString())) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public int buscarPalabra (String fichero1, String palabra, boolean primera_aparicion) throws IOException{
		
		//Inicializamos variables
		solucion = false;
		contador = 1;
		
		//Eliminamos todos los valores de la Lista de posiciones
		listaPosiciones.clear();
		
		//Creamos el filereader
		fr3 = (FileReader) abrirFichero(fichero1, 1);
		
		
		//Creamos el bufferedReader para poder leer el fichero
		br3 = crearBuffer(fr3);
		
		while((cadena = br3.readLine()) != null) {
			if(cadena.equals(palabra)) {
				listaPosiciones.add(contador);
				solucion = true;
			}
			contador++;
		}
		
		//Cerramos filereader y flujo de datos
		cerrar(fr3);
		cerrar(br3);
		
		//Si la palabra se encuentra en el fichero
		if(solucion) {
			if(primera_aparicion) {
				//Si queremos la primera aparición, cogemos el primer valor de la Lista
				return listaPosiciones.get(0);
			}
			else {
				//Si queremos la última aparición, cogemos el último valor de la Lista
				return listaPosiciones.get(listaPosiciones.size()-1);
			}
		}
		else {
			//Si no se encuentra en el fichero, el método devolverá -1
			return -1;
		}
	}	
	
	public void ordenarFichero(File origen, File destino, int tipo_orden) throws IOException {
		
		//Inicializamos lista
		listaPalabras = new ArrayList<String>();
		
		//Primero leemos el contenido del fichero origen y guardamos las palabras en un array
		fr4 = (FileReader)abrirFichero(origen.getName(), 1);
		br4 = crearBuffer(fr4);
		
		while ((cadena = br4.readLine()) != null) {
			//Añadimos la palabra al arraylist
			listaPalabras.add(cadena);		
		}
		
		//Cerramos filereader y bufferedreader
		cerrar(fr4);
		cerrar(br4);
		
		//Ordenamos el arrayList mediante el método de BURBUJA
		
		//Pasamos el ArrayList a Array para ordenarlo
		arrayPalabras = new String[listaPalabras.size()];
		arrayPalabras = listaPalabras.toArray(arrayPalabras);
		
		
		//Recorremos el ArrayList
		for(int i=1; i<= arrayPalabras.length; i++) {
			
			//Vamos comparando si un valor es mayor que el de detrás y los intercambiamos
			for(int s=0 ; s < arrayPalabras.length - i; s++) {	
				
				//Si 's' es mayor que 's + 1' (valores arraylist), intercambiamos posiciones
				if(arrayPalabras[s].compareTo(arrayPalabras[s + 1]) > 0) {
					palabra = arrayPalabras[s];
					//Pasamos 's+1' a la posición anterior 
					arrayPalabras[s] = arrayPalabras[s+1];
					//Pasamos 's' a la posicion posterior
					arrayPalabras[s+1] = palabra;
				}
				
			}
		}
		
		//Si lo queremos ordenado de Z a A invertimos el array, si lo queremos de A a Z no haremos nada
		if(tipo_orden == -1) {
	        for (int i = 0; i<arrayPalabras.length; i++) {
	        	//Guardamos la posición del ultimo valor del string en un int
	        	numElementos = arrayPalabras.length - 1;
	        	//Ponemos el primer elemento en la última posición
	            arrayPalabras[numElementos] = arrayPalabras[i];
	            //Quitamos un valor a numElementos para que valla cogiendo del final al principio todos los valores
	            numElementos--;
	        }
		}
		
		//Hay que escribir el Array en el fichero destino
		
		//Creamos el bufferedWritter indicandole que escribiremos en el fichero destino
		bw = new BufferedWriter(new FileWriter(destino));
		
		//Vamos escribiendo los valores del array en el fichero destino
		for(int i = 0; i < arrayPalabras.length; i++) {
			bw.write(arrayPalabras[i]);
			bw.newLine(); 								//Con esto creamos un salto de línea
		}
		
		//Finalmente cerramos el buffer
		cerrar(bw);
	}
	
	public void copiarFichero(String fichOrigen, String fichDestino) throws IOException {
		
		//Inicializamos objetos necesarios para leer y escribir bytes
		fis = (FileInputStream) abrirFichero(fichOrigen, 2);
		fos = (FileOutputStream) abrirFichero(fichDestino, 2);
		
		//Creamos objeto file para poder obtener la cantidad de bytes que tiene
		File f = new File(fichOrigen);
		//Guardamos el num de bytes en un entero
		numBytes = (int) f.length();
		
		//Inicializamos el array
		buffer = new byte[numBytes];
		
		//Inicializamos el contador a 0
		contador2 = 0;
		
		//Cuando sea -1 habrá terminado de recorrer el fichero
		while((c = fis.read())!= -1) {
			buffer[contador2] = (byte) c;
			//Escribimos el byte en el fichero destino
			fos.write(c);
			//Sumamos el contador
			contador2++;
		}
	
		//Cerramos flujo de bytes
		cerrar(fis);
		cerrar(fos);
	}
	
	public void guardarLibro(Libro libro) throws FileNotFoundException, IOException {
		
		//Creamos ObjectOutputStream con fos, de manera que el nombre del fichero será
		//el título del libro
		ous = new ObjectOutputStream(new FileOutputStream("libros/" + libro.getTitulo()));
		
		//Escribimos el objeto en el fichero
		ous.writeObject(libro);
		
		//Cerramos el flujo
		cerrar(ous);
	}
	
	public Libro recuperarLibro(String titulo) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		//Creamos objetos necesarios
		ois = new ObjectInputStream(new FileInputStream("libros/" +titulo));
		//Almacenamos el objeto libro
		libro = (Libro) ois.readObject();
		
		//Lo devolvemos
		return libro;
	}
	
	public ArrayList<Libro> recuperarTodos() throws FileNotFoundException, ClassNotFoundException, IOException{
		
		//Creamos un objeto file del directorio del proyecto
		File f = new File("libros");
		
		//Metemos en un array todos los ficheros de ese directorio
		File[] arrayFicheros = f.listFiles();
		
		//Recorremos el array de ficheros y añadimos libros a la lista
		for(int i = 0; i < arrayFicheros.length; i++) {
			listaLibros.add(this.recuperarLibro(arrayFicheros[i].getName()));
		}
		
		return listaLibros;
	}
	
	//Método para comprobar si existe un libro en el directorio con una Id o no
	public boolean existeId(int id) throws FileNotFoundException, ClassNotFoundException, IOException {
		
		boolean existe = false;
		
		File f = new File("libros");
		
		File[] arrayFicheros = f.listFiles();
		
		for(int i = 0; i < arrayFicheros.length; i++) {
			listaLibros.add(this.recuperarLibro(arrayFicheros[i].getName()));
		}
		
		for(int i= 0; i < listaLibros.size(); i++) {
			if(listaLibros.get(i).getId() == id) {
				existe = true;
			}
		}
		
		listaLibros.clear();  //Limpiamos la lista para evitar errores
		
		return existe;
	}
	
	public boolean existeTitulo(String titulo) throws FileNotFoundException, ClassNotFoundException, IOException {
		
		boolean existe = true;
		
		File f = new File("libros");
		
		File[] arrayFicheros = f.listFiles();
		
		for(int i = 0; i < arrayFicheros.length; i++) {
			listaLibros.add(this.recuperarLibro(arrayFicheros[i].getName()));
		}
		
		for(int i= 0; i < listaLibros.size(); i++) {
			if(listaLibros.get(i).getTitulo() == titulo) {
				existe = true;
			}
		}
		
		listaLibros.clear();  //Limpiamos la lista para evitar errores
		
		return existe;
	}
	
	public void rotarImagen(String fichOrigen, String fichDestino) throws IOException {
		
		File fileOrigen = new File(fichOrigen);
		File fileDestino = new File(fichDestino);
		//Creamos el bufferedimage
		bi = ImageIO.read(fileOrigen);
		
		//Creamos matriz con la altura y anchura de la imagen
		int[][] matrizRotada = new int[bi.getWidth()][bi.getHeight()];
		
		
		
		//Contamos cuantas columnas tiene
		int x = matrizRotada[0].length - 1;
		
		//Con esto rotamos 90º la matriz
		for(int i = 0; i < matrizRotada.length - 1; i++) {
			for(int j = 0; j < x; j++) {
				matrizRotada[j][x-i] = bi.getRGB(i, j);
			}
		}
		
		
		for(int i=0; i < matrizRotada.length; i++) {
			for(int j=0; j < matrizRotada.length; j++) {
				bi.setRGB(i, j, matrizRotada[i][j]);
			}
		}
		
		//Escribimos la nueva imagen en el fichero destino
		ImageIO.write(bi, "jpg", fileDestino);
		
	}
	
	
	//Método para crear Espejo de una imagen
	public void crearEspejo(String fichOrigen, String fichDestino) throws IOException {
		
		File fileOrigen = new File(fichOrigen);
		File fileDestino = new File(fichDestino);
		
		//Creamos el bufferedimage
		bi = ImageIO.read(fileOrigen);
		
		//Creamos matriz con la altura y anchura de la imagen
		int[][] matriz = new int[bi.getWidth()][bi.getHeight()];
		int[][] matriz2 = new int[bi.getWidth()][bi.getHeight()];
		int[][] matriz3 = new int[bi.getWidth()][bi.getHeight()];
		
		//Contamos cuantas columnas tiene
		int x = matriz[0].length - 1;
		
		//Con esto se crea el espejo
		for(int i = 0; i < matriz.length - 1; i++) {
			for(int j = 0; j < x; j++) {
				//matriz[matriz.length-1-i][j] = bi.getRGB(i,j);
				matriz[i][j] = bi.getRGB(matriz.length-1-i,j);
			}
		}
		
		//ESTO NO HACE FALTA
		
		//Giramos 2 veces para que quede el espejo bien (en matrices nuevas)
		/*for(int i = 0; i < matriz.length - 1; i++) {
			for(int j = 0; j < x; j++) {
				matriz2[j][x-i] = matriz[i][j];
			}
		}
		for(int i = 0; i < matriz.length - 1; i++) {
			for(int j = 0; j < x; j++) {
				matriz3[j][x-i] = matriz2[i][j];
			}
		}*/
		
		
		
		//Una vez tenemos la matriz correcta, escribimos en el bi
		for(int i=0; i < matriz.length; i++) {
			for(int j=0; j < matriz.length; j++) {
				bi.setRGB(i, j, matriz[i][j]);
			}
		}
		
		//Escribimos la nueva imagen en el fichero destino
		ImageIO.write(bi, "jpg", fileDestino);
	
		
	}
	
	

}
