package persistencia;

import models.Restaurante;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

public class Persistencia {
    static final String PATH = "data/";
    static final String FICHERO_XML = "libreriaConcesionario.xml";
    static final String FICHERO_CSV = "libreriaConcesionario.csv";

    // Carga los restaurantes del CSV en un ArrayList
    public static ArrayList<Restaurante> cargaRestaurantes() {
        ArrayList<Restaurante> restaurantes = new ArrayList<>();
        String nombreTemp = "", direccionTemp = "", ciudadTemp = "", estadoTemp = "";
        int codigoPostalTemp = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/Restaurantes.csv"));
            // Me salto la primera línea porque es la cabecera
            String linea = br.readLine();
            linea = br.readLine();
            while (linea != null) {

                // Si no empieza por comillas se separa normal por comas; si sí, la dirección trae comas dentro
                if (!linea.startsWith("\"")) {
                    nombreTemp = linea.substring(0, linea.indexOf(","));
                    linea = linea.substring(linea.indexOf(",") + 1);
                    direccionTemp = linea.substring(0, linea.indexOf(","));
                    linea = linea.substring(linea.indexOf(",") + 1);
                    ciudadTemp = linea.substring(0, linea.indexOf(","));
                    linea = linea.substring(linea.indexOf(",") + 1);
                    estadoTemp = linea.substring(0, linea.indexOf(","));
                    linea = linea.substring(linea.indexOf(",") + 1);
                    codigoPostalTemp = Integer.parseInt(linea);
                    restaurantes.add(new Restaurante(nombreTemp, direccionTemp, ciudadTemp, estadoTemp, codigoPostalTemp));
                } else if (linea.startsWith("\"")) {
                    nombreTemp = linea.substring(1, linea.indexOf(","));
                    linea = linea.substring(linea.indexOf("\"\"") + 2);
                    direccionTemp = linea.substring(0, linea.indexOf("\"\""));
                    linea = linea.substring(linea.indexOf("\",") + 2);
                    ciudadTemp = linea.substring(0, linea.indexOf(","));
                    linea = linea.substring(linea.indexOf(",") + 1);
                    estadoTemp = linea.substring(0, linea.indexOf(","));
                    linea = linea.substring(linea.indexOf(",") + 1);
                    codigoPostalTemp = Integer.parseInt(linea.substring(0, linea.indexOf("\"")));
                    restaurantes.add(new Restaurante(nombreTemp, direccionTemp, ciudadTemp, estadoTemp, codigoPostalTemp));
                }

                //Leo la siguiente linea
                linea = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return restaurantes;
    }

    // Añade un restaurante nuevo al final del CSV respetando las comillas si la dirección tiene comas
    public static boolean aniadeInfo(String nombre, String direccion, String ciudad, String estado, int codigoPostal) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("data/Restaurantes.csv", true));
            bw.newLine();
            bw.write((direccion.contains(",") ? "\"" + nombre : nombre)
                    + "," + (direccion.contains(",") ? "\"\"" + direccion + "\"\"" : direccion)
                    + "," + ciudad + "," + estado + "," + (direccion.contains(",") ? codigoPostal + "\"" : codigoPostal) + "\n");
            bw.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Guarda en Restaurantes2.csv los que tienen CP empezando por 6
    public static boolean creaCopia(ArrayList<Restaurante> restaurantes) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("data/Restaurantes2.csv", true));
            for (Restaurante restaurante : restaurantes) {
                if ((restaurante.getCodigoPostal() / 10000) == 6) {
                    bw.write((restaurante.getDireccion().contains(",") ? "\"" + restaurante.getNombre() : restaurante.getNombre())
                            + "," + (restaurante.getDireccion().contains(",") ? "\"\"" + restaurante.getDireccion()
                            + "\"\"" : restaurante.getDireccion()) + "," + restaurante.getCiudad() + "," + restaurante.getEstado()
                            + "," + (restaurante.getDireccion().contains(",") ? restaurante.getCodigoPostal() + "\"" : restaurante.getCodigoPostal()));
                    bw.newLine();
                }
            }
            bw.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Lee el XML del concesionario con DOM y lo pasa a CSV
    public static void generarCsvConcesionario() {
        if (existe(PATH + FICHERO_XML)) return;
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(PATH + FICHERO_XML));
            doc.getDocumentElement().normalize();
            Element raiz = doc.getDocumentElement();
            String lugar = texto(raiz, "lugar");
            String nombreConce = texto(raiz, "nombreConce");
            Element catalogo = (Element) doc.getElementsByTagName("produCatalog").item(0);
            NodeList productos = catalogo.getElementsByTagName("produCatalog");
            try (BufferedWriter bw = Files.newBufferedWriter(new File(PATH + FICHERO_CSV).toPath(), StandardCharsets.UTF_8)) {
                // Meto el BOM de UTF-8 para que Excel abra bien las tildes
                bw.write('\uFEFF');
                escribirFila(bw, "Lugar", lugar);
                escribirFila(bw, "Nombre Conce", nombreConce);
                bw.newLine();
                escribirFila(bw, "", "Link", "Nombre", "Precio");
                for (int i = 0; i < productos.getLength(); i++) {
                    Node nodo = productos.item(i);
                    if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                        Element producto = (Element) nodo;
                        String link = texto(producto, "link");
                        String nombre = texto(producto, "nombreProd");
                        String precio = texto(producto, "precio");
                        escribirFila(bw, "Producto " + (i + 1), link, nombre, precio);
                    }
                }
            }
            System.out.println("Archivo CSV generado correctamente.");
        } catch (SAXException e) {
            System.out.println("Error de análisis SAX: " + e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println("Error de configuración: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Error de referencia nula: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
    }

    // Devuelve true si NO existe el fichero para salir rápido del metodo
    static boolean existe(String nombre) {
        if (!new File(nombre).exists()) {
            System.out.println("No existe '" + nombre + "'. Genéralo antes con la opción correspondiente.");
            return true;
        }
        return false;
    }

    static String texto(Element e, String etiqueta) {
        return e.getElementsByTagName(etiqueta).item(0).getTextContent().trim();
    }

    static void escribirFila(BufferedWriter bw, String... campos) throws IOException {
        for (int i = 0; i < campos.length; i++) {
            if (i > 0) {
                bw.write(";");
            }
            bw.write(csv(campos[i]));
        }
        bw.newLine();
    }

    // Escapa comillas y punto y coma para no romper el CSV
    static String csv(String valor) {
        if (valor.contains(";") || valor.contains("\"") || valor.contains("\n") || valor.contains("\r")) {
            return "\"" + valor.replace("\"", "\"\"") + "\"";
        }
        return valor;
    }
}
