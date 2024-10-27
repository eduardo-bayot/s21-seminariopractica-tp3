package com.moderacion.dao;

import com.moderacion.modelo.CombinacionSospechosa;
import java.util.HashSet;
import java.util.Set;

public class MockCombinacionSospechosaDAO implements CombinacionSospechosaDAO {

    @Override
    public Set<CombinacionSospechosa> obtenerCombinacionesSospechosas() {
        Set<CombinacionSospechosa> combinaciones = new HashSet<>();
        combinaciones.add(new CombinacionSospechosa("enviar", "dinero"));
        combinaciones.add(new CombinacionSospechosa("compartir", "contraseña"));
        return combinaciones;
    }
}

