package ficheros;

import java.util.Collections;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class OrdenarFichero {

	public static void main(String[] args) {
		File fichero = new File("/home/jb/Documentos/Datos.txt");
		Fichero f1 = new Fichero(fichero);
		f1.contarLineas();
		f1.escribir();
	}

}

class Fichero {
	File archivo;
	ArrayList<Integer> numeros = null;
	ArrayList<String> lineas = null;

	public Fichero(File archivo) {
		this.archivo = archivo;
		numeros = new ArrayList<Integer>();
		lineas = new ArrayList<String>();
	}
	
	public void contarLineas() {
		BufferedReader br = null;
		String linea;
		try {
			br = new BufferedReader(new FileReader(archivo));
			while((linea = br.readLine()) != null) {
				numeros.add(Integer.parseInt(linea.split(": ")[3]));
				lineas.add(linea);
			}
		} catch (FileNotFoundException e) {
			System.err.println("El fichero no ha podido encontrarse");
		} catch (IOException e) {
			System.err.println("Error a la hora de leer");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.err.println("Error a la hora de cerrar");
				}
			}
		}
		Collections.sort(numeros);
	}

	public void escribir() {
		BufferedWriter bw = null;
		int linea = 0;
		boolean encontrado = false;
		
		try {
			bw = new BufferedWriter(new FileWriter("/home/jb/Documentos/DatosOrdenados.txt"));
			while (lineas.size() > 0) {
				for (int i = 0; i < lineas.size() && !encontrado; i++) {
					linea = i;
					if (lineas.get(linea).split(": ")[3].equalsIgnoreCase(Integer.toString(numeros.get(0)))) {
						bw.write(lineas.get(linea));
						bw.newLine();
						encontrado = true;
					}
				}
				encontrado = false;
				lineas.remove(linea);
				numeros.remove(0);
			}
		} catch(IOException e) {
			System.out.println("Error a la hora de escribir el archivo");
		} finally {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar la escritura");
				}
			}
		}
	}
}