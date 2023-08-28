package models;

import java.time.LocalDate;

public class Huesped {
    private int id;
    private String nombre, apellido;
    private LocalDate fechaNacimiento;
    private String nacionalidad, telefono;
    private int idReserva;
    private Reserva reserva;

    public Huesped(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, String telefono,
            Reserva reserva) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.reserva = reserva;
        this.idReserva = reserva.getId();
    }

    public Huesped(int id, String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad,
            String telefono, int idReserva) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.idReserva = idReserva;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Huesped [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento="
                + fechaNacimiento + ", nacionalidad=" + nacionalidad + ", telefono=" + telefono + ", idReserva="
                + idReserva + ", reserva=" + reserva + "]";
    }

}
