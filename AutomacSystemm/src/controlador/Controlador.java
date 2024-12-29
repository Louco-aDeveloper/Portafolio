package controlador;

import modelo.Cliente;
import modelo.Vehiculo;
import java.util.ArrayList;

public class Controlador {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();

    // Métodos para gestionar clientes
    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void mostrarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    // Métodos para gestionar vehículos
    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    public void mostrarVehiculos() {
        for (Vehiculo vehiculo : vehiculos) {
            System.out.println(vehiculo);
        }
    }
}
