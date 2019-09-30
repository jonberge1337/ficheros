package prueba;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GestionaArchivo {
	public static int buscarMaxima(int[] numeros) {
		int numero = numeros[0];
		for (int i = 1; i < numeros.length; i++) {
			if(numeros[i] > numero) {
				numero = numeros[i];
			}
		}
		return numero;
	}
	
	public static int edadMedia(int numeros[]) {
		int media = 0;
		for (int i = 0; i < numeros.length; i++) {
			media += numeros[i];
		}
		return media / numeros.length;
	}

	public static void main(String[] args) {
		
		int menu;
		String nombre;
		String apellido;
		String edad;
		int edadAritmetico;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		File archivo = new File("/home/jb/Documentos/Datos.txt");
		
		do {

			do {
				System.out.println("\n\nElige una opcion del siguiente menu:");
				System.out.println("1. Altas");
				System.out.println("2. Consultas");
				System.out.println("3. Listado");
				System.out.println("4. Numero de filas");
				System.out.println("5. Edad media");
				System.out.println("6. Mayor edad");
				System.out.println("0. Fin programa");

				try {
					menu = sc.nextInt();
				} catch (Exception e) {
					menu = 5;
					sc.nextLine();
				}
				if (menu < 0 || menu > 6) {
					System.out.println("No es una opcion validad, tiene que ser del 0 al 6");
				}
			} while (menu < 0 || menu > 6);
			sc.nextLine();
			if (menu == 1) {
				System.out.println("Introduce el nombre: ");
				nombre = sc.nextLine();
				System.out.println("Introduce el apellido: ");
				apellido = sc.nextLine();
				System.out.println("Introduce la edad");
				edad = sc.nextLine();
				Escritura.introducirLinea(archivo, nombre, apellido, edad);
			} else if(menu == 2) {
				System.out.println("Introduce un nombre a buscar: ");
				nombre = sc.nextLine();
				Lectura.buscarNombre(nombre, archivo);
			} else if(menu == 3) {
				Lectura.mostrarArchivo(archivo);
			} else if(menu == 4) {
				System.out.println("El archivo tiene " + Lectura.contarLineas(archivo) + " lineas");
			} else if(menu == 5) {
				int edades[] = Lectura.sacarEdades(archivo);
				edadAritmetico = edadMedia(edades);
				System.out.println("La edad media es: " + edadAritmetico);
			} else if(menu == 6) {
				int edades[] = Lectura.sacarEdades(archivo);
				edadAritmetico = buscarMaxima(edades);
				Lectura.buscarPorEdad(edadAritmetico, archivo);
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
	
	public static void buscarPorEdad(int edadMax, File archivo) {
		BufferedReader br = null;
		String linea;
		int edad = 0;
		
		try {
			br = new BufferedReader(new FileReader(archivo));
			while((linea = br.readLine()) != null) {

				String[] lineaDividida = linea.split(": ");
				for (String palabra : lineaDividida) {
					try {
						edad = Integer.parseInt(palabra);
						if(edad == edadMax) {
							System.out.println(linea);
						}
					} catch (Exception e) {
						;
					}
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Ha ocurrido el error: " + e);
		}

	}
	
	public static int contarLineas(File archivo) {
		int lineas = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(archivo));
			while (br.readLine() != null) {
				lineas++;
			}

			br.close();
		} catch(IOException e) {
			System.out.println("Ha habido un error al leer el archivo");
		}

		return lineas;
	}

	public static int[] sacarEdades(File archivo) {
		int edades[] = new int[Lectura.contarLineas(archivo)];
		int contador = 0;
		String linea;
		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			while ((linea = br.readLine()) != null) {
				String[] lineaDividida = linea.split(": ");
				for (String palabra : lineaDividida) {
					try {
						edades[contador] = Integer.parseInt(palabra);
						contador++;
					} catch (Exception e) {
						;
					}
				}
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("archivo no encontrado");
		} catch (IOException e) {
			System.out.println("Error al leer el archivo");
		}
		return edades;
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

	public static void introducirLinea(File archivo, String nombre, String apellido, String edad) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(archivo, true));
			bw.write("Nombre: "+nombre+ " Apellido: " + apellido + " Poblacion: " + edad);
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