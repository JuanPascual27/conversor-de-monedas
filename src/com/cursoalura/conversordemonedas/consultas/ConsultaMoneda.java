package com.cursoalura.conversordemonedas.consultas;

import com.cursoalura.conversordemonedas.modelos.ConversionMoneda;
import com.cursoalura.conversordemonedas.modelos.ConversionMonedaDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
            ConversionMonedaDTO conversion = new Gson().fromJson(response.body(), ConversionMonedaDTO.class);
            return new ConversionMoneda(conversion, cantidad);
        } catch(IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void consultarMonedas() {
        URI direccion = URI.create(urlBase + "/codes");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonArray monedas = jsonObject.getAsJsonArray("supported_codes");

            for (var moneda : monedas) {
                JsonArray par = moneda.getAsJsonArray();
                String codigo = par.get(0).getAsString();
                String nombre = par.get(1).getAsString();
                System.out.println("[" + codigo + "]: " + nombre);
            }
        } catch(IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
