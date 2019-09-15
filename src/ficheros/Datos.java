package ficheros;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class Datos {

	public static void main(String[] args) throws IOException {
		String frase;
		File archivo = new File("/home/jb/Documentos/ficheros/Datos.txt");
		File directorio = new File("/home/jb/Documentos/ficheros");
		BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true));
		BufferedReader br = new BufferedReader(new FileReader(archivo));
		Scanner sc = new Scanner(System.in);

		if(!directorio.exists()) {
			directorio.mkdir();
		}

		do {
			bw.write("==============================\n");
			System.out.println("Introduce el nombre");
			frase = sc.nextLine();
			bw.write("Nombre: " + frase + "\n");
			System.out.println("Introduce el apellido");
			frase = sc.nextLine();
			bw.write("Apellido: " + frase + "\n");
			System.out.println("Introduce el edad");
			frase = sc.nextLine();
			bw.write("Edad: " + frase + "\n");
			bw.write("==============================\n\n");
			System.out.println("Si quieres introducir otra persona escribe si");
			frase = sc.nextLine();
		} while (frase.equalsIgnoreCase("si"));
		bw.close();
		
		try {
			while ((frase = br.readLine()) != null) {
				System.out.println(frase);
			}
		} catch (IOException e) {
			System.out.println("No se ha podido leer el archivo");
		}

		System.out.println("Programa finalizado");
	}

}
