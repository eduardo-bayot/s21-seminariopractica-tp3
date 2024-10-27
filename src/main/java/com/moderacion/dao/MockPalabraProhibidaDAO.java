package com.moderacion.dao;

import java.util.HashSet;
import java.util.Set;

public class MockPalabraProhibidaDAO implements PalabraProhibidaDAO {

    private Set<String> palabrasProhibidasMock;

    public MockPalabraProhibidaDAO() {
        this.palabrasProhibidasMock = new HashSet<>();
    }

    public void agregarPalabraProhibida(String palabra) {
        palabrasProhibidasMock.add(palabra);
    }

    @Override
    public Set<String> obtenerPrimeraPalabraProhibida(Set<String> palabrasMensaje) {
        Set<String> coincidencias = new HashSet<>();
        for (String palabra : palabrasMensaje) {
            if (palabrasProhibidasMock.contains(palabra)) {
                coincidencias.add(palabra);
                return coincidencias;
            }
        }
        return coincidencias;
    }
}

