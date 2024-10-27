package com.moderacion.validacion;

import com.moderacion.core.IModeracion;
import com.moderacion.modelo.Mensaje;

/**
 * Clase que implementa la validación de exceso de puntuación en un mensaje.
 * Si el mensaje contiene un porcentaje excesivo de signos de puntuación,
 * se considerará inapropiado y será marcado como bloqueado.
 */
public class ModeracionPuntuacion implements IModeracion {

    private static final double UMBRAL_PUNTUACION = 0.3; // Umbral del 30% de puntuación para bloquear

    /**
     * Valida si el mensaje tiene un porcentaje excesivo de signos de puntuación.
     *
     * @param mensaje El mensaje a validar.
     * @return true si el mensaje debe ser bloqueado, false si pasa la validación.
     */
    @Override
    public boolean validar(Mensaje mensaje) {
        String contenido = mensaje.getContenido();
        int totalCaracteres = contenido.length();
        int signosPuntuacion = 0;

        for (char c : contenido.toCharArray()) {
            // Contar caracteres que no sean letras ni dígitos
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                signosPuntuacion++;
            }
        }

        // Evitar división por cero
        if (totalCaracteres == 0) {
            return false;
        }

        // Calcular el porcentaje de puntuación
        double porcentajePuntuacion = (double) signosPuntuacion / totalCaracteres;
        if (porcentajePuntuacion > UMBRAL_PUNTUACION) {
            mensaje.setEstado(Mensaje.EstadoMensaje.BLOQUEADO);
            mensaje.agregarRazonBloqueo("Exceso de puntuación");
            return true;
        }

        return false;
    }
}
