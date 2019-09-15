package ficheros;

import java.io.File;
import java.util.Scanner;

public class Listar {
	public static String pedirDirectorio(Scanner sc) {
		System.out.println("Introduce la ruta del directorio que quieres listar: ");
		return sc.nextLine();
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String repetir;
		do {
			File carpeta = new File(pedirDirectorio(sc));
			String[] archivos = carpeta.list();

			if (archivos == null) {
				System.out.println("El directorio no existe");
			} else {
				for (String archivo : archivos) {
					System.out.println(archivo);
				}
			}
			System.out.println("Si quieres mirar otra carpeta escribe si");
			repetir = sc.nextLine();
		} while (repetir.equalsIgnoreCase("si"));
		System.out.println("Programa finalizado");
	}
}
