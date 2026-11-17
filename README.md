# Archivos-CSV

Práctica de **Acceso a Datos (2º DAM)** en Java para trabajar con ficheros **CSV** y pasar datos de **XML a CSV** desde consola.

---

## Instrucciones de ejecución

### 1. Finalidad

Este proyecto es una app de consola en Java pensada para practicar la lectura, filtrado y escritura de ficheros. Al arrancarla te sale un menú con 5 opciones:

1. **Mostrar restaurantes por código postal**: Saca por pantalla todos los restaurantes cuyo código postal empieza por `6`.
2. **Añadir datos al fichero**: Te pide los datos de un restaurante nuevo por teclado y los mete al final de `data/Restaurantes.csv`.
3. **Crear copia filtrada en CSV**: Guarda en `data/Restaurantes2.csv` los restaurantes cuyo código postal empieza por `6` (añadiéndolos al final si el archivo ya existe).
4. **Generar CSV desde XML**: Lee `data/libreriaConcesionario.xml` usando DOM y genera el archivo `data/libreriaConcesionario.csv`.
5. **Salir**: Cierra el programa.

---

### 2. Requisitos y librerías

- **Java JDK**: El proyecto está configurado con **OpenJDK 26** (en `.idea/misc.xml`), pero vale cualquier **Java 15 o superior**.
- **Librerías**: No hace falta instalar nada externo ni usar Maven/Gradle. Todo está hecho con las librerías estándar que ya trae Java:
  - `java.io.*` (`BufferedReader`, `BufferedWriter`, `FileReader`, `FileWriter`, `File`)
  - `java.nio.charset.StandardCharsets` y `java.nio.file.Files`
  - `java.util.ArrayList` y `java.util.Scanner`
  - `javax.xml.parsers.DocumentBuilderFactory`, `org.w3c.dom.*` y `org.xml.sax.SAXException` (para leer el XML con DOM)
- **IDE (opcional)**: IntelliJ IDEA (el repo ya incluye el `.iml` y la carpeta `.idea/`).

---

### 3. Ficheros de entrada

Los archivos que lee el programa tienen que estar dentro de la carpeta `data/` en la raíz del proyecto:

- **`data/Restaurantes.csv`**  
  Es un CSV separado por comas con la cabecera `Restaurant,Address,City,State,Zipcode`. Tiene el listado de restaurantes (nombre, dirección, ciudad, estado y código postal). Si alguna dirección lleva comas dentro, viene entre comillas dobles toda la línea.

- **`data/libreriaConcesionario.xml`**  
  Un XML con la etiqueta raíz `<catagProductos>`. Trae arriba `<lugar>` y `<nombreConce>`, y luego un `<produCatalog>` con varios productos (`<produCatalog>`), cada uno con su `<link>`, `<nombreProd>` y `<precio>`.

  ![libreriaConcesionario.xml](https://github.com/user-attachments/assets/d915324e-68f5-409a-ab8a-ca1fac2f3643)

---

### 4. Pasos para ejecutarla

> **Ojo:** Hay que ejecutar el proyecto desde la carpeta raíz (`Archivos-CSV`), si no las rutas relativas a `data/` no las va a encontrar.

#### Desde la terminal

1. Abre una terminal en la carpeta `Archivos-CSV`.
2. Compila el código:
   ```bash
   javac -encoding UTF-8 -d out -sourcepath src src/Main.java
   ```
3. Lanza el programa:
   ```bash
   java -cp out Main
   ```

#### Desde IntelliJ IDEA

1. Abre la carpeta `Archivos-CSV` en IntelliJ.
2. Comprueba que tienes el JDK seleccionado en *Project Structure* (`File > Project Structure > Project`).
3. Ve a `src/Main.java` y dale al botón de *Run* en el método `main`.

---

### 5. Resultados que genera

- **Por consola**:
  - **Opción 1**: Te imprime la lista de restaurantes cuyo C.P. empieza por `6`, bien formateada con su nombre, dirección, ciudad, estado y código postal.
  - **Opciones 2, 3 y 4**: Te muestra por pantalla si todo ha ido bien o si ha saltado algún fallo (por ejemplo, si no encuentra el XML).

- **Archivos que crea o modifica en `data/`**:
  - **`data/Restaurantes.csv`** *(Opción 2)*: Le añade al final la nueva línea con el restaurante que hayas metido por teclado.

    ![Restaurantes.csv](https://github.com/user-attachments/assets/f5f8c792-a945-4427-b540-b20b1850d220)

  - **`data/Restaurantes2.csv`** *(Opción 3)*: Crea el fichero (o añade líneas si ya existe) con los restaurantes filtrados cuyo código postal empieza por `6`.

    ![Restaurantes2.csv](https://github.com/user-attachments/assets/217d0a35-0106-4e2a-9524-92d6225bb135)

  - **`data/libreriaConcesionario.csv`** *(Opción 4)*: Crea o sobrescribe el CSV sacado del XML, guardado en **UTF-8 con BOM** (`\uFEFF`) y separado por punto y coma (`;`) para que se abra bien en Excel, con los datos del concesionario y la tabla de productos (`Link`, `Nombre`, `Precio`).

    ![libreriaConcesionario.csv](https://github.com/user-attachments/assets/8fd76395-ed03-498d-9781-36af5ad429ff)

---

## Estructura del proyecto

```text
Archivos-CSV/
├── data/
│   ├── libreriaConcesionario.csv
│   ├── libreriaConcesionario.xml
│   ├── Restaurantes.csv
│   └── Restaurantes2.csv
├── src/
│   ├── controller/
│   │   └── Controller.java
│   ├── models/
│   │   └── Restaurante.java
│   ├── persistencia/
│   │   └── Persistencia.java
│   ├── utils/
│   │   └── Utils.java
│   └── Main.java
└── README.md
```
