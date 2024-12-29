package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

public class VentanaPrincipal extends JFrame {

    // Componentes de la interfaz
    private JTabbedPane tabbedPane;
    private JPanel panelClientes, panelVehiculos, panelVentas;
    private JTable tablaClientes, tablaVehiculos, tablaVentas;
    private DefaultTableModel modeloClientes, modeloVehiculos, modeloVentas;
    private JTextField txtRut, txtNombre, txtTelefono, txtCorreo, txtPatente, txtMarca, txtModelo, txtKilometraje;
    private JButton btnAgregarCliente, btnMostrarClientes, btnAgregarVehiculo, btnMostrarVehiculos;
    private JButton btnAgregarVenta, btnMostrarVentas;
    private JComboBox<String> comboClientes, comboVehiculos;
    private JTextField txtValorVenta;

    public VentanaPrincipal() {
        // Configuración del JFrame
        setTitle("Gestión de Clientes, Vehículos y Ventas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Crear tabbedPane para dividir Clientes, Vehículos y Ventas
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(10, 10, 760, 540);

        // Panel de Clientes
        panelClientes = new JPanel();
        panelClientes.setLayout(null);
        crearInterfazClientes(panelClientes);
        tabbedPane.addTab("Clientes", panelClientes);

        // Panel de Vehículos
        panelVehiculos = new JPanel();
        panelVehiculos.setLayout(null);
        crearInterfazVehiculos(panelVehiculos);
        tabbedPane.addTab("Vehículos", panelVehiculos);

        // Panel de Ventas
        panelVentas = new JPanel();
        panelVentas.setLayout(null);
        crearInterfazVentas(panelVentas);
        tabbedPane.addTab("Ventas", panelVentas);

        // Agregar el tabbedPane al JFrame
        add(tabbedPane);
    }

    // Método para crear la interfaz del panel Clientes
    private void crearInterfazClientes(JPanel panel) {
        // Etiquetas y campos de texto
        JLabel lblRut = new JLabel("RUT:");
        lblRut.setBounds(10, 10, 100, 25);
        panel.add(lblRut);

        txtRut = new JTextField();
        txtRut.setBounds(120, 10, 200, 25);
        panel.add(txtRut);

        JLabel lblNombre = new JLabel("Nombre Completo:");
        lblNombre.setBounds(10, 45, 100, 25);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 45, 200, 25);
        panel.add(txtNombre);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(10, 80, 100, 25);
        panel.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(120, 80, 200, 25);
        panel.add(txtTelefono);

        JLabel lblCorreo = new JLabel("Correo Electrónico:");
        lblCorreo.setBounds(10, 115, 120, 25);
        panel.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(140, 115, 200, 25);
        panel.add(txtCorreo);

        // Botones
        btnAgregarCliente = new JButton("Agregar Cliente");
        btnAgregarCliente.setBounds(10, 160, 150, 30);
        panel.add(btnAgregarCliente);

        btnMostrarClientes = new JButton("Mostrar Clientes");
        btnMostrarClientes.setBounds(180, 160, 150, 30);
        panel.add(btnMostrarClientes);

        // Tabla para mostrar clientes
        modeloClientes = new DefaultTableModel(new String[]{"RUT", "Nombre", "Teléfono", "Correo"}, 0);
        tablaClientes = new JTable(modeloClientes);
        JScrollPane scrollClientes = new JScrollPane(tablaClientes);
        scrollClientes.setBounds(10, 210, 720, 250);
        panel.add(scrollClientes);
    }

