package com.moderacion.dao;

import com.moderacion.modelo.PalabraPeso;
import java.util.Optional;

/**
 * DAO para acceder a los pesos de las palabras.
 */
public interface PalabraPesoDAO {
    Optional<PalabraPeso> obtenerPesoPalabra(String palabra);
}

