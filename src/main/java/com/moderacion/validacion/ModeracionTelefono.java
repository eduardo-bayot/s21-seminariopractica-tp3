package com.moderacion.validacion;

import com.moderacion.core.IModeracion;
import com.moderacion.modelo.Mensaje;
import java.util.regex.Pattern;

/**
 * Clase que implementa la validación de números de teléfono en un mensaje.
 */
public class ModeracionTelefono implements IModeracion {

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "\\+?\\d{1,4}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}");

    @Override
    public boolean validar(Mensaje mensaje) {
        String contenido = mensaje.getContenido();

        if (PHONE_PATTERN.matcher(contenido).find()) {
            mensaje.setEstado(Mensaje.EstadoMensaje.BLOQUEADO);
            mensaje.setRazonBloqueo("Contiene número de teléfono");
            return true;
        }

        return false;
    }
}

