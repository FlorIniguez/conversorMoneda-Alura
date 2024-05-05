package com.alura.conversor.calculador;

import com.alura.conversor.api.FiltroTasas;

import java.util.Map;

public class CalculadorCambio {
    private final Map<String, Double> tasasFiltradas;

    // el constructor recibe este mapa de tasas filtradas y lo almacena en un campo de la clase
    public CalculadorCambio(Map<String, Double> tasasFiltradas) {
        this.tasasFiltradas = tasasFiltradas;
    }

    public double convertir(double cantidad, String monedaOrigen, String monedaDestino) {
        // Verificar si las monedas están presentes en las tasas filtradas
        if (tasasFiltradas.containsKey(monedaOrigen) && tasasFiltradas.containsKey(monedaDestino)) {
            // Obtener tasas de cambio para las monedas especificadas
            //Con el get obtengo el double, valor, del Map (clave,valor)
            double tasaOrigen = tasasFiltradas.get(monedaOrigen);
            double tasaDestino = tasasFiltradas.get(monedaDestino);

            // conversión utilizando las tasas de cambio y la cantidad proporcionada.
            return cantidad * (1 / tasaOrigen) * tasaDestino;
        } else {
            // Si alguna de las monedas no está en las tasas filtradas, lanzar una excepción o manejar el error de alguna otra manera
            throw new IllegalArgumentException("Moneda no admitida");
        }
    }


}
