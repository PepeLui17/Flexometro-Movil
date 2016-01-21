package com.example.joseromero.flexometromovil;

/**
 * Created by joseromero on 1/20/16.
 */
public class Medicion {
    private String nombre;
    private float ancho;
    private float alto;

    public Medicion(String nombre, float ancho, float alto) {
        this.nombre = nombre;
        this.ancho = ancho;
        this.alto = alto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getAncho() {
        return ancho;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    public float getAlto() {
        return alto;
    }

    public void setAlto(float alto) {
        this.alto = alto;
    }
}
