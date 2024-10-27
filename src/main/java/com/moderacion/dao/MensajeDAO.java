package com.moderacion.dao;

import com.moderacion.modelo.Mensaje;
import java.util.Date;
import java.util.List;

public interface MensajeDAO {
    Mensaje obtenerMensaje(int id);
    void agregarMensaje(String contenido, int usuarioId);
    void actualizarMensaje(int id, String nuevoContenido, Mensaje.EstadoMensaje nuevoEstado, String razonBloqueo);
    void eliminarMensaje(int id);
    List<Mensaje> listarMensajes();
    List<Mensaje> obtenerMensajesRecientes(int usuarioId);
    Mensaje obtenerUltimoMensajeDelUsuario(int usuarioId);
    int contarMensajesConHashYVentana(int usuarioId, int hash, Date tiempoInicio, Date tiempoFin);
}

