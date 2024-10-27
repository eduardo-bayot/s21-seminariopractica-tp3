package com.moderacion.modelo;

/**
 * Clase que representa una palabra prohibida en el sistema.
 * Incluye el ID y el texto de la palabra prohibida.
 */
public class PalabraProhibida {

    private int id;
    private String palabra;

    public PalabraProhibida(int id, String palabra) {
        this.id = id;
        this.palabra = palabra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
}

