package dir;

import java.io.File;
import java.io.FileNotFoundException;

public class Principal {

	public static void main(String[] args) {
		
		String ruta = "probando";
		File dir = new File (ruta);
		String ficheroABuscar = "fichero1.txt";
		
		
		try {
			if (localizar(dir, "fichero1.txt")) {
				System.out.println("El fichero " + ficheroABuscar + " se encuentra dentro del directorio " + ruta);
			}
			else {
				System.out.println("El fichero " + ficheroABuscar + " NO se encuentra dentro del directorio " + ruta);
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el directorio introducido");
		}

	}
	
	
	public static boolean localizar(File dir, String fich) throws FileNotFoundException{
		
		//Si el directorio no existe, lanzaremos excepción y el método no continuará
		if(!dir.exists()) {
			throw new FileNotFoundException();
		}
		
		boolean retorno = false;
		
		File listaFicheros[];
		
		listaFicheros = dir.listFiles();
		
		//Recorremos array
		for(int i=0; i < listaFicheros.length; i++) {
			//Si el nombre de algún fichero coincide, devolveremos true
			if(listaFicheros[i].getName().equals(fich)) {
				retorno = true;
			}
		}
		
		return retorno;
	}
	
	public static void comparar(){
		
	}

}

