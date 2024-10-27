package com.moderacion.dao;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class InMemoryPalabraProhibidaDAO implements PalabraProhibidaDAO {

    private Set<String> palabrasProhibidas;

    public InMemoryPalabraProhibidaDAO() {
        this.palabrasProhibidas = new TreeSet<>();
        // Agregar palabras prohibidas de ejemplo
        this.palabrasProhibidas.add("palabraprohibida1");
        this.palabrasProhibidas.add("palabraprohibida2");
        this.palabrasProhibidas.add("palabraprohibida3");
    }

    @Override
    public Set<String> obtenerPrimeraPalabraProhibida(Set<String> palabrasMensaje) {
        for (String palabra : palabrasMensaje) {
            if (palabrasProhibidas.contains(palabra)) {
                Set<String> coincidencia = new HashSet<>();
                coincidencia.add(palabra);
                return coincidencia;
            }
        }
        return new HashSet<>();
    }
}

