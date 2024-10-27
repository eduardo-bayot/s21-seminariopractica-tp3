package com.moderacion.validacion;

import com.moderacion.core.IModeracion;
import com.moderacion.modelo.Mensaje;

/**
 * Clase que implementa la validación de mayúsculas en un mensaje.
 * Si el mensaje contiene un porcentaje excesivo de letras en mayúsculas,
 * se considerará inapropiado y será marcado como bloqueado.
 */
public class ModeracionMayusculas implements IModeracion {

    private static final double UMBRAL_MAYUSCULAS = 0.7; // Umbral del 70% de mayúsculas para bloquear

    /**
     * Valida si el mensaje tiene un porcentaje excesivo de letras en mayúsculas.
     *
     * @param mensaje El mensaje a validar.
     * @return true si el mensaje debe ser bloqueado, false si pasa la validación.
     */
    @Override
    public boolean validar(Mensaje mensaje) {
        String contenido = mensaje.getContenido();
        int totalLetras = contenido.length();
        int mayusculas = 0;

        for (char c : contenido.toCharArray()) {
            if (Character.isUpperCase(c)) {
                mayusculas++;
            }
        }

        // Calcular el porcentaje de mayúsculas y comparar con el umbral
        double porcentajeMayusculas = (double) mayusculas / totalLetras;
        if (porcentajeMayusculas > UMBRAL_MAYUSCULAS) {
            mensaje.setEstado(Mensaje.EstadoMensaje.BLOQUEADO);
            mensaje.agregarRazonBloqueo("Exceso de mayúsculas");
            return true;
        }

        return false;
    }
}
