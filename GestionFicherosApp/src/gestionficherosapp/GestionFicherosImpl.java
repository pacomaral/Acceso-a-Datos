package gestionficherosapp;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import gestionficheros.FormatoVistas;
import gestionficheros.GestionFicheros;
import gestionficheros.GestionFicherosException;
import gestionficheros.TipoOrden;

public class GestionFicherosImpl implements GestionFicheros {
	private File carpetaDeTrabajo = null;
	private Object[][] contenido;
	private int filas = 0;
	private int columnas = 3;
	private FormatoVistas formatoVistas = FormatoVistas.NOMBRES;
	private TipoOrden ordenado = TipoOrden.DESORDENADO;
	
	private Date fecha;

	public GestionFicherosImpl() {
		carpetaDeTrabajo = File.listRoots()[0];
		actualiza();
	}

	private void actualiza() {

		String[] ficheros = carpetaDeTrabajo.list(); // obtener los nombres
		// calcular el número de filas necesario
		filas = ficheros.length / columnas;
		if (filas * columnas < ficheros.length) {
			filas++; // si hay resto necesitamos una fila más
		}

		// dimensionar la matriz contenido según los resultados

		contenido = new String[filas][columnas];
		// Rellenar contenido con los nombres obtenidos
		for (int i = 0; i < columnas; i++) {
			for (int j = 0; j < filas; j++) {
				int ind = j * columnas + i;
				if (ind < ficheros.length) {
					contenido[j][i] = ficheros[ind];
				} else {
					contenido[j][i] = "";
				}
			}
		}
	}

	@Override
	public void arriba() {

		System.out.println("holaaa");
		if (carpetaDeTrabajo.getParentFile() != null) {
			//Nos vamos al directorio anterior
			carpetaDeTrabajo = carpetaDeTrabajo.getParentFile();
			//Actualizamos el contenido
			actualiza();
		}

	}

	@Override
	public void creaCarpeta(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		//Si no tenemos permisos de escritura en el directorio que queremos crear la carpeta,
		//lanzamos una excepción
		if(!carpetaDeTrabajo.canWrite()) {
			throw new GestionFicherosException("No tienes permisos de escritura");
		}
		
		//Si el directorio ya existe, lanzaremos una excepción
		if(file.exists()) {
			throw new GestionFicherosException("La carpeta ya existe");
		}
		
		//crear la carpeta -> lanzará una excepción
		file.mkdir();
		
		//Actualizamos contenido
		actualiza();
	}

	@Override
	public void creaFichero(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		
		//Si no tenemos permisos de escritura en el directorio que queremos crear la carpeta,
		//lanzamos una excepción
		if(!carpetaDeTrabajo.canWrite()) {
			throw new GestionFicherosException("No tienes permisos de escritura");
		}
		
		//Si el fichero ya existe, lanzaremos una excepción
		if(file.exists()) {
			throw new GestionFicherosException("Ya existe un fichero con ese nombre en el directorio");
		}
		
		//Creamos el fichero
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();	//Mostramos el error por consola
			throw new GestionFicherosException("No se ha podido crear el fichero");
		}
		
