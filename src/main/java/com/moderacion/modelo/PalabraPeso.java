package com.moderacion.modelo;

/**
 * Clase que representa una palabra y su peso asociado.
 */
public class PalabraPeso {
    
    private String palabra;
    private int peso;

    public PalabraPeso(String palabra, int peso) {
        this.palabra = palabra;
        this.peso = peso;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}

