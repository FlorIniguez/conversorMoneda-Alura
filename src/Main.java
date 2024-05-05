import com.alura.conversor.api.ConsultaApi;
import com.alura.conversor.api.FiltroTasas;
import com.alura.conversor.calculador.CalculadorCambio;
import com.alura.conversor.model.Conversion;
import com.alura.conversor.model.HistorialConversiones;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ConsultaApi consultaApi = new ConsultaApi();
        String jsonResponse = consultaApi.consultaCambio();
        FiltroTasas filtroTasas = new FiltroTasas();

        // Filtrar las tasas de cambio y capturarlas
        Map<String, Double> tasasFiltradas = filtroTasas.filtrarTasas(jsonResponse);

        // Crear instancia de CalculadorCambio y pasar las tasas filtradas
        CalculadorCambio calculadorCambio = new CalculadorCambio(tasasFiltradas);
        // Crear instancia de HistorialConversiones
        HistorialConversiones historial = new HistorialConversiones();


        Scanner teclado = new Scanner(System.in);

        int option = 0;
        double cantidad = 0;


        while (option != 8) {
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
            option = teclado.nextInt();

            if (option >= 1 && option <= 6) {
                System.out.print("Ingrese la cantidad  a convertir: ");
                cantidad = teclado.nextDouble();

                // Realizar la conversión de moneda según la opción seleccionada
                String monedaOrigen, monedaDestino;
                switch (option) {
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
                    // Realizar la conversión y mostrar el resultado
                    double resultado = calculadorCambio.convertir(cantidad, monedaOrigen, monedaDestino);
                    System.out.println(cantidad + " " + monedaOrigen + " equivale a " + resultado + " " + monedaDestino);

                    System.out.println("\n ***********************************");

                    // Agregar la conversión al historial
                    historial.agregarConversion(new Conversion(monedaOrigen, monedaDestino, cantidad));

                } else {
                    System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 7.");
                }
            } else if (option == 7) {
                // Mostrar el historial de conversiones
                System.out.println("Historial de conversiones:");
                List<Conversion> conversiones = historial.obtenerHistorial();
                for (Conversion conversion : conversiones) {
                    System.out.println(conversion);
                }
                System.out.println("\n ***********************************");
            } else if (option > 8) {
                System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 8.");
                System.out.println("\n ***********************************");
            }
        }


    }
}