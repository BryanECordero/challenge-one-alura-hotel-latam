package models;

import java.time.LocalDate;

public class Reserva {
    int id;
    LocalDate fechaDeEntrada;
    LocalDate fechaDeSalida;
    Float valor;
    String formaDePago;

    public Reserva(LocalDate fechaDeEntrada, LocalDate fechaDeSalida, Float valor, String formaDePago) {
        this.fechaDeEntrada = fechaDeEntrada;
        this.fechaDeSalida = fechaDeSalida;
        this.valor = valor;
        this.formaDePago = formaDePago;
    }

    public Reserva(int id, LocalDate fechaDeEntrada, LocalDate fechaDeSalida, Float valor, String formaDePago) {
        this.id = id;
        this.fechaDeEntrada = fechaDeEntrada;
        this.fechaDeSalida = fechaDeSalida;
        this.valor = valor;
        this.formaDePago = formaDePago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaDeEntrada() {
        return fechaDeEntrada;
    }

    public void setFechaDeEntrada(LocalDate fechaDeEntrada) {
        this.fechaDeEntrada = fechaDeEntrada;
    }

    public LocalDate getFechaDeSalida() {
        return fechaDeSalida;
    }

    public void setFechaDeSalida(LocalDate fechaDeSalida) {
        this.fechaDeSalida = fechaDeSalida;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    @Override
    public String toString() {
        return "Reserva [id=" + id + ", fechaDeEntrada=" + fechaDeEntrada + ", fechaDeSalida=" + fechaDeSalida
                + ", valor=" + valor + ", formaDePago=" + formaDePago + "]";
    }

}
