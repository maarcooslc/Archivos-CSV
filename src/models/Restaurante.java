package models;

public class Restaurante {
    //Atributos
    private String nombre;
    private String direccion;
    private String ciudad;
    private String estado;
    private int codigoPostal;

    //Constructor
    public Restaurante(String nombre, String direccion, String ciudad, String estado, int codigoPostal) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.estado = estado;
        this.codigoPostal = codigoPostal;
    }

    //Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    //Pintar Restaurante
    @Override
    public String toString() {
        return String.format(
                "----------------------------------------%n" +
                        "🍽️ Restaurante : %s%n" +
                        "📍 Dirección   : %s%n" +
                        "🏙️ Ciudad      : %s%n" +
                        "🗺️ Estado      : %s%n" +
                        "📮 C.P.        : %d%n",
                nombre, direccion, ciudad, estado, codigoPostal
        );
    }
}
