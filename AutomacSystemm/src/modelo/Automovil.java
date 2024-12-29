package modelo;

public class Automovil extends Vehiculo {
    private String tipoEnergia;
    private boolean aireAcondicionado;

    // Constructor
    public Automovil(String patente, String marca, String modelo, int kilometraje, String tipoEnergia, boolean aireAcondicionado) {
        super(patente, marca, modelo, kilometraje);
        this.tipoEnergia = tipoEnergia;
        this.aireAcondicionado = aireAcondicionado;
    }

    // Getters y Setters
    public String getTipoEnergia() {
        return tipoEnergia;
    }

    public void setTipoEnergia(String tipoEnergia) {
        this.tipoEnergia = tipoEnergia;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    // Método toString para mostrar datos
    @Override
    public String toString() {
        return super.toString() + " Automovil{" +
               "Tipo de Energía='" + tipoEnergia + '\'' +
               ", Aire Acondicionado=" + aireAcondicionado +
               '}';
    }
}
