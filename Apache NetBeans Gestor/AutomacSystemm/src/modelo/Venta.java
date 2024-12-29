package modelo;

import java.util.Date;

public class Venta {
    private Date fechaVenta;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private double valorVenta;

    // Constructor
    public Venta(Date fechaVenta, Cliente cliente, Vehiculo vehiculo, double valorVenta) {
        this.fechaVenta = fechaVenta;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.valorVenta = valorVenta;
    }

    // Getters y setters
    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public double getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(double valorVenta) {
        this.valorVenta = valorVenta;
    }

    // Método toString para representar la venta como una cadena
    @Override
    public String toString() {
        return "Venta{" +
               "fechaVenta=" + fechaVenta +
               ", cliente=" + cliente +
               ", vehiculo=" + vehiculo +
               ", valorVenta=" + valorVenta +
               '}';
    }
}
