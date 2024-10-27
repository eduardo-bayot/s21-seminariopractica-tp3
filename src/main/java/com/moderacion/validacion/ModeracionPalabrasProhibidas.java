package com.moderacion.validacion;

import com.moderacion.core.IModeracion;
import com.moderacion.dao.PalabraProhibidaDAO;
import com.moderacion.modelo.Mensaje;

import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Clase de moderación que verifica si el mensaje contiene palabras prohibidas.
 * Bloquea el mensaje si se detecta alguna palabra prohibida.
 */
public class ModeracionPalabrasProhibidas implements IModeracion {

    private PalabraProhibidaDAO palabraProhibidaDAO;

    public ModeracionPalabrasProhibidas(PalabraProhibidaDAO palabraProhibidaDAO) {
        this.palabraProhibidaDAO = palabraProhibidaDAO;
    }

    @Override
    public boolean validar(Mensaje mensaje) {
        Set<String> palabrasMensaje = Arrays.stream(mensaje.getContenido().toLowerCase().split("\\s+"))
                                            .collect(Collectors.toCollection(TreeSet::new));

        Set<String> palabraProhibida = palabraProhibidaDAO.obtenerPrimeraPalabraProhibida(palabrasMensaje);

        if (!palabraProhibida.isEmpty()) {
            mensaje.setEstado(Mensaje.EstadoMensaje.BLOQUEADO);
            mensaje.setRazonBloqueo("Contiene palabra prohibida: " + palabraProhibida.iterator().next());
            return true;
        }
        return false;
    }
}
