package com.moderacion.validacion;

import com.moderacion.core.IModeracion;
import com.moderacion.dao.MensajeDAO;
import com.moderacion.modelo.Mensaje;

import java.util.Date;

/**
 * Clase de moderación que verifica la velocidad de envío de mensajes.
 * Bloquea los mensajes enviados en un tiempo menor a un segundo después del mensaje anterior del mismo usuario.
 */
public class ModeracionVelocidad implements IModeracion {

    private static final int TIEMPO_MINIMO_MS = 1000; // 1 segundo en milisegundos
    private MensajeDAO mensajeDAO;

    public ModeracionVelocidad(MensajeDAO mensajeDAO) {
        this.mensajeDAO = mensajeDAO;
    }

    @Override
    public boolean validar(Mensaje mensaje) {
        // Obtener el último mensaje del usuario
        Mensaje ultimoMensaje = mensajeDAO.obtenerUltimoMensajeDelUsuario(mensaje.getUsuarioId());

        if (ultimoMensaje != null) {
            // Calcular la diferencia de tiempo en milisegundos
            long diferenciaTiempo = mensaje.getTimestamp().getTime() - ultimoMensaje.getTimestamp().getTime();

            // Bloquear si el mensaje fue enviado demasiado rápido
            if (diferenciaTiempo < TIEMPO_MINIMO_MS) {
                mensaje.setEstado(Mensaje.EstadoMensaje.BLOQUEADO);
                mensaje.setRazonBloqueo("Mensaje enviado demasiado rápido, posible bot");
                return true;
            }
        }

        return false;
    }
}

