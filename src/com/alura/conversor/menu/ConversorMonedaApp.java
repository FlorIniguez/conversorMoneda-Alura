package com.alura.conversor.menu;

import com.alura.conversor.api.ConsultaApi;
import com.alura.conversor.api.FiltroTasas;
import com.alura.conversor.calculador.CalculadorCambio;
import com.alura.conversor.model.Conversion;
import com.alura.conversor.model.HistorialConversiones;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConversorMonedaApp {
    private final ConsultaApi consultaApi;
    private final FiltroTasas filtroTasas;
    private final CalculadorCambio calculadorCambio;
    private final HistorialConversiones historial;

    public ConversorMonedaApp() throws IOException, InterruptedException {
        this.consultaApi = new ConsultaApi();
        this.filtroTasas = new FiltroTasas();
        // Obtener el JSON de tasas de cambio
        String jsonResponse = consultaApi.consultaCambio();
        // Filtrar las tasas de cambio y capturarlas
        Map<String, Double> tasasFiltradas = filtroTasas.filtrarTasas(jsonResponse);
        // Crear instancia de CalculadorCambio y pasar las tasas filtradas
        this.calculadorCambio = new CalculadorCambio(tasasFiltradas);
        this.historial = new HistorialConversiones();
    }


    public void iniciar() {
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 8) {
            mostrarMenu();
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    realizarConversion(opcion);
                    break;
                case 7:
                    mostrarHistorial();
                    break;
                case 8:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 8.");
            }
        }
    }

    private void mostrarHistorial() {
        List<Conversion> historialDeConversiones = historial.obtenerHistorial();
        for (Conversion conversion : historialDeConversiones) {
            System.out.println(conversion); // Imprimir cada conversión en la consola
        }
    }

    private void mostrarMenu() {
        System.out.println("Bienvenido al conversor de moneda");
        System.out.println("Elija el tipo de cambio que desea realizar");
        System.out.println("1. ARS a USD");
        System.out.println("2. USD a ARS");
        System.out.println("3. BRL a USD");
        System.out.println("4. USD a BRL");
        System.out.println("5. COD a USD");
        System.out.println("6. USD a COD");
        System.out.println("7. Ver historial");
        System.out.println("8. Salir");
        System.out.print("Ingrese una opción: ");
    }

    private void realizarConversion(int opcion) {
        try {
            Map<String, Double> tasasFiltradas = filtroTasas.filtrarTasas(consultaApi.consultaCambio());
            double cantidad = leerCantidad();

            String monedaOrigen, monedaDestino;
            switch (opcion) {
                case 1:
                    monedaOrigen = "ARS";
                    monedaDestino = "USD";
                    break;
                case 2:
                    monedaOrigen = "USD";
                    monedaDestino = "ARS";
                    break;
                case 3:
                    monedaOrigen = "BRL";
                    monedaDestino = "USD";
                    break;
                case 4:
                    monedaOrigen = "USD";
                    monedaDestino = "BRL";
                    break;
                case 5:
                    monedaOrigen = "COP";
                    monedaDestino = "USD";
                    break;
                case 6:
                    monedaOrigen = "USD";
                    monedaDestino = "COP";
                    break;
                default:
                    monedaOrigen = "";
                    monedaDestino = "";
            }

            if (!monedaOrigen.isEmpty() && !monedaDestino.isEmpty()) {
                double resultado = calculadorCambio.convertir(cantidad, monedaOrigen, monedaDestino);
                System.out.println(cantidad + " " + monedaOrigen + " equivale a " + resultado + " " + monedaDestino);
                historial.agregarConversion(new Conversion(monedaOrigen, monedaDestino, cantidad, LocalDateTime.now()));
            } else {
                System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 7.");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al realizar la conversión: " + e.getMessage());
        }
    }


    private double leerCantidad() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese la cantidad a convertir");
        return teclado.nextDouble();
    }


}


