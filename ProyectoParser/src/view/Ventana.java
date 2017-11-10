package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JLabel etiquetaFichero, etiquetaTitulo, etiquetaAutor, etiquetaEditor, etiquetaAnyo, etiquetaNumPags, etiquetaContadorLibros;
	private JTextField cajaFichero, cajaTitulo, cajaAutor, cajaAutor2, cajaEditor, cajaAnyo, cajaNumPags, cajaFicheroXML;
	private JButton botonParsear, botonAnyadirLibro, botonCrearXML;
	private JScrollPane scrollPane;
	private JTextArea areaTexto;

	private int contador=0;
	/**
	 * Create the frame.
	 */
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 773, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		etiquetaFichero = new JLabel("Fichero XML a parsear");
		etiquetaFichero.setBounds(73, 50, 191, 20);
		contentPane.add(etiquetaFichero);
		
		cajaFichero = new JTextField();
		cajaFichero.setBounds(73, 86, 146, 26);
		contentPane.add(cajaFichero);
		cajaFichero.setColumns(10);
		
		botonParsear = new JButton("Parsear información");
		botonParsear.setBounds(42, 137, 222, 29);
		contentPane.add(botonParsear);
		
		botonAnyadirLibro = new JButton("Añadir libro a la lista");
		botonAnyadirLibro.setBounds(73, 435, 222, 29);
		contentPane.add(botonAnyadirLibro);
		
		botonCrearXML = new JButton("Crear XML");
		botonCrearXML.setBounds(353, 435, 222, 29);
		contentPane.add(botonCrearXML);
		
		etiquetaTitulo = new JLabel("Titulo");
		etiquetaTitulo.setBounds(42, 309, 69, 20);
		contentPane.add(etiquetaTitulo);
		
		etiquetaNumPags = new JLabel("Páginas");
		etiquetaNumPags.setBounds(303, 309, 69, 20);
		contentPane.add(etiquetaNumPags);
		
		etiquetaAutor = new JLabel("Autor");
		etiquetaAutor.setBounds(504, 309, 69, 20);
		contentPane.add(etiquetaAutor);
		
		etiquetaEditor = new JLabel("Editor");
		etiquetaEditor.setBounds(42, 361, 69, 20);
		contentPane.add(etiquetaEditor);
		
		etiquetaAnyo = new JLabel("Año");
		etiquetaAnyo.setBounds(303, 361, 69, 20);
		contentPane.add(etiquetaAnyo);
		
		etiquetaContadorLibros = new JLabel(contador + " libros añadidos");
		etiquetaContadorLibros.setBounds(305, 503, 140, 20);
		contentPane.add(etiquetaContadorLibros);
		
		cajaTitulo = new JTextField();
		cajaTitulo.setBounds(96, 306, 162, 26);
		contentPane.add(cajaTitulo);
		cajaTitulo.setColumns(10);
		
		cajaNumPags = new JTextField();
		cajaNumPags.setBounds(376, 306, 69, 26);
		contentPane.add(cajaNumPags);
		cajaNumPags.setColumns(10);
		
		cajaAutor = new JTextField();
		cajaAutor.setBounds(576, 306, 115, 26);
		contentPane.add(cajaAutor);
		cajaAutor.setColumns(10);
		
		cajaAutor2 = new JTextField();
		cajaAutor2.setBounds(576, 345, 115, 26);
		contentPane.add(cajaAutor2);
		cajaAutor2.setColumns(10);
		
		cajaEditor = new JTextField();
		cajaEditor.setBounds(96, 358, 162, 26);
		contentPane.add(cajaEditor);
		cajaEditor.setColumns(10);
		
		cajaAnyo = new JTextField();
		cajaAnyo.setBounds(374, 358, 71, 26);
		contentPane.add(cajaAnyo);
		cajaAnyo.setColumns(10);
		
		cajaFicheroXML = new JTextField();
		cajaFicheroXML.setBounds(606, 436, 103, 26);
		contentPane.add(cajaFicheroXML);
		cajaFicheroXML.setColumns(10);
		
		

		areaTexto = new JTextArea();
		areaTexto.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		areaTexto.setBounds(350, 21, 222, 292);
		areaTexto.setEditable(false);
		scrollPane = new JScrollPane(areaTexto);
		scrollPane.setBounds(303, 21, 406, 219);
		
		contentPane.add(scrollPane);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(0, 274, 751, 2);
		contentPane.add(separator);
	}

	public JTextField getCajaAutor2() {
		return cajaAutor2;
	}

	public void setCajaAutor2(JTextField cajaAutor2) {
		this.cajaAutor2 = cajaAutor2;
	}

	public JTextField getCajaFicheroXML() {
		return cajaFicheroXML;
	}

	public void setCajaFicheroXML(JTextField cajaFicheroXML) {
		this.cajaFicheroXML = cajaFicheroXML;
	}

	public JLabel getEtiquetaContadorLibros() {
		return etiquetaContadorLibros;
	}

	public void setEtiquetaContadorLibros(JLabel etiquetaContadorLibros) {
		this.etiquetaContadorLibros = etiquetaContadorLibros;
	}

	public JTextField getCajaTitulo() {
		return cajaTitulo;
	}

	public void setCajaTitulo(JTextField cajaTitulo) {
		this.cajaTitulo = cajaTitulo;
	}

	public JTextField getCajaAutor() {
		return cajaAutor;
	}

	public void setCajaAutor(JTextField cajaAutor) {
		this.cajaAutor = cajaAutor;
	}

	public JTextField getCajaEditor() {
		return cajaEditor;
	}

	public void setCajaEditor(JTextField cajaEditor) {
		this.cajaEditor = cajaEditor;
	}

	public JTextField getCajaAnyo() {
		return cajaAnyo;
	}

	public void setCajaAnyo(JTextField cajaAnyo) {
		this.cajaAnyo = cajaAnyo;
	}

	public JTextField getCajaNumPags() {
		return cajaNumPags;
	}

	public void setCajaNumPags(JTextField cajaNumPags) {
		this.cajaNumPags = cajaNumPags;
	}

	public JButton getBotonAnyadirLibro() {
		return botonAnyadirLibro;
	}

	public void setBotonAnyadirLibro(JButton botonAnyadirLibro) {
		this.botonAnyadirLibro = botonAnyadirLibro;
	}

	public JButton getBotonCrearXML() {
		return botonCrearXML;
	}

	public void setBotonCrearXML(JButton botonCrearXML) {
		this.botonCrearXML = botonCrearXML;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JTextArea getAreaTexto() {
		return areaTexto;
	}

	public void setAreaTexto(JTextArea areaTexto) {
		this.areaTexto = areaTexto;
	}
	
	public void ponerTexto(String txt) {
		areaTexto.setText(txt);
	}

	public JLabel getEtiquetaFichero() {
		return etiquetaFichero;
	}


	public void setEtiquetaFichero(JLabel etiquetaFichero) {
		this.etiquetaFichero = etiquetaFichero;
	}


	public JTextField getCajaFichero() {
		return cajaFichero;
	}


	public void setCajaFichero(JTextField cajaFichero) {
		this.cajaFichero = cajaFichero;
	}


	public JButton getBotonParsear() {
		return botonParsear;
	}


	public void setBotonParsear(JButton botonParsear) {
		this.botonParsear = botonParsear;
	}
	
	//Método para mostrar un error
	public void mostrarError(String m){
		JOptionPane.showMessageDialog(this.contentPane,
			    m,
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	//Método para mostrar un mensaje informativo durante la ejecución del programa
	public void mostrarMensaje(String palabra) {
		JOptionPane.showMessageDialog(this, palabra);
	}
}
