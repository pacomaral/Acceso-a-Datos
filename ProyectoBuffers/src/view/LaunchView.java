package view;

import java.awt.Dimension;

import javax.swing.*;

public class LaunchView extends JFrame {

	private JButton comparar,buscar, ordenarContenido, copiarFichero, anyadirLibro, recuperarLibro, recuperarTodos;
	private JTextArea textArea;
	private JTextField fichero1,fichero2,palabra, ficheroOrigen, ficheroDestino, cajaTitulo, cajaId, cajaAutor, cajaAnyo, cajaNumPags, cajaEditor;
	private JLabel label_f1,label_f2,label_pal, etiquetaFichOrdenar, etiquetaFichDestino, etiquetaTitulo, etiquetaId, etiquetaAutor, etiquetaAnyo, etiquetaNumPags, etiquetaEditor;
	private JRadioButton opcion1, opcion2, opcion3, opcion4;
	private ButtonGroup grupo1, grupo2;
	private JButton rotarImagen, crearEspejo;
	
	private JPanel panel;
	
	public LaunchView() {
		
		setBounds(300,300,1760,542);
		setTitle("Proyecto Buffers");	
		panel = new JPanel();
		
		comparar = new JButton("Comparar contenido");
		comparar.setPreferredSize(new Dimension(150, 26));
		buscar = new JButton("Buscar palabra");
		buscar.setPreferredSize(new Dimension(150, 26));
					
		fichero1 = new JTextField("",10);
		fichero2 = new JTextField("",10);
		palabra = new JTextField("",10);
		ficheroOrigen = new JTextField("",10);
		ficheroDestino = new JTextField("",10);
		
		label_f1 = new JLabel("Fichero 1:");
		label_f2 = new JLabel("Fichero 2:");
		label_pal = new JLabel("Palabra:");

		textArea = new JTextArea(15, 70);
		textArea.setBounds(25,25,25,25);
		textArea.setEditable(false);		
		
		//Configurando los nuevos componentes de la aplicación
		opcion1 = new JRadioButton("Primera aparición", true);
		opcion2 = new JRadioButton("Última aparición", false);
		grupo1 = new ButtonGroup();
		grupo1.add(opcion1);
		grupo1.add(opcion2);
		
		opcion3 = new JRadioButton("Orden natural", true);
		opcion4 = new JRadioButton("Orden inverso", false);
		grupo2 = new ButtonGroup();
		grupo2.add(opcion3);
		grupo2.add(opcion4);
		
		ordenarContenido = new JButton("Ordenar fichero");
		ordenarContenido.setPreferredSize(new Dimension(150, 26));
		
		copiarFichero = new JButton("Copiar fichero");
		copiarFichero.setPreferredSize(new Dimension(150, 26));
		
		etiquetaFichOrdenar = new JLabel("Fichero origen: ");
		etiquetaFichDestino = new JLabel("Fichero destino: ");
		
		//Configuramos componentes Act 1g
		etiquetaId = new JLabel("Id");
		etiquetaTitulo = new JLabel("Titulo");
		etiquetaAnyo = new JLabel("Año");
		etiquetaAutor = new JLabel("Autor");
		etiquetaNumPags = new JLabel("Num. Páginas");
		etiquetaEditor = new JLabel("Editor");
		
		cajaId = new JTextField("",10);
		cajaTitulo = new JTextField("",10);
		cajaAnyo = new JTextField("",10);
		cajaAutor = new JTextField("",10);
		cajaNumPags = new JTextField("",10);
		cajaEditor = new JTextField("",10);
		
		anyadirLibro = new JButton("Añadir libro");
		anyadirLibro.setPreferredSize(new Dimension(150, 26));
		recuperarLibro = new JButton("Recuperar libro");
		recuperarLibro.setPreferredSize(new Dimension(150, 26));
		recuperarTodos = new JButton("Recuperar todos");
		recuperarTodos.setPreferredSize(new Dimension(150, 26));
		
		rotarImagen = new JButton("Rotar 90º");
		buscar.setPreferredSize(new Dimension(150, 26));
		
		crearEspejo = new JButton("Crear espejo");
		buscar.setPreferredSize(new Dimension(150, 26));
		
		panel.add(comparar);
		panel.add(buscar);
		panel.add(label_f1);
		panel.add(fichero1);
		panel.add(label_f2);
		panel.add(fichero2);
		panel.add(label_pal);
		panel.add(palabra);
		panel.add(opcion1);
		panel.add(opcion2);
		panel.add(textArea);
		
		panel.add(ordenarContenido);
		panel.add(copiarFichero);
		panel.add(etiquetaFichOrdenar);
		panel.add(ficheroOrigen);
		panel.add(etiquetaFichDestino);
		panel.add(ficheroDestino);
		panel.add(rotarImagen);
		panel.add(crearEspejo);
		panel.add(opcion3);
		panel.add(opcion4);
		
		panel.add(etiquetaId);
		panel.add(cajaId);
		panel.add(etiquetaTitulo);
		panel.add(cajaTitulo);
		panel.add(etiquetaAnyo);
		panel.add(cajaAnyo);
		panel.add(etiquetaAutor);
		panel.add(cajaAutor);
		panel.add(etiquetaNumPags);
		panel.add(cajaNumPags);
		panel.add(etiquetaEditor);
		panel.add(cajaEditor);
		panel.add(anyadirLibro);
		panel.add(recuperarLibro);
		panel.add(recuperarTodos);
		
		
        // Añadimos el JPanel al JFrame
        this.getContentPane().add(panel);		
		
	}	
	
