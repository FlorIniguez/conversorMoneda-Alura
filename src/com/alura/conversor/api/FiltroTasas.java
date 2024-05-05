package com.alura.conversor.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class FiltroTasas {

    public Map<String, Double> filtrarTasas(String jsonResponse) {
        //parsear con Gson y convertir en un obj
        //JsonObject tiene metodos para "sacar la info"
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonResponse).getAsJsonObject();

        // Crear mapa para almacenar tasas filtradas
        Map<String, Double> tasasFiltradas = new HashMap<>();

        //del objetoJson traigo las tasasa de cambio
        // Filtrar las tasas de cambio para las monedas específicas
        String[] currenciesToFilter = {"ARS", "COP", "BRL", "USD"};
        for (String currency : currenciesToFilter) {
            //jsonObject.getAsJsonObject("conversion_rates")accede al objeto JSON que tiene las tasas de cambio.
            //busca la propiedad llamada "conversion_rates" dentro del objeto JSON principal

            //get(currency) busca la propiedad correspondiente al código de moneda dentro del objeto JSON que representa las tasas de cambio y devuelve su valor.
            //.getAsDouble(): Este método convierte el valor de la propiedad (en este caso, la tasa de cambio) en un tipo double
            double tipoCambio = jsonObject.getAsJsonObject("conversion_rates").get(currency).getAsDouble();
            tasasFiltradas.put(currency, tipoCambio);
        }

        // Devolver las tasas filtradas
        return tasasFiltradas;
    }


}
