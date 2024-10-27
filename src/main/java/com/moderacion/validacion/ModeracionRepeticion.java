package com.moderacion.validacion;

import com.moderacion.core.IModeracion;
import com.moderacion.dao.MensajeDAO;
import com.moderacion.modelo.Mensaje;

import java.util.Calendar;
import java.util.Date;

/**
 * Clase de moderación que verifica si el mensaje actual es una repetición de mensajes anteriores del mismo usuario
 * dentro de una ventana de tiempo.
 */
public class ModeracionRepeticion implements IModeracion {

    private static final int MINUTOS_VENTANA = 5;  // Ventana de tiempo de 5 minutos
    private MensajeDAO mensajeDAO;

    public ModeracionRepeticion(MensajeDAO mensajeDAO) {
        this.mensajeDAO = mensajeDAO;
    }

    @Override
    public boolean validar(Mensaje mensaje) {
        int hashActual = mensaje.getContenido().hashCode();

        // Definir la ventana de tiempo
        Calendar cal = Calendar.getInstance();
        cal.setTime(mensaje.getTimestamp());
        cal.add(Calendar.MINUTE, -MINUTOS_VENTANA);
        Date tiempoInicio = cal.getTime();
        Date tiempoFin = mensaje.getTimestamp();

        // Contar mensajes repetidos en la ventana de tiempo
        int conteo = mensajeDAO.contarMensajesConHashYVentana(mensaje.getUsuarioId(), hashActual, tiempoInicio, tiempoFin);

        // Bloquear si hay mensajes repetidos en la ventana de tiempo
        if (conteo > 1) {
            mensaje.setEstado(Mensaje.EstadoMensaje.BLOQUEADO);
            mensaje.agregarRazonBloqueo("Mensaje repetido en ventana de tiempo");
            return true;
        }

        return false;
    }
}
