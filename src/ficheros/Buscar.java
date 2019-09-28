import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Buscar {

	public static String pedirFrase(String frase) {
		String archivo;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println(frase);
		archivo = sc.nextLine();
		
		return archivo;

	}
	public static void main(String[] args) {
		String nombre;
		File archivo = new File(pedirFrase("Introduce el nombre del archivo junto a la ruta"));
		BuscarString f1 = new BuscarString(archivo);
		do {
		f1.leerArchivo();
//		f1.mostrarContenido();
		nombre = pedirFrase("Introduce un nombre que quieras buscar: ");
		System.out.println(f1.buscarNombre(nombre));
		nombre = pedirFrase("¿Quieres volver a repetir? Si/No");
		} while (nombre.equalsIgnoreCase("si"));


	}

}

class BuscarString{
	BufferedReader bw = null;
	String contenido;
	public BuscarString(File archivo) {
		try {
			this.bw = new BufferedReader(new FileReader(archivo));
		} catch (FileNotFoundException e) {
			System.out.println("Esa ruta no contiene el archivo buscado");
		}
		
	}
	
	public void leerArchivo() {
		int l;
		this.contenido = "";
		try {
			while ((l = this.bw.read()) != -1) {
				this.contenido += (char) l; 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarContenido() {
		for (int i = 0; i < this.contenido.length(); i++) {
			System.out.println(this.contenido.charAt(i));
		}
	}
	
	public String buscarNombre(String nombre) {
		String respuesta = "";
		if(this.contenido.contains(nombre)) {
			respuesta = "El nombre: " + nombre + "esta en el archivo";
		} else {
			respuesta = "Ese nombre no se encuentra en este archivo";
		}

		return respuesta;
	}
	
	
}
