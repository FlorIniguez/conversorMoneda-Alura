package com.alura.conversor.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApi {
    public String consultaCambio() throws IOException, InterruptedException {
        //ENVIO SOLICITUD HTTP
        URI url_str = URI.create("https://v6.exchangerate-api.com/v6/989e1651e746799b3b3fb305/latest/USD");
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(url_str)
                .GET()
                .build();

        //Enviamos la solicitud al servidor y obtenemos la respuesta HTTP como string
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();

    }
}
