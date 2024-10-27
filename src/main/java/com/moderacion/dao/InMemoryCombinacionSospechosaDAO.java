package com.moderacion.dao;

import com.moderacion.modelo.CombinacionSospechosa;
import java.util.HashSet;
import java.util.Set;

public class InMemoryCombinacionSospechosaDAO implements CombinacionSospechosaDAO {

    private static InMemoryCombinacionSospechosaDAO instancia;
    private Set<CombinacionSospechosa> combinaciones;

    private InMemoryCombinacionSospechosaDAO() {
        combinaciones = new HashSet<>();
        combinaciones.add(new CombinacionSospechosa("enviar", "dinero"));
        combinaciones.add(new CombinacionSospechosa("compartir", "contraseña"));
    }

    public static synchronized InMemoryCombinacionSospechosaDAO instancia() {
        if (instancia == null) {
            instancia = new InMemoryCombinacionSospechosaDAO();
        }
        return instancia;
    }

    @Override
    public Set<CombinacionSospechosa> obtenerCombinacionesSospechosas() {
        return combinaciones;
    }
}

