# Diferentes modos de lectura y escritura
## Lectura caracteres

- **Clase**: Filereader:
  - java.lang.Object
    - java.io.Reader
      - java.io.InputStreamReader
        - java.io.FileReader
- **Metodo**: read hereda de Reader e InputStreamReader
- **Motivo**: Se usa cuando necesitemos leer un archivo en de texto plano.
- **Enlace**: [FileReader](https://docs.oracle.com/javase/7/docs/api/java/io/FileReader.html)
- **Ejemplo**:
```java

FileReader fr;
int letra;
try{
    fr = new Filereader("/home/usuario/fichero.txt");
    while((letra = fr.read()) != -1){ // Aqui vamos asignando la letra en el numero ansi correspondiente y lo comparamos con el -1 que es el fin del fichero. Mientras no llegue al final seguira leyendo.
        System.out.print((char) letra);
    }
} catch(FileNotFoundException e){
    ... // Si no encuentra lanza un excepción que hay que capturarla
}
fr.close(); // El flujo de datos siempre hay que cerrarlo
```

- **Clase**: BufferedReader:
  - java.lang.Object
    - java.io.Reader
      - java.io.BufferReader
- **Metodo**: 
```java
int read()
int read(char[] cbuf, int off, int len)
String readLine()
```
- **Motivo**: Se usa de la misma manera para leer un archivo de texto plano, la diferencia que hay que esta en vez de leer caracter por caracter va leyendo linea por linea.
- **Enlace**: [BufferedReader](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html)
- **Ejemplo**:

```java
BufferedReader br;
String linea;
try{
    br = new BufferedReader(new FileReader("/home/usuario/fichero.txt"));
    while((linea = bw.readLine()) != null){
        Sytem.out.println(linea);
    }
} catch(IOException e){
    ... // Antes hemos mencionado el FileNotFoundException al abrir el
        // archivo pero a la hora de leer tambien nos puede lanzar la excepción IOException
} br.close(); // Como siempre lo que se abre se cierra para no tener imprevistos
```

---

## Lectura bytes

- **Clase**: FileInputStream:
  - java.lang.Object
    - java.io.InputStream
      - java.io.FileInputStream
- **Metodo**: 
```java
int read() 
int read(byte[] b)
int read(byte[] b, int off, int len)

```
- **Motivo**: Se usa para leer cualquier tipo de archivo ya pues ser texto de fichero plano como puede ser un archivo png o lo que sea. Ya que todos los ficheros son cumulos de bytes.
- **Enlace**: [FileInputStream](https://docs.oracle.com/javase/7/docs/api/java/io/FileInputStream.html)
- **Ejemplo**:
```java
FileInputStream fis;
int valor;
try{
    fis = new FileInputStream("/home/usuario/ejemplo.txt");

	while((valor = fis.read())!=-1){ // Si no le indicamos el tamaño del buffer 
    // hace muy parecido que leer por caracter
		System.out.print((char)valor);
	}

} catch(IOException e){
    // Capturamos la excepcion
} fis.close() // Cerramos el flujo

```
```java
FileInputStream fis;
int valor;
String result;
byte buf = new byte[1024];
try{
    fis = new FileInputStream("/home/usuario/ejemplo.txt");
    while((len = fis.read(buf)) > 0){
        result = IOUtils.toString(inputStream, StandardCharsets.UTF_8); // convertimos el buffer en String
        System.out.println(result);
    }
} catch(IOException e){
    //Capturamos la exception
} fis.close(); // Cerramos el flujo
```

---

## Escritura caracteres

- **Clase**: FileWriter
  - java.lang.Object
    - java.io.Writer
      - java.io.OutputStreamWriter
        - java.io.FileWriter

- **Metodo**: write hereda de de OutputStreamWriter y Writer
- **Motivo**: Se usa para escribir archivos de texto plano es la equivalente a FileReader pero para escritura.
- **Enlace**: [FileWriter](https://docs.oracle.com/javase/7/docs/api/java/io/FileWriter.html)
- **Ejemplo**:
```java
FileWriter fw;
try{
    fw = new FileWriter("/home/usuario/Datos.txt");
    fw.write("Escritura por caracteres en java");
} catch(IOException e){
    // capturamos la excepcion
} fw.close(); // Cerramos el flujo
```
- **Clase**: BufferedWriter
  - java.lang.Object
    - java.io.Writer
      - java.io.BufferedWriter

- **Metodo**: 
```java
void newLine()
void write(char[] cbuf, int off, int len)
void write(int c)
void write(String s, int off, int len)
```
- **Motivo**: Se usa para escribir archivos de texto plano es la equivalente a BufferedReader pero para escritura. Es mas eficiente que FileWriter ya que tiene mas capacidad de almacenar en el buffer y asi no tener que acceder tantas veces al fichero.
- **Enlace**: [BufferedWriter](https://docs.oracle.com/javase/7/docs/api/java/io/BufferedWriter.html)
- **Ejemplo**:
```java
BufferedWriter bw;
try{
    // El segundo argumento si lo ponemos a true significa que va 
    //a añadir al archivo, en caso de false se sobreescribe el fichero.
    bw = new BufferedWriter(new FileWriter("/home/usuario/Datos.txt", true)); 
    bw.write("Escritura por caracteres en java");
} catch(IOException e){
    // Capturamos la excepción
} bw.close(); // Cerramos el flujo
```

---

## Escritura bytes

- **Clase**: FileOutputStream
  - java.lang.Object
    - java.io.OutputStream
      - java.io.FileOutputStream
- **Metodo**: 
```java
int write() 
int write(byte[] b)
int write(byte[] b, int off, int len)
```
- **Motivo**: Se puede crear un archivo de cualquier tipo.
- **Enlace**: [FileOutputStream](https://docs.oracle.com/javase/7/docs/api/java/io/FileOutputStream.html)
- **Ejemplo**:
```java
// Copiar ficheros
File origen = new File("origen.txt");
File destino = new File("destino.txt");

try {
    InputStream in = new FileInputStream(origen);
    OutputStream out = new FileOutputStream(destino);
				
    byte[] buf = new byte[1024];
    int len;

    while ((len = in.read(buf)) > 0) {
        out.write(buf, 0, len);
    }
		
    in.close(); // Cerramos los flujos de entrada y salida
    out.close();
} catch (IOException ioe){
    ioe.printStackTrace(); // Capturamos la excepción
}
```
