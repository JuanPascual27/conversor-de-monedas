package com.cursoalura.conversordemonedas.principal;

import com.cursoalura.conversordemonedas.acciones.AccionesMoneda;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String textoMenu = """
                ********************************************************************************
                "Bienvenido/a al conversor de monedas"
                
                1) Dólar >>> Peso mexicano
                2) Peso mexicano >>> Dólar
                3) Dólar >>> Peso argentino
                4) Peso argentino >>> Dólar
                5) Dólar >>> Peso colombiano
                6) Peso colombiano >>> Dólar
                7) Salir
                ********************************************************************************
                Elija una opción válida:
                """;
        AccionesMoneda acciones = new AccionesMoneda();
        Scanner teclado = new Scanner(System.in);

        int opcion = 0;
        while(opcion != 7) {
            System.out.print(textoMenu);
            try {
                opcion = teclado.nextInt();
            } catch (InputMismatchException e) {
                teclado.next();
                opcion = 0;
            }
            switch(opcion) {
                case 1:
                    acciones.convertir("USD", "MXN");
                    break;
                case 2:
                    acciones.convertir("MXN", "USD");
                    break;
                case 3:
                    acciones.convertir("USD", "ARS");
                    break;
                case 4:
                    acciones.convertir("ARS", "USD");
                    break;
                case 5:
                    acciones.convertir("USD", "COP");
                    break;
                case 6:
                    acciones.convertir("COP", "USD");
                    break;
                case 7:
                    System.out.println("Gracias por usar el conversor de monedas");
                    break;
                default:
                    System.out.println("Ingrese una opción válida por favor");
            }
        }
    }
}
