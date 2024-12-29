package modelo;

public class Camioneta extends Vehiculo {
    private int capacidadCarga;

    // Constructor
    public Camioneta(String patente, String marca, String modelo, int kilometraje, int capacidadCarga) {
        super(patente, marca, modelo, kilometraje);
        this.capacidadCarga = capacidadCarga;
    }

    // Getters y Setters
    public int getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(int capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    // Método toString para mostrar datos
    @Override
    public String toString() {
        return super.toString() + " Camioneta{" +
               "Capacidad de Carga=" + capacidadCarga +
               "kg}";
    }
}
