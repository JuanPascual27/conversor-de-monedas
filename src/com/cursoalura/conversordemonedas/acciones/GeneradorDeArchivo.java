package com.cursoalura.conversordemonedas.acciones;

import com.cursoalura.conversordemonedas.modelos.ConversionMoneda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GeneradorDeArchivo {
    private final String archivo = "historialDeConversiones.json";
    public void guardarConversion(List<ConversionMoneda> conversiones) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter escritura = new FileWriter(archivo)) {
            escritura.write(gson.toJson(conversiones));
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo JSON", e);
        }
    }

    public List<ConversionMoneda> cargarConversiones() {
        Gson gson = new Gson();
        try (FileReader lectura = new FileReader(archivo)) {
            Type listType = new TypeToken<ArrayList<ConversionMoneda>>(){}.getType();
            List<ConversionMoneda> conversiones = gson.fromJson(lectura, listType);
            return conversiones != null ? conversiones : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
