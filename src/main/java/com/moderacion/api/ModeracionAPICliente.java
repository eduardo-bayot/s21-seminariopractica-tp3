package com.moderacion.api;

import com.moderacion.modelo.Mensaje;
import com.moderacion.modelo.Mensaje.EstadoMensaje;

public interface ModeracionAPICliente {
    /**
     * Analiza un mensaje usando el sistema de moderación externo.
     * 
     * @param mensaje El mensaje a analizar.
     * @return Estado del mensaje tras el análisis de la API.
     */
    EstadoMensaje analizarMensaje(Mensaje mensaje);
}