    // Método para crear la interfaz del panel Vehículos
    private void crearInterfazVehiculos(JPanel panel) {
        // Etiquetas y campos de texto
        JLabel lblPatente = new JLabel("Patente:");
        lblPatente.setBounds(10, 10, 100, 25);
        panel.add(lblPatente);

        txtPatente = new JTextField();
        txtPatente.setBounds(120, 10, 200, 25);
        panel.add(txtPatente);

        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setBounds(10, 45, 100, 25);
        panel.add(lblMarca);

        txtMarca = new JTextField();
        txtMarca.setBounds(120, 45, 200, 25);
        panel.add(txtMarca);

        JLabel lblModelo = new JLabel("Modelo:");
        lblModelo.setBounds(10, 80, 100, 25);
        panel.add(lblModelo);

        txtModelo = new JTextField();
        txtModelo.setBounds(120, 80, 200, 25);
        panel.add(txtModelo);

        JLabel lblKilometraje = new JLabel("Kilometraje:");
        lblKilometraje.setBounds(10, 115, 100, 25);
        panel.add(lblKilometraje);

        txtKilometraje = new JTextField();
        txtKilometraje.setBounds(120, 115, 200, 25);
        panel.add(txtKilometraje);

        // Botones
        btnAgregarVehiculo = new JButton("Agregar Vehículo");
        btnAgregarVehiculo.setBounds(10, 160, 150, 30);
        panel.add(btnAgregarVehiculo);

        btnMostrarVehiculos = new JButton("Mostrar Vehículos");
        btnMostrarVehiculos.setBounds(180, 160, 150, 30);
        panel.add(btnMostrarVehiculos);

        // Tabla para mostrar vehículos
        modeloVehiculos = new DefaultTableModel(new String[]{"Patente", "Marca", "Modelo", "Kilometraje"}, 0);
        tablaVehiculos = new JTable(modeloVehiculos);
        JScrollPane scrollVehiculos = new JScrollPane(tablaVehiculos);
        scrollVehiculos.setBounds(10, 210, 720, 250);
        panel.add(scrollVehiculos);
    }

    // Método para crear la interfaz del panel Ventas
    private void crearInterfazVentas(JPanel panel) {
        // Etiquetas y campos de texto
        JLabel lblCliente = new JLabel("Seleccionar Cliente:");
        lblCliente.setBounds(10, 10, 150, 25);
        panel.add(lblCliente);

        comboClientes = new JComboBox<>();
        comboClientes.setBounds(160, 10, 200, 25);
        panel.add(comboClientes);

        JLabel lblVehiculo = new JLabel("Seleccionar Vehículo:");
        lblVehiculo.setBounds(10, 45, 150, 25);
        panel.add(lblVehiculo);

        comboVehiculos = new JComboBox<>();
        comboVehiculos.setBounds(160, 45, 200, 25);
        panel.add(comboVehiculos);

        JLabel lblValorVenta = new JLabel("Valor Venta:");
        lblValorVenta.setBounds(10, 80, 150, 25);
        panel.add(lblValorVenta);

        txtValorVenta = new JTextField();
        txtValorVenta.setBounds(160, 80, 200, 25);
        panel.add(txtValorVenta);

        // Botones
        btnAgregarVenta = new JButton("Agregar Venta");
        btnAgregarVenta.setBounds(10, 115, 150, 30);
        panel.add(btnAgregarVenta);

        btnMostrarVentas = new JButton("Mostrar Ventas");
        btnMostrarVentas.setBounds(180, 115, 150, 30);
        panel.add(btnMostrarVentas);

        // Tabla para mostrar ventas
        modeloVentas = new DefaultTableModel(new String[]{"Cliente", "Vehículo", "Valor Venta"}, 0);
        tablaVentas = new JTable(modeloVentas);
        JScrollPane scrollVentas = new JScrollPane(tablaVentas);
        scrollVentas.setBounds(10, 160, 720, 250);
        panel.add(scrollVentas);
    }

    // Getters para acceder a los componentes desde otros lugares
    public JButton getBtnAgregarCliente() {
        return btnAgregarCliente;
    }

    public JButton getBtnMostrarClientes() {
        return btnMostrarClientes;
    }

    public JButton getBtnAgregarVehiculo() {
        return btnAgregarVehiculo;
    }

    public JButton getBtnMostrarVehiculos() {
        return btnMostrarVehiculos;
    }

    public JButton getBtnAgregarVenta() {
        return btnAgregarVenta;
    }

    public JButton getBtnMostrarVentas() {
        return btnMostrarVentas;
    }

    public DefaultTableModel getModeloClientes() {
        return modeloClientes;
    }

    public DefaultTableModel getModeloVehiculos() {
        return modeloVehiculos;
    }

    public DefaultTableModel getModeloVentas() {
        return modeloVentas;
    }

    public JComboBox<String> getComboClientes() {
        return comboClientes;
    }

    public JComboBox<String> getComboVehiculos() {
        return comboVehiculos;
    }

    public JTextField getTxtValorVenta() {
        return txtValorVenta;
    }
}
