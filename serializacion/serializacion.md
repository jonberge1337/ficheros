# Serializacion

Para que un programa java pueda convertir un objeto en un montón de bytes y pueda luego recuperarlo, el objeto necesita ser Serializable. Al poder convertir el objeto a bytes, ese objeto se puede enviar a través de red, guardarlo en un fichero, y después reconstruirlo al otra lado de la red, leerlo del fichero,....

Para que un objeto sea serializable basta con que implemente la interfaz Serializable. Como la interfaz Serializable no tiene métodos, es muy sencillo implementarla, basta con un implements Serializable y nada más. 
**Ejemplo:**
``` java
public class Vehiculo implements Serializable{
    public String marca;
    public String modelo;
    public int largo;
}
```
Si dentro de la clase hay atributos de tipo avanzados (otra clase) esa clase tambien debera implementar Serializable
**Ejemplo**:
``` java
public class Coche implements Serializable{
    Vehiculo f;
    public int potencia;
}
```
---
Tenemos dos clases para trabajar con los archivos serializados.

- **ObjectInputStream:**
  - **Enlace:** [Java API ObjectInputStream](https://docs.oracle.com/javase/7/docs/api/java/io/ObjectInputStream.html)
  - **Jerarquia:**
    - java.lang.Object
      - java.io.InputStream
        - java.io.ObjectInputStream
  - **uso:** Se usa para leer un archivo o un buffer serializado
  - **ejemplo:**
``` java
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
```
---
- **ObjectOutputStream:**
  - **Enlace:** [Java API ObjectOutputStream](https://docs.oracle.com/javase/7/docs/api/java/io/ObjectOutputStream.html)
  - **Jerarquia:**
    - java.lang.Object
      - java.io.OutputStream
        - java.io.ObjectOutputStream
  - Se usa para escribir cualquier cosa serializado
  - **ejemplo:**
``` java
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
```
Cuando pasamos objetos Serializable de un lado a otro tenemos un pequeño problema. Si la clase que queremos pasar es objeto_serializable, lo normal, salvo que usemos Carga dinámica de clases, es que en ambos lados (el que envía y el que recibe la clase), tengan su propia copia del fichero objeto_serializable.class. Es posible que en distintas versiones de nuestro programa la clase objeto_serializable cambie, de forma que es posible que un lado tenga una versión más antigua que en el otro lado. Si sucede esto, la reconstrucción de la clase en el lado que recibe es imposible.
Para evitar este problema, se aconseja que la clase objeto_serializable tenga un atributo privado de esta forma 
``` java
 private static final long serialVersionUID = 8799656478674716638L;
```