	public JButton getRotarImagen() {
		return rotarImagen;
	}

	public void setRotarImagen(JButton rotarImagen) {
		this.rotarImagen = rotarImagen;
	}

	public JButton getCrearEspejo() {
		return crearEspejo;
	}

	public void setCrearEspejo(JButton crearEspejo) {
		this.crearEspejo = crearEspejo;
	}

	public JTextField getCajaTitulo() {
		return cajaTitulo;
	}

	public void setCajaTitulo(JTextField cajaTitulo) {
		this.cajaTitulo = cajaTitulo;
	}

	public JTextField getCajaId() {
		return cajaId;
	}

	public void setCajaId(JTextField cajaId) {
		this.cajaId = cajaId;
	}

	public JTextField getCajaAutor() {
		return cajaAutor;
	}

	public void setCajaAutor(JTextField cajaAutor) {
		this.cajaAutor = cajaAutor;
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

	public JTextField getCajaEditor() {
		return cajaEditor;
	}

	public void setCajaEditor(JTextField cajaEditor) {
		this.cajaEditor = cajaEditor;
	}

	public JButton getAnyadirLibro() {
		return anyadirLibro;
	}

	public void setAnyadirLibro(JButton anyadirLibro) {
		this.anyadirLibro = anyadirLibro;
	}

	public JButton getRecuperarLibro() {
		return recuperarLibro;
	}

	public void setRecuperarLibro(JButton recuperarLibro) {
		this.recuperarLibro = recuperarLibro;
	}

	public JButton getRecuperarTodos() {
		return recuperarTodos;
	}

	public void setRecuperarTodos(JButton recuperarTodos) {
		this.recuperarTodos = recuperarTodos;
	}

	public JButton getComparar() {
		return comparar;
	}

	public void setComparar(JButton comparar) {
		this.comparar = comparar;
	}

	public JButton getBuscar() {
		return buscar;
	}

	public void setBuscar(JButton buscar) {
		this.buscar = buscar;
	}
	
	public JButton getCopiar() {
		return this.copiarFichero;
	}
	
	public JButton getOrdenar() {
		return this.ordenarContenido;
	}
	
	public void setCopiar(JButton boton) {
		this.copiarFichero = boton;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
			
	public JTextField getFichero1() {
		return fichero1;
	}

	public void setFichero1(JTextField fichero1) {
		this.fichero1 = fichero1;
	}

	public JTextField getFichero2() {
		return fichero2;
	}
	
	public JTextField getFicheroOrigen() {
		return ficheroOrigen;
	}
	
	public JTextField getFicheroDestino() {
		return ficheroDestino;
	}

	public void setFichero2(JTextField fichero2) {
		this.fichero2 = fichero2;
	}
	
	public JTextField getPalabra() {
		return palabra;
	}

	public void showError(String m){
		JOptionPane.showMessageDialog(this.panel,
			    m,
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	//Método para saber si está seleccionada la primera aparición o no
	public boolean primeraAparicion() {
		if(opcion1.isSelected()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Método para saber si debemos ordenar el fichero de forma natural o inversa
	public int ordenNatural() {
		if(opcion3.isSelected()) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	//Método para mostrar un mensaje informativo durante la ejecución del programa
	public void mostrarMensaje(String palabra) {
		JOptionPane.showMessageDialog(this, palabra);
	}


}
