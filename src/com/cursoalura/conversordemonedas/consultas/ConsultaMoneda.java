package com.cursoalura.conversordemonedas.consultas;

import com.cursoalura.conversordemonedas.modelos.ConversionMoneda;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {
    private final String urlBase = "https://v6.exchangerate-api.com/v6/3e9a0333a3381a44c6aa1143";

    public ConversionMoneda conversionMoneda(String monedaBase, String monedaObjetivo, double cantidad) {
        URI direccion = URI.create(urlBase + "/pair/" + monedaBase + "/" + monedaObjetivo + "/" + cantidad);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), ConversionMoneda.class);
        } catch(IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
