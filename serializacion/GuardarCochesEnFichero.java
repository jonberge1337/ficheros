package cocheSerializar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GuardarCochesEnFichero {

	public static void main(String[] args) {
		Coche varios[] = new Coche[3];
		varios[0] = new Coche("bmw", 3, 2, 320, 3, 3200);
		varios[1] = new Coche("audi", 3, 2, 180, 5, 1800);
		varios[2] = new Coche("citroen", 3, 2, 110, 5, 1100);

		File archivo = new File("/home/jb/Documentos/CochesSerializados.txt");
		try {
			ObjectOutputStream escribiendoFichero = new ObjectOutputStream(new FileOutputStream(archivo));
			escribiendoFichero.writeObject(varios);
			escribiendoFichero.close();
		} catch (IOException e) {
			System.out.println("Error a la hora de escribir");
		}
	}

}
