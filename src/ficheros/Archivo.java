package persona;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Archivo {

	public static void main(String[] args) {
		File lectura = new File("/home/jb/Documentos/persona.txt");
		File escritura = new File("/home/jb/Documentos/escritura.txt");
		ArrayList<Persona> personas = new ArrayList<Persona>();
		String linea;
		String array[];
		int edad = 0;
		int maximo = 0;
		int posicion = 0;
		int pMaximo = 0;
		int suma = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader(lectura));
			while ((linea = br.readLine()) != null) {
				array = linea.split(" ");
				edad = Integer.parseInt(array[2]);
				suma += edad;
				if (maximo < edad) {
					maximo = edad;
					pMaximo = posicion;
				}
				Persona p = new Persona(array[0], array[1], edad);
				personas.add(p);
				posicion += 1;
			}
			br.close();
			personas.get(pMaximo).setMedia(suma / personas.size());
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(escritura));
			oos.writeObject(personas.get(pMaximo));
			oos.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo");
		} catch (IOException e) {
			System.out.println("Error en la lectura");
		}
		
	}

}

class Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String apellido;
	private int edad;
	private float media;
	
	public Persona(String n, String a, int e) {
		this.nombre = n;
		this.apellido = a;
		this.edad = e;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public float getMedia() {
		return media;
	}

	public void setMedia(float media) {
		this.media = media;
	}
	
}