package com.cursoalura.conversordemonedas.acciones;

import com.cursoalura.conversordemonedas.consultas.ConsultaMoneda;
import com.cursoalura.conversordemonedas.excepciones.CodigoMonedaException;
import com.cursoalura.conversordemonedas.modelos.ConversionMoneda;
import com.cursoalura.conversordemonedas.modelos.ConversionMonedaDTO;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AccionesMoneda {
    private List<ConversionMoneda> conversiones;
    private GeneradorDeArchivo archivo;

    public AccionesMoneda() {
        archivo = new GeneradorDeArchivo();
        conversiones = archivo.cargarConversiones();
    }

    public void convertir(String monedaBase, String monedaObjetivo) {
        Scanner teclado = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();

        System.out.println("Ingrese el valor que desea convertir: ");
        try {
            double cantidad = teclado.nextDouble();
            ConversionMoneda resultado = consulta.conversionMoneda(monedaBase, monedaObjetivo, cantidad);
            if (resultado.getTasaDeConversion() == 0)
                throw new CodigoMonedaException("El o los códigos de moneda no son válidos");
            System.out.printf("El valor %.4f [%s] corresponde al valor final de >>> : %.4f [%s]\n",
                    cantidad, monedaBase, resultado.getResultado(), monedaObjetivo);
            System.out.println("Con una taza de conversión de: " + resultado.getTasaDeConversion());

            conversiones.add(resultado);
            archivo.guardarConversion(conversiones);
        } catch (InputMismatchException e) {
            System.out.println("Verifique la cantidad e intentelo nuevamente");
        } catch (CodigoMonedaException e) {
            System.out.println(e.getMessage());
        }
    }

    public void convertirOtraMoneda() {
        Scanner teclado = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();

        System.out.println("Monedas disponibles: ");
        consulta.consultarMonedas();
        System.out.println("Ingrese el código de la moneda base: ");
        String monedaBase = teclado.nextLine().toUpperCase();
        System.out.println("Ingrese el código de la moneda objetivo: ");
        String monedaObjetivo = teclado.nextLine().toUpperCase();
        convertir(monedaBase, monedaObjetivo);
    }

    public void verHistorial() {
        System.out.println("Historial de conversiones: ");
        for (ConversionMoneda conversion : conversiones) {
            System.out.println("[" + conversion.getHora() + " - " + conversion.getFecha() +
                    "] -> " + conversion.getCantidad() + "[" + conversion.getMonedaBase() +
                    "] >>> " + conversion.getResultado() + "[" + conversion.getMonedaObjetivo() +
                    "]");
        }
    }
}
