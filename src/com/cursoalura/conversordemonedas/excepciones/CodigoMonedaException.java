package com.cursoalura.conversordemonedas.excepciones;

public class CodigoMonedaException extends RuntimeException {
    private final String mensaje;

    public CodigoMonedaException(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}
