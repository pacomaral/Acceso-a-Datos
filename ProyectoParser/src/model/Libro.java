package model;


import java.io.Serializable;
import java.util.ArrayList;

public class Libro implements Serializable{

	private int anyo, numPaginas;
	private String titulo, editor;
	private ArrayList<String> autores;
	
	//Constructor
	public Libro(int anyo, int numPag, String titulo, ArrayList<String> autores, String editor) {
		this.anyo=anyo;
		this.numPaginas=numPag;
		this.titulo=titulo;
		this.autores=autores;
		this.editor=editor;
	}
	
	public int getAnyo() {
		return anyo;
	}
	
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	
	public int getNumPaginas() {
		return numPaginas;
	}
	
	
	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAutores() {
		
		StringBuilder resultado = new StringBuilder();
		
		for(int i=0; i<autores.size(); i++) {
			//Añadimos cada elemento del arraylist a la cadena
			resultado.append(autores.get(i));
			//Si el elemento no es el último, añadimos coma	
			if(!(autores.size() ==  (i+1))) {
				resultado.append(", ");
			}
		}
		
		return resultado.toString();
	}
	
	public ArrayList<String> getListaAutores(){
		return this.autores;
	}
	
	public void setNombre(String autor) {
		autores.add(autor);
	}
	
	public String getEditor() {
		return editor;
	}
	
	public void setEditor(String editor) {
		this.editor = editor;
	}
	
	public String toString() {
		String cadena = "Titulo: " + this.getTitulo() + "\nAutores: " + this.getAutores() + "\nAño: " + this.getAnyo() + "\nNúm. Págs: " + this.getNumPaginas() + "\nEditor: " + this.getEditor() + "\n\n\n";
		return cadena;
	}
}
