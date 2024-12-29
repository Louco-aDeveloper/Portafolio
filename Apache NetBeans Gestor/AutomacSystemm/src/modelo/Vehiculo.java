package modelo;

public class Vehiculo {
    private String patente;
    private String marca;
    private String modelo;
    private int kilometraje;

    // Constructor
    public Vehiculo(String patente, String marca, String modelo, int kilometraje) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
    }

    // Getters y Setters
    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    // Método toString para mostrar datos
    @Override
    public String toString() {
        return "Vehiculo{" +
               "Patente='" + patente + '\'' +
               ", Marca='" + marca + '\'' +
               ", Modelo='" + modelo + '\'' +
               ", Kilometraje=" + kilometraje +
               '}';
    }
}
