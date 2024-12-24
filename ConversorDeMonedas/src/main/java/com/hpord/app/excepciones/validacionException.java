package com.hpord.app.excepciones;

public class validacionException extends RuntimeException{
    private String mensaje;

    public validacionException(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}
