package ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Copiar {

 	public static void main(String[] args) throws IOException {
		File archivo = new File("/home/jb/Documentos/ficheros/Datos.txt");
		File copia = new File("/home/jb/Documentos/ficheros/Datos1.txt");
		Scanner sc = new Scanner(System.in);
		char a;
		BufferedReader entrada = new BufferedReader(new FileReader(archivo));
		BufferedWriter salida = new BufferedWriter(new FileWriter(copia));

		int dato;
		while ((dato = entrada.read()) != -1) {
			salida.write(dato);
		}
		System.out.println("Comprueba que lo haya copiado");
		a = sc.next().charAt(0);
		
		archivo.delete();
		System.out.println("Comprueba que lo haya elimnado");
		a = sc.next().charAt(0);

		copia.renameTo(archivo);
		System.out.println("Comprueba que haya cambiado de nombre");
		a = sc.next().charAt(0);

		entrada.close();
		salida.close();
	}

}
