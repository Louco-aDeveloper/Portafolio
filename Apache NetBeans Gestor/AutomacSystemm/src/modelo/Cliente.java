package modelo;

public class Cliente {
    private String rut;
    private String nombreCompleto;
    private String telefono;
    private String correoElectronico;

    // Constructor
    public Cliente(String rut, String nombreCompleto, String telefono, String correoElectronico) {
        this.rut = rut;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    // Getters y Setters
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    // Método toString para mostrar datos
    @Override
    public String toString() {
        return "Cliente{" +
               "RUT='" + rut + '\'' +
               ", Nombre='" + nombreCompleto + '\'' +
               ", Teléfono='" + telefono + '\'' +
               ", Correo='" + correoElectronico + '\'' +
               '}';
    }
}
