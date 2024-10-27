package com.moderacion.dao;

import com.moderacion.modelo.PalabraPeso;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MockPalabraPesoDAO implements PalabraPesoDAO {

    private Map<String, PalabraPeso> palabrasPeso = new HashMap<>();

    public MockPalabraPesoDAO() {
        palabrasPeso.put("banana", new PalabraPeso("banana", 3));
        palabrasPeso.put("carne", new PalabraPeso("carne", 5));
        palabrasPeso.put("empanada", new PalabraPeso("empanada", 2));
        palabrasPeso.put("fiesta", new PalabraPeso("fiesta", 3));
    }

    @Override
    public Optional<PalabraPeso> obtenerPesoPalabra(String palabra) {
        return Optional.ofNullable(palabrasPeso.get(palabra));
    }
}

