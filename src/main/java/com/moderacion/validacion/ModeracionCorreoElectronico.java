package com.moderacion.validacion;

import com.moderacion.core.IModeracion;
import com.moderacion.modelo.Mensaje;
import java.util.regex.Pattern;

/**
 * Clase que implementa la validación de correos electrónicos en un mensaje.
 */
public class ModeracionCorreoElectronico implements IModeracion {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");

    @Override
    public boolean validar(Mensaje mensaje) {
        String contenido = mensaje.getContenido();

        if (EMAIL_PATTERN.matcher(contenido).find()) {
            mensaje.setEstado(Mensaje.EstadoMensaje.BLOQUEADO);
            mensaje.agregarRazonBloqueo("Contiene correo electrónico");
            return true;
        }

        return false;
    }
}

