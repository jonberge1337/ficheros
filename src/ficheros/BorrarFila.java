package ficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BorrarFila {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		File archivo = new File("/home/jb/Documentos/Datos.txt");
		Archivo a1 = new Archivo(archivo);
		String nombre;
		boolean borrar;
		do {
			System.out.println("Introduce un nombre para buscar en el archivo");
			nombre = sc.nextLine();
			a1.encontrarLinea(nombre);
			a1.borrarLineas();
			System.out.println("Deseas buscar otro nombre? En caso afirmativo escribe si");
			borrar = sc.nextLine().equalsIgnoreCase("Si") ? true : false;
		} while (borrar);
		a1.escribirArchivo();

	}

}

class Archivo {
	private File archivo;
	private ArrayList<String> lineas;
	private ArrayList<Integer> numeroLineas;

	public Archivo(File archivo) {
		this.archivo = archivo;
		leerArchivo();
	}

	public void leerArchivo() {
		BufferedReader br = null;
		lineas = new ArrayList<String>();
		String linea;
		try {
			br = new BufferedReader(new FileReader(archivo));
			while ((linea = br.readLine()) != null) {
				lineas.add(linea);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
		} catch (IOException e) {
			System.out.println("Error al leer las lineas");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar el archivo");
				}
			}
		}
	}

	public void encontrarLinea(String nombre) {
		String nombreLinea;
		numeroLineas = new ArrayList<Integer>();
		for (int i = lineas.size() - 1; i >= 0 ; i--) {
			nombreLinea = "";
			for (String palabra : lineas.get(i).split(" ")) {
				if(!palabra.equalsIgnoreCase("Nombre:") && !palabra.equalsIgnoreCase("Apellido:")) {
					nombreLinea += palabra + " ";
				} else if (palabra.equalsIgnoreCase("Apellido:")) {
					break;
				}
			}
			if(nombreLinea.trim().equalsIgnoreCase(nombre)) {
				System.out.println(lineas.get(i));
				numeroLineas.add(i);
			}
		}
	}

	public void borrarLineas() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		if (numeroLineas.size() > 0) {
			System.out.println("Desea borrar la{s} lineas{s} encontrada{s}? En caso afirmativo escribe si");
			if (sc.nextLine().equalsIgnoreCase("Si")) {
				for (int linea : numeroLineas) {
					lineas.remove(linea);
				}
			}
		} else {
			System.out.println("No se ha encontrado ninguna linea");
		}
	}

	public void escribirArchivo() {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(archivo));
			for (String linea : lineas) {
				bw.write(linea);
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error al escribir el fichero");
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar el archivo");
				}
			}
		}
	}
}