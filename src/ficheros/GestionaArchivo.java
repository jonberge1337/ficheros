package ejercicio7;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GestionaArchivo {

	public static void main(String[] args) {
		
		int menu;
		String nombre;
		String apellido;
		String poblacion;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		File archivo = new File("/home/jb/Documentos/Datos.txt");
		
		do {

			do {
				System.out.println("\n\nElige una opcion del siguiente menu:");
				System.out.println("1. Altas");
				System.out.println("2. Consultas");
				System.out.println("3. Listado");
				System.out.println("0. Fin programa");

				try {
					menu = sc.nextInt();
				} catch (Exception e) {
					menu = 5;
					sc.nextLine();
				}
				if (menu < 0 || menu > 3) {
					System.out.println("No es una opcion validad, tiene que ser del 0 al 3");
				}
			} while (menu < 0 || menu > 3);
			sc.nextLine();
			if (menu == 1) {
				System.out.println("Introduce el nombre: ");
				nombre = sc.nextLine();
				System.out.println("Introduce el apellido: ");
				apellido = sc.nextLine();
				System.out.println("Introduce la población");
				poblacion = sc.nextLine();
				Escritura.introducirLinea(archivo, nombre, apellido, poblacion);
			} else if(menu == 2) {
				System.out.println("Introduce un nombre a buscar: ");
				nombre = sc.nextLine();
				Lectura.buscarNombre(nombre, archivo);
			} else if(menu == 3) {
				Lectura.mostrarArchivo(archivo);
			}
		} while (menu != 0);
		System.out.println("Programa finalizado");
	}

}

class Lectura{

	public static void buscarNombre(String nombre, File archivo) {
		BufferedReader br = null;
		String linea;
		boolean encontrado = false;
		try {
			br = new BufferedReader(new FileReader(archivo));
			while((linea = br.readLine()) != null) {
				if (linea.contains(nombre) && !encontrado) {
					System.out.println("Encontrado: ");
					System.out.println(linea);
					encontrado = true;
				} else if(linea.contains(nombre)) {
					System.out.println(linea);
				}
			}
			if (!encontrado) {
				System.out.println("No se ha encontrado nadie con ese nombre");
			}
		} catch (IOException e) {
			System.out.println("Ha ocurrido el error: " + e);
		}
	}

	public static void mostrarArchivo(File archivo) {
		BufferedReader br = null; 
		String linea;
		try {
			br = new BufferedReader(new FileReader(archivo));
			while((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
		} catch (IOException e) {
			System.out.println("Ha ocurrido el error: " + e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("Ha ocurrido el error: " + e);
				}
			}
		}
	}

}

class Escritura{

	public static void introducirLinea(File archivo, String nombre, String apellido, String poblacion) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(archivo, true));
			bw.write("Nombre: "+nombre+ " Apellido: " + apellido + " Poblacion: " + poblacion);
			bw.newLine();
		} catch (IOException e) {
			System.out.println("Ha ocurrido el error: " + e);
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					System.out.println("Ha ocurrido el error: " + e);
				}
			}
		}

	}

}