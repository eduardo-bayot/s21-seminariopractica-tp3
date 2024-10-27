package com.moderacion.dao;

import com.moderacion.modelo.PalabraPeso;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryPalabraPesoDAO implements PalabraPesoDAO {

    private Map<String, PalabraPeso> palabrasPeso = new HashMap<>();

    public InMemoryPalabraPesoDAO() {
        // Ejemplos de palabras con posibles dobles significados
        palabrasPeso.put("banana", new PalabraPeso("banana", 3));
        palabrasPeso.put("carne", new PalabraPeso("carne", 5));
        palabrasPeso.put("empanada", new PalabraPeso("empanada", 2));
        palabrasPeso.put("pollo", new PalabraPeso("pollo", 2));
        palabrasPeso.put("perro", new PalabraPeso("perro", 3));
        palabrasPeso.put("gato", new PalabraPeso("gato", 2));
        palabrasPeso.put("fiesta", new PalabraPeso("fiesta", 3));
        palabrasPeso.put("chocolate", new PalabraPeso("chocolate", 4));
        palabrasPeso.put("queso", new PalabraPeso("queso", 1));
        palabrasPeso.put("vino", new PalabraPeso("vino", 2));
        palabrasPeso.put("cerveza", new PalabraPeso("cerveza", 3));
        palabrasPeso.put("pan", new PalabraPeso("pan", 1));
        palabrasPeso.put("leche", new PalabraPeso("leche", 4));
        palabrasPeso.put("salchicha", new PalabraPeso("salchicha", 5));
        palabrasPeso.put("dulce", new PalabraPeso("dulce", 2));
        palabrasPeso.put("rojo", new PalabraPeso("rojo", 1));
        palabrasPeso.put("verde", new PalabraPeso("verde", 1));
        palabrasPeso.put("azul", new PalabraPeso("azul", 1));
        palabrasPeso.put("cielo", new PalabraPeso("cielo", 2));
        palabrasPeso.put("tierra", new PalabraPeso("tierra", 2));

        // Puedes agregar más palabras de ser necesario
    }

    @Override
    public Optional<PalabraPeso> obtenerPesoPalabra(String palabra) {
        return Optional.ofNullable(palabrasPeso.get(palabra));
    }
}

