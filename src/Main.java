import com.alura.conversor.api.ConsultaApi;
import com.alura.conversor.api.FiltroTasas;
import com.alura.conversor.calculador.CalculadorCambio;

import java.io.IOException;
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


        Scanner teclado = new Scanner(System.in);

        int option = 0;
        double cantidad = 0;


        while (option != 7) {
            System.out.println("Bienvenido al conversor de moneda");
            System.out.println("Elija el tipo de cambio que desea realizar");
            System.out.println("1. ARS a USD");
            System.out.println("2. USD a ARS");
            System.out.println("3. BRL a USD");
            System.out.println("4. USD a BRL");
            System.out.println("5. COD a USD");
            System.out.println("6. USD a COD");
            System.out.println("7. Salir");

            System.out.print("Ingrese una opción: ");
            option = teclado.nextInt();

            if (option >= 1 && option <= 6) {
                System.out.print("Ingrese la cantidad  a convertir: ");
                cantidad = teclado.nextDouble();


                // Realizar la conversión de moneda según la opción seleccionada
                switch (option) {
                    case 1:
                        System.out.println(cantidad + " ARS equivale a " + calculadorCambio.convertir(cantidad, "ARS", "USD") + " USD");

                        break;
                    case 2:
                        System.out.println(cantidad + " USD equivale a " + calculadorCambio.convertir(cantidad, "USD", "ARS") + " ARS");
                        break;
                    case 3:
                        System.out.println(cantidad + " BRL equivale a " + calculadorCambio.convertir(cantidad, "BRL", "USD") + " USD");
                        break;
                    case 4:
                        System.out.println(cantidad + " USD equivale a " + calculadorCambio.convertir(cantidad, "USD", "BRL") + " BRL");
                        break;
                    case 5:
                        System.out.println(cantidad + " COD equivale a " + calculadorCambio.convertir(cantidad, "COD", "USD") + " USD");
                        break;
                    case 6:
                        System.out.println(cantidad + " USD equivale a " + calculadorCambio.convertir(cantidad, "USD", "COD") + " COD");
                        break;
                }
            } else if (option != 7) {
                System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 7.");
            }

        }


    }
}