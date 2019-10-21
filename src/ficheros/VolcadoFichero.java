package prueba;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class VolcadoFichero {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String nombre;
		int opcion = 0;
		int pares[] = new int[100];
		int lectura[] = new int[100];
		File archivo = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		System.out.println("Introduce el nombre del fichero");
		nombre = sc.nextLine();
		for (int i = 0; i < pares.length; i++) {
			pares[i] = (i+1) * 2;
		}
		archivo = new File("/home/jb/Documentos/" + nombre + "txt");

		do {
			System.out.println("Elige una opción del siguiente menu");
			do {
				System.out.println("1. Volcar array\n2. Mostrar fichero\n3. Salir");
				try {
					opcion = sc.nextInt();
					if (opcion < 1 || opcion > 3) {
						System.err.println("Solo numero del 1 al 3");
					}
				} catch (Exception e) {
					sc.nextLine();
					System.err.println("Solo numero del 1 al 3");
				}
			} while (opcion < 1 || opcion > 3);

			if (opcion == 1) {
				try {
					oos = new ObjectOutputStream(new FileOutputStream(archivo));
					oos.writeObject(pares);
					oos.close();
				} catch (FileNotFoundException e) {
					System.err.println("No se encontro el archivo");
				} catch (IOException e) {
					System.err.println("Error al volcar");
				}
				System.out.println("Volcado el array\n\n");
			} else if(opcion == 2) {
				try {
					ois = new ObjectInputStream(new FileInputStream(archivo));
					lectura = (int[]) ois.readObject();
					System.out.println("Lectura del archivo");
					for (int i : lectura) {
						System.out.println(i);
					}
					System.out.println("Fin de lectura");
				} catch (FileNotFoundException e) {
					System.err.println("No se ha encontrado el archivo");
				} catch (IOException e) {
					System.err.println("Error en la lectura");
				} catch (ClassNotFoundException e) {
					System.err.println("Error en el casting");
				}
			}
		} while (opcion != 3);
		System.out.println("Programa finalizada");

	}

}
