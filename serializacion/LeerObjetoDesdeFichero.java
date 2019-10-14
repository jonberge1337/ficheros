package cocheSerializar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LeerObjetoDesdeFichero {

	public static void main(String[] args) {
		File archivo = new File("/home/jb/Documentos/CochesSerializados.txt");
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
			Coche coches[] = (Coche[]) ois.readObject();
			for (Coche coche : coches) {
				System.out.println(coche);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			System.out.println("Error al leer el objeto");
		} catch (ClassNotFoundException e) {
			System.out.println("Error a la hora de convertir el array");
		}

	}

}