		actualiza();

	}

	@Override
	public void elimina(String arg0) throws GestionFicherosException {
		
		File file = new File(carpetaDeTrabajo,arg0);
		
		//Si no tenemos permisos de escritura en el directorio que queremos crear la carpeta,
		//lanzamos una excepción
		if(!carpetaDeTrabajo.canWrite()) {
			throw new GestionFicherosException("No tienes permisos de escritura");
		}
		
		//Si el fichero no existe, lanzaremos una excepción
		if(!file.exists()) {
			throw new GestionFicherosException("No existe este fichero");
		}
		
		//Si intentamos borrar un directorio que contiene algun elemento, lanzará una excepción
		if(file.isDirectory()) {
			if(file.listFiles().length != 0) {
				throw new GestionFicherosException("El directorio no está vacío");
			}
		}
		
		//Borramos el fichero o el directorio
		file.delete();
		
		actualiza();
	}

	@Override
	public void entraA(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo, arg0);
		// se controla que el nombre corresponda a una carpeta existente
		if (!file.isDirectory()) {
			throw new GestionFicherosException("Error. Se ha encontrado "
					+ file.getAbsolutePath()
					+ " pero se esperaba un directorio");
		}
		// se controla que se tengan permisos para leer carpeta
		if (!file.canRead()) {
			throw new GestionFicherosException("Alerta. No se puede acceder a "
					+ file.getAbsolutePath() + ". No hay permiso");
		}
		
		// nueva asignación de la carpeta de trabajo
		carpetaDeTrabajo = file;
		// se requiere actualizar contenido
		actualiza();

	}

	@Override
	public int getColumnas() {
		return columnas;
	}

	@Override
	public Object[][] getContenido() {
		return contenido;
	}

	@Override
	public String getDireccionCarpeta() {
		return carpetaDeTrabajo.getAbsolutePath();
	}

	@Override
	public String getEspacioDisponibleCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEspacioTotalCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getFilas() {
		return filas;
	}

	@Override
	public FormatoVistas getFormatoContenido() {
		return formatoVistas;
	}

	@Override
	public String getInformacion(String arg0) throws GestionFicherosException {
		
		StringBuilder strBuilder=new StringBuilder();
		File file = new File(carpetaDeTrabajo,arg0);
		
		//Controlar que existe. Si no, se lanzará una excepción
		if(!file.exists()) {
			throw new GestionFicherosException("El fichero o directorio no existe");
		}
		//Controlar que haya permisos de lectura. Si no, se lanzará una excepción
		if(!file.canRead()) {
			throw new GestionFicherosException("No tienes permisos de lectura");
		}
		
		//Título
		strBuilder.append("INFORMACIÓN DEL SISTEMA");
		strBuilder.append("\n\n");
		
		//Nombre
		strBuilder.append("Nombre: ");
		strBuilder.append(arg0);
		strBuilder.append("\n");
		
		//Tipo: fichero o directorio
		if(!file.isDirectory()) {
			strBuilder.append("Tipo: Fichero");
			strBuilder.append("\n");
		}
		else if(file.isDirectory()) {
			strBuilder.append("Tipo: Directorio");
			strBuilder.append("\n");
		}
		
		//Ubicación
		strBuilder.append("Ruta: ");
		strBuilder.append(file.getAbsolutePath());
		strBuilder.append("\n");
		
		//Fecha de última modificación
		fecha = new Date(file.lastModified());
		strBuilder.append("Última modificación: ");
		strBuilder.append(fecha.toString());
		strBuilder.append("\n");
		
		//Si es un fichero oculto o no y su tamaño
		if(!file.isDirectory()) {
			if(file.isHidden()) {
				strBuilder.append("El fichero está oculto");
				strBuilder.append("\n");
			}
			else {
				strBuilder.append("El fichero no está oculto");
				strBuilder.append("\n");
			}
			strBuilder.append("Tamaño: ");
			strBuilder.append(file.length());
			strBuilder.append(" bytes\n");
		}
		//Si es directorio: Espacio libre, espacio disponible, espacio total
		//bytes
		else if(file.isDirectory()) {
			
			//Numero de elementos que contiene
			strBuilder.append("Número de elementos en el directorio: ");
			strBuilder.append(file.list().length);
			strBuilder.append("\n");
			
			//Espacio libre, disponible y total
			strBuilder.append("Espacio libre: ");
			strBuilder.append(file.getFreeSpace());
			strBuilder.append(" bytes");
			strBuilder.append("\n");
			
			strBuilder.append("Espacio disponible: ");
			strBuilder.append(file.getUsableSpace());
			strBuilder.append(" bytes");
			strBuilder.append("\n");
			
			strBuilder.append("Espacio total: ");
			strBuilder.append(file.getTotalSpace());
			strBuilder.append(" bytes");
			strBuilder.append("\n");
		}
		
		return strBuilder.toString();
	}

	@Override
	public boolean getMostrarOcultos() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNombreCarpeta() {
		return carpetaDeTrabajo.getName();
	}

	@Override
	public TipoOrden getOrdenado() {
		return ordenado;
	}

	@Override
	public String[] getTituloColumnas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getUltimaModificacion(String arg0)
			throws GestionFicherosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String nomRaiz(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numRaices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void renombra(String arg0, String arg1) throws GestionFicherosException {
		
		File file = new File(carpetaDeTrabajo,arg0);
		File renamedFile = new File(carpetaDeTrabajo,arg1);
		
		//Si no tenemos permisos de escritura en el directorio que queremos crear la carpeta,
		//lanzamos una excepción
		if(!carpetaDeTrabajo.canWrite()) {
			throw new GestionFicherosException("No tienes permisos de escritura");
		}
		//Si el queremos renombrar el fichero/directorio y ya existe otro igual, lanzaremos una excepción
		if(renamedFile.exists()) {
			throw new GestionFicherosException("Ya existe un fichero o directorio con ese nombre");
		}
		
		//Renombramos el fichero o el directorio
		file.renameTo(renamedFile);
		
		actualiza();

	}

	@Override
	public boolean sePuedeEjecutar(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sePuedeEscribir(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sePuedeLeer(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setColumnas(int arg0) {
		columnas = arg0;

	}

	@Override
	public void setDirCarpeta(String arg0) throws GestionFicherosException {
		File file = new File(arg0);

		// se controla que la dirección exista y sea directorio
		if (!file.isDirectory()) {
			throw new GestionFicherosException("Error. Se esperaba "
					+ "un directorio, pero " + file.getAbsolutePath()
					+ " no es un directorio.");
		}

		// se controla que haya permisos para leer carpeta
		if (!file.canRead()) {
			throw new GestionFicherosException(
					"Alerta. No se puede acceder a  " + file.getAbsolutePath()
							+ ". No hay permisos");
		}

		// actualizar la carpeta de trabajo
		carpetaDeTrabajo = file;

		// actualizar el contenido
		actualiza();

	}

	@Override
	public void setFormatoContenido(FormatoVistas arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMostrarOcultos(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOrdenado(TipoOrden arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSePuedeEjecutar(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSePuedeEscribir(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSePuedeLeer(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUltimaModificacion(String arg0, long arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

}
