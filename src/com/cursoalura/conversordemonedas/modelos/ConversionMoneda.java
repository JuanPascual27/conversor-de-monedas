package com.cursoalura.conversordemonedas.modelos;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConversionMoneda {
    private String monedaBase;
    private String monedaObjetivo;
    private double tasaDeConversion;
    private double cantidad;
    private double resultado;
    private String fecha;
    private String hora;

    public ConversionMoneda() {}

    public ConversionMoneda(ConversionMonedaDTO conversion, double cantidad) {
        this.monedaBase = conversion.base_code();
        this.monedaObjetivo = conversion.target_code();
        this.tasaDeConversion = conversion.conversion_rate();
        this.cantidad = cantidad;
        this.resultado = conversion.conversion_result();
        this.fecha = LocalDate.now().toString();
        this.hora = LocalTime.now().toString().substring(0, 8);
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public void setMonedaBase(String monedaBase) {
        this.monedaBase = monedaBase;
    }

    public String getMonedaObjetivo() {
        return monedaObjetivo;
    }

    public void setMonedaObjetivo(String monedaObjetivo) {
        this.monedaObjetivo = monedaObjetivo;
    }

    public double getTasaDeConversion() {
        return tasaDeConversion;
    }

    public void setTasaDeConversion(double tasaDeConversion) {
        this.tasaDeConversion = tasaDeConversion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
