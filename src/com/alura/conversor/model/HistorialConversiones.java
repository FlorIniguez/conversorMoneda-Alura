package com.alura.conversor.model;

import java.util.ArrayList;
import java.util.List;

public class HistorialConversiones {
    private List<Conversion> historial;

    public HistorialConversiones() {
        this.historial = new ArrayList<>();
    }

    public void agregarConversion(Conversion conversion) {
        historial.add(conversion);
    }

    public List<Conversion> obtenerHistorial() {
        return historial;
    }
}


