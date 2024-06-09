package com.cursoalura.conversordemonedas.acciones;

import com.cursoalura.conversordemonedas.consultas.ConsultaMoneda;
import com.cursoalura.conversordemonedas.excepciones.CodigoMonedaException;
import com.cursoalura.conversordemonedas.modelos.ConversionMoneda;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccionesMoneda {
    public void convertir(String monedaBase, String monedaObjetivo) {
        Scanner teclado = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();

        System.out.println("Ingrese el valor que desea convertir: ");
        try {
            double cantidad = teclado.nextDouble();
            ConversionMoneda resultado = consulta.conversionMoneda(monedaBase, monedaObjetivo, cantidad);
            if (resultado.conversion_rate() == 0)
                throw new CodigoMonedaException("El o los códigos de moneda no son válidos");
            System.out.printf("El valor %.4f [%s] corresponde al valor final de >>> : %.4f [%s]\n",
                    cantidad, monedaBase, resultado.conversion_result(), monedaObjetivo);
            System.out.println("Con una taza de conversión de: " + resultado.conversion_rate());
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
}
