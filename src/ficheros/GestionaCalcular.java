package examen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestionaCalcular{
	public static void main(String[] args) {
		File archivo = new File("/home/jb/Documentos/ventas.txt");
		Calcular c1 = new Calcular(archivo);
		c1.clasificarArticulos();
		c1.contarArticulos();
		c1.escribirInforme();
	}
}

class Calcular{
	
	private ArrayList<String> articulos = new ArrayList<String>();
	private int cantidades[];
	File archivo; 

	public Calcular(File archivo) {
		this.archivo = archivo;
	}

	public void clasificarArticulos() {
		String linea;
		boolean esta;
		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			while ((linea = br.readLine()) != null) {
				esta = false;
				for (String articulo : articulos) {
					if (articulo.equalsIgnoreCase(linea.split(" ")[1])) {
						esta = true;
					}
				}
				if(!esta) {
					articulos.add(linea.split(" ")[1]);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el archivo");
		} catch (IOException e) {
			System.err.println("Error de lectura del archivo");
		}
	}

	public void contarArticulos() {
		String linea;
		cantidades = new int[articulos.size()];
		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			while (( linea = br.readLine() ) != null) {
				for (int i = 0; i < cantidades.length; i++) {
					if (articulos.get(i).equalsIgnoreCase(linea.split(" ")[1])) {
						cantidades[i] += Integer.parseInt(linea.split(" ")[2]);
					}
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println("No se encontro el archivo");
		} catch (IOException e) {
			System.err.println("Error en la lectura");
		}
	}

	public void escribirInforme() {
		File informe = new File("/home/jb/Documentos/acumuladas.txt");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(informe));
			for (int i = 0; i < cantidades.length; i++) {
				bw.write(articulos.get(i) + " " + cantidades[i]);
			}
			bw.close();
		} catch (IOException e) {
			System.err.println("Error en la escritu");
		}
	}


}
