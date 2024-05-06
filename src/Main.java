import com.alura.conversor.api.ConsultaApi;
import com.alura.conversor.api.FiltroTasas;
import com.alura.conversor.calculador.CalculadorCambio;
import com.alura.conversor.menu.ConversorMonedaApp;
import com.alura.conversor.model.Conversion;
import com.alura.conversor.model.HistorialConversiones;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ConversorMonedaApp conversorMonedaApp = new ConversorMonedaApp();
        conversorMonedaApp.iniciar();

    }
}