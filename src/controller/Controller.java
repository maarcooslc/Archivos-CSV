package controller;

import models.Restaurante;
import persistencia.Persistencia;
import java.util.ArrayList;

public class Controller {
    //Atributos
    private ArrayList<Restaurante> restaurantes;

    //Constructor
    public Controller() {
        this.restaurantes = Persistencia.cargaRestaurantes();
    }

    //Getters y setters+
    public ArrayList<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(ArrayList<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }

    // Saca los restaurantes cuyo código postal empieza por el número que se le pase
    public String mostrarRestaurantesPorCodPostal(int i) {
        String respuesta = "";
        for (Restaurante restaurante : restaurantes) {
            // Dividiendo entre 10000 sacamos el primer dígito del CP
            if ((restaurante.getCodigoPostal() / 10000) == i ) {
                respuesta += restaurante.toString();
            }
        }
        return respuesta;
    }

    public boolean aniadeInfo(String nombre, String direccion, String ciudad, String estado, int codigoPostal) {
        return Persistencia.aniadeInfo(nombre, direccion, ciudad, estado, codigoPostal);
    }

    public boolean creaCopia() {
        return Persistencia.creaCopia(restaurantes);
    }

    public void generarCsvConcesionario() {
        Persistencia.generarCsvConcesionario();
    }
}
