package com.moderacion.dao;

import java.util.Set;

public interface PalabraProhibidaDAO {
    Set<String> obtenerPrimeraPalabraProhibida(Set<String> palabrasMensaje);
}
