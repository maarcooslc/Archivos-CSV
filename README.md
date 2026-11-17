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
  - **`data/Restaurantes2.csv`** *(Opción 3)*: Crea el fichero (o añade líneas si ya existe) con los restaurantes filtrados cuyo código postal empieza por `6`.
  - **`data/libreriaConcesionario.csv`** *(Opción 4)*: Crea o sobrescribe el CSV sacado del XML, guardado en **UTF-8 con BOM** (`\uFEFF`) y separado por punto y coma (`;`) para que se abra bien en Excel, con los datos del concesionario y la tabla de productos (`Link`, `Nombre`, `Precio`).

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

---

## Pruebas y evidencias

### Tabla de pruebas

Estas pruebas se han hecho compilando con `javac -encoding UTF-8 -d out -sourcepath src src/Main.java` y lanzando el programa con `java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -cp out Main`:

| Caso | Tipo | Entrada que se ha usado | Lo que tenía que pasar | Lo que ha salido | Valoración |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **1. Ver restaurantes y pasar XML a CSV** | Funcionamiento normal | Opciones `1`, `4` y `5` con `data/Restaurantes.csv` y `data/libreriaConcesionario.xml` en su sitio. | La opción `1` saca los 9 restaurantes con C.P. que empieza por `6`. La `4` crea `data/libreriaConcesionario.csv` en UTF-8 con BOM y suelta `Archivo CSV generado correctamente.`. La `5` cierra el programa. | Salió la lista de los 9 restaurantes (`61704`, `60606`, `61265`, `62704`, `68508`, `68114`, `65109`, `65804`, `63600`), se creó `data/libreriaConcesionario.csv` con los 3 productos y cerró bien (código `0`). | **Bien** |
| **2. Meter restaurante con comas y C.P. límite `60000`** | Caso límite | Opción `2` metiendo `Bar Limite`, dirección `Calle Real, 10, Bajo`, ciudad `Martos`, estado `Jaén`, C.P. `60000` (el primer C.P. de 5 cifras que empieza por `6`); luego opción `1` y `5`. | Que lo guarde bien entre comillas en `data/Restaurantes.csv` sin dejar líneas en blanco de más y que salga al momento en la opción `1` (`60000 / 10000 == 6`). | **Al principio fallaba** (no salía en la opción `1` hasta reiniciar y metía un salto de línea doble). **Después de arreglarlo**, lo guarda como `"Bar Limite,""Calle Real, 10, Bajo"",Martos,Jaén,60000"` y ya sale al instante en la opción `1`. | **Bien (tras arreglarlo)** |
| **3. Meter letras en el menú** | Entrada incorrecta | Escribir `abc` al pedir la opción del menú y luego `5`. | Que no pete el programa, avise de que no vale y vuelva a sacar el menú. | **Al principio petaba** con `NumberFormatException: For input string: "abc"` y se cerraba. **Después de arreglarlo**, saca `Opción incorrecta: introduce un número válido.` y sigue funcionando hasta salir con `5`. | **Bien (tras arreglarlo)** |
| **4. Falta el XML en la opción 4** | Fichero inexistente | Cambiarle el nombre un momento a `data/libreriaConcesionario.xml` y darle a la opción `4` y luego `5`. | Que detecte que no está el archivo, avise por pantalla sin colgarse y vuelva al menú. | Sacó `No existe 'data/libreriaConcesionario.xml'. Genéralo antes con la opción correspondiente.` y volvió al menú hasta salir con `5`. | **Bien** |

---

### Evidencias

#### Archivos de entrada y salida
- **De entrada**: [`data/Restaurantes.csv`](data/Restaurantes.csv) (21 restaurantes + cabecera) y [`data/libreriaConcesionario.xml`](data/libreriaConcesionario.xml) (3 productos).
- **Lo que genera (Caso 1)** en [`data/libreriaConcesionario.csv`](data/libreriaConcesionario.csv):
  ```csv
  Lugar;Martos(Jaen)
  Nombre Conce;Catalogo Productos en Martos

  ;Link;Nombre;Precio
  Producto 1;https://www.amazon.es/PlayStation-4-PS4-Consola-500GB/dp/B013U9CW8A;PlayStation 4 (PS4) - Consola 500GB;340.95
  Producto 2;https://www.amazon.es/Raspberry-Modelo-GHz-Quad-core-Cortex-A53/dp/B01CD5VC92/;Raspberry Pi 3 Modelo B (1,2 GHz Quad-core ARM Cortex-A53, 1GB RAM, USB 2.0);41.95
  Producto 3;https://www.amazon.es/Gigabyte-Brix-Bareb%C3%B3n-Serial-SO-DIMM/dp/B00HFCTUPM/;"Gigabyte Brix - Barebón (Intel, Core i5, 2,6 GHz, 6, 35 cm (2.5""), Serial ATA III, SO-DIMM) Negro";421.36
  ```
- **Lo que sale por consola en el Caso 4 (si falta el XML)**:
  ```text
  Introduce una opción: No existe 'data/libreriaConcesionario.xml'. Genéralo antes con la opción correspondiente.
  Introduce una opción: Saliendo del programa
  ```

#### Fallos que salieron al probar y cómo se han arreglado

1. **El programa petaba si metías letras en vez de números (`NumberFormatException`)**:
   - **Qué pasaba**: Al usar `Integer.parseInt(S.nextLine())` a pelo en [`src/Main.java`](src/Main.java) (tanto en el menú como al pedir el código postal), si metías texto como `abc` saltaba la excepción y te echaba fuera.
   - **Cómo se arregló**: Se metió un `try-catch (NumberFormatException)` en los dos sitios para avisar por pantalla y volver al menú sin que se cierre.
   - **Comprobación**: Volviendo a probar el Caso 3 con `abc`, ahora saca `Opción incorrecta: introduce un número válido.` y te deja seguir hasta salir con `5`.

2. **Añadir un restaurante nuevo no se veía hasta reiniciar**:
   - **Qué pasaba**: `aniadeInfo(...)` en [`src/controller/Controller.java`](src/controller/Controller.java) lo escribía en el CSV, pero no lo metía en la lista `restaurantes` en memoria, así que si le dabas a la opción `1` justo después no salía.
   - **Cómo se arregló**: Cuando `Persistencia.aniadeInfo(...)` devuelve `true`, se añade también el nuevo `Restaurante` al `ArrayList`.
   - **Comprobación**: Al repetir el Caso 2, `Bar Limite` (`60000`) ya sale al momento en la opción `1`:
     ```text
     ----------------------------------------
     🍽️ Restaurante : Bar Limite
     📍 Dirección   : Calle Real, 10, Bajo
     🏙️ Ciudad      : Martos
     🗺️ Estado      : Jaén
     📮 C.P.        : 60000
     ```

3. **Salto de línea doble al guardar y fallo si quedaban líneas en blanco**:
   - **Qué pasaba**: `aniadeInfo(...)` en [`src/persistencia/Persistencia.java`](src/persistencia/Persistencia.java) ponía `bw.newLine()` al principio y `"\n"` al final, así que si metías dos restaurantes seguidos dejaba una línea vacía en medio que luego hacía fallar a `cargaRestaurantes()`.
   - **Cómo se arregló**: Se quitó el `"\n"` del final en `aniadeInfo(...)` y se puso un `if (linea.isBlank())` en `cargaRestaurantes()` para que se salte cualquier línea vacía al leer el CSV.
