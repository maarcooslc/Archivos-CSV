# Archivos-CSV

Aplicación de consola en Java para la gestión, filtrado y exportación de datos en formato **CSV** y conversión de **XML a CSV**.

---

## Instrucciones de ejecución

### 1. Finalidad

El proyecto permite operar sobre un listado de restaurantes almacenado en CSV y transformar un catálogo de productos en XML a formato CSV mediante un menú interactivo en consola con las siguientes opciones:

1. **Mostrar restaurantes por código postal**: Lista por pantalla los restaurantes cuyo código postal empieza por `6`.
2. **Añadir datos al fichero**: Solicita los datos de un nuevo restaurante por teclado y los añade al fichero `data/Restaurantes.csv`.
3. **Crear copia filtrada en CSV**: Exporta al fichero `data/Restaurantes2.csv` los restaurantes cuyo código postal empieza por `6` (en modo *append*).
4. **Generar CSV desde XML**: Lee `data/libreriaConcesionario.xml` mediante DOM y genera `data/libreriaConcesionario.csv`.
5. **Salir**: Finaliza la ejecución del programa.

---

### 2. Requisitos y librerías

- **Java JDK**: Proyecto configurado con **OpenJDK 26** (`.idea/misc.xml`). Requiere **Java 15 o superior** (por el uso de *text blocks* en `Main.java`).
- **Librerías**: No requiere dependencias externas. Utiliza únicamente paquetes de la biblioteca estándar de Java:
  - `java.io.*` (`BufferedReader`, `BufferedWriter`, `FileReader`, `FileWriter`, `File`)
  - `java.nio.charset.StandardCharsets`, `java.nio.file.Files`
  - `java.util.ArrayList`, `java.util.Scanner`
  - `javax.xml.parsers.DocumentBuilderFactory`, `org.w3c.dom.*`, `org.xml.sax.SAXException`
- **IDE (opcional)**: IntelliJ IDEA (incluye `Archivos-CSV.iml` y configuración en `.idea/`).

---

### 3. Ficheros de entrada

La aplicación espera los ficheros de entrada dentro de la carpeta `data/` (ruta relativa al directorio raíz del proyecto):

- **`data/Restaurantes.csv`**  
  Fichero CSV separado por comas con cabecera (`Restaurant,Address,City,State,Zipcode`). Contiene el listado de restaurantes (nombre, dirección, ciudad, estado y código postal). Soporta registros con direcciones que incluyen comas entre comillas dobles.

- **`data/libreriaConcesionario.xml`**  
  Fichero XML con la estructura `<catagProductos>`, que incluye:
  - `<lugar>` y `<nombreConce>` como cabecera/metadatos.
  - `<produCatalog>` con los distintos elementos `<produCatalog>` que contienen `<link>`, `<nombreProd>` y `<precio>`.

---

### 4. Pasos para ejecutarla

> **Importante:** Ejecuta siempre el programa desde la **raíz del proyecto** (`Archivos-CSV`) para que las rutas relativas a `data/` se resuelvan correctamente.

#### Opción A: Desde la terminal

1. Abre una terminal en la raíz del proyecto (`Archivos-CSV`).
2. Compila los archivos fuente:
   ```bash
   javac -encoding UTF-8 -d out -sourcepath src src/Main.java
   ```
3. Ejecuta la clase principal:
   ```bash
   java -cp out Main
   ```

#### Opción B: Desde IntelliJ IDEA

1. Abre la carpeta `Archivos-CSV` en IntelliJ IDEA.
2. Verifica que el SDK de Java esté configurado en *Project Structure* (`File > Project Structure > Project`).
3. Abre `src/Main.java` y ejecuta el método `main`.

---

### 5. Resultados que genera

- **Salida por consola**:
  - **Opción 1**: Muestra cada restaurante cuyo código postal empieza por `6` formateado con nombre, dirección, ciudad, estado y código postal.
  - **Opciones 2, 3 y 4**: Imprime mensajes de confirmación o aviso en caso de error o ausencia del fichero XML.

- **Ficheros modificados o generados en `data/`**:
  - **`data/Restaurantes.csv`** *(Opción 2)*: Añade al final del fichero la nueva fila introducida por el usuario (aplicando comillas si la dirección contiene comas).
  - **`data/Restaurantes2.csv`** *(Opción 3)*: Crea o añade al final del fichero los restaurantes filtrados cuyo código postal empieza por `6`.
  - **`data/libreriaConcesionario.csv`** *(Opción 4)*: Genera (o sobrescribe) un fichero CSV codificado en **UTF-8 con BOM** (`\uFEFF`) y delimitado por punto y coma (`;`) con la información de `Lugar`, `Nombre Conce` y la tabla de productos (`Link`, `Nombre`, `Precio`).

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

