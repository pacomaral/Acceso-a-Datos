package model;

import java.io.Serializable;

public class Libro implements Serializable{

	private int id, anyo, numPaginas;
	private String titulo, autor, editor;
	
	//Constructor
	public Libro(int id, int anyo, int numPag, String titulo, String autor, String editor) {
		this.id=id;
		this.anyo=anyo;
		this.numPaginas=numPag;
		this.titulo=titulo;
		this.autor=autor;
		this.editor=editor;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public String getEditor() {
		return editor;
	}
	
	public void setEditor(String editor) {
		this.editor = editor;
	}
	
	public String toString() {
		String cadena = "Id: " + this.getId() + "\nTitulo: " + this.getTitulo() + "\nAutor: " + this.getAutor() + "\nAño: " + this.getAnyo() + "\nNúm. Págs: " + this.getNumPaginas() + "\nEditor: " + this.getEditor();
		return cadena;
	}
}
