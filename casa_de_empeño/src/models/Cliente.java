package models;

public class Cliente {

    private int idCliente;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private String direccion;

    public Cliente(
        int idCliente,
        String nombres,
        String apellidos,
        String telefono,
        String correo,
        String direccion
    ) {

        this.idCliente = idCliente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }
}