Aplicación de consola en Java para la gestión, filtrado y exportación de datos en formato **CSV** y conversión de **XML a CSV**.

---

## Instrucciones de ejecución

### 1. Finalidad

El proyecto permite operar sobre un listado de restaurantes almacenado en CSV y transformar un catálogo de productos en XML a formato CSV mediante un menú interactivo en consola con las siguientes opciones:

1. **Mostrar restaurantes por código postal**: Lista por pantalla los restaurantes cuyo código postal empieza por `6`.
2. **Añadir datos al fichero**: Solicita los datos de un nuevo restaurante por teclado y los añade al fichero `data/Restaurantes.csv`.
3. **Crear copia filtrada en CSV**: Exporta al fichero `data/Restaurantes2.csv` los restaurantes cuyo código postal empieza por `6` (en modo *append*).
4. **Generar CSV desde XML**: Lee `data/libreriaConcesionario.xml` mediante DOM y genera `data/libreriaConcesionario.csv`.
5. **Salir**: Finaliza la ejecución del programa.

---

### 2. Requisitos y librerías

- **Java JDK**: Proyecto configurado con **OpenJDK 26** (`.idea/misc.xml`). Requiere **Java 15 o superior** (por el uso de *text blocks* en `Main.java`).
- **Librerías**: No requiere dependencias externas. Utiliza únicamente paquetes de la biblioteca estándar de Java:
  - `java.io.*` (`BufferedReader`, `BufferedWriter`, `FileReader`, `FileWriter`, `File`)
  - `java.nio.charset.StandardCharsets`, `java.nio.file.Files`
  - `java.util.ArrayList`, `java.util.Scanner`
  - `javax.xml.parsers.DocumentBuilderFactory`, `org.w3c.dom.*`, `org.xml.sax.SAXException`
- **IDE (opcional)**: IntelliJ IDEA (incluye `Archivos-CSV.iml` y configuración en `.idea/`).

---

### 3. Ficheros de entrada

La aplicación espera los ficheros de entrada dentro de la carpeta `data/` (ruta relativa al directorio raíz del proyecto):

- **`data/Restaurantes.csv`**  
  Fichero CSV separado por comas con cabecera (`Restaurant,Address,City,State,Zipcode`). Contiene el listado de restaurantes (nombre, dirección, ciudad, estado y código postal). Soporta registros con direcciones que incluyen comas entre comillas dobles.

- **`data/libreriaConcesionario.xml`**  
  Fichero XML con la estructura `<catagProductos>`, que incluye:
  - `<lugar>` y `<nombreConce>` como cabecera/metadatos.
  - `<produCatalog>` con los distintos elementos `<produCatalog>` que contienen `<link>`, `<nombreProd>` y `<precio>`.

---

### 4. Pasos para ejecutarla

> **Importante:** Ejecuta siempre el programa desde la **raíz del proyecto** (`Archivos-CSV`) para que las rutas relativas a `data/` se resuelvan correctamente.

#### Opción A: Desde la terminal

1. Abre una terminal en la raíz del proyecto (`Archivos-CSV`).
2. Compila los archivos fuente:
   ```bash
   javac -encoding UTF-8 -d out -sourcepath src src/Main.java
   ```
3. Ejecuta la clase principal:
   ```bash
   java -cp out Main
   ```

#### Opción B: Desde IntelliJ IDEA

1. Abre la carpeta `Archivos-CSV` en IntelliJ IDEA.
2. Verifica que el SDK de Java esté configurado en *Project Structure* (`File > Project Structure > Project`).
3. Abre `src/Main.java` y ejecuta el método `main`.

---

### 5. Resultados que genera

- **Salida por consola**:
  - **Opción 1**: Muestra cada restaurante cuyo código postal empieza por `6` formateado con nombre, dirección, ciudad, estado y código postal.
  - **Opciones 2, 3 y 4**: Imprime mensajes de confirmación o aviso en caso de error o ausencia del fichero XML.

- **Ficheros modificados o generados en `data/`**:
  - **`data/Restaurantes.csv`** *(Opción 2)*: Añade al final del fichero la nueva fila introducida por el usuario (aplicando comillas si la dirección contiene comas).
  - **`data/Restaurantes2.csv`** *(Opción 3)*: Crea o añade al final del fichero los restaurantes filtrados cuyo código postal empieza por `6`.
  - **`data/libreriaConcesionario.csv`** *(Opción 4)*: Genera (o sobrescribe) un fichero CSV codificado en **UTF-8 con BOM** (`\uFEFF`) y delimitado por punto y coma (`;`) con la información de `Lugar`, `Nombre Conce` y la tabla de productos (`Link`, `Nombre`, `Precio`).

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
