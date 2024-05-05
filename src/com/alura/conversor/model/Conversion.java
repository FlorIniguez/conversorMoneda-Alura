package com.alura.conversor.model;

public class Conversion {
    private String monedaOrigen;
    private String monedaDestino;
    private double cantidad;
   // private String fechaHora;

    public Conversion(String monedaOrigen, String monedaDestino, double cantidad) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.cantidad = cantidad;
       // this.fechaHora = fechaHora;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public void setMonedaOrigen(String monedaOrigen) {
        this.monedaOrigen = monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public void setMonedaDestino(String monedaDestino) {
        this.monedaDestino = monedaDestino;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }



    @Override
    public String toString() {
        return "De " + monedaOrigen + " a " + monedaDestino + ": " + cantidad;
    }
}
