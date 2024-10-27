package com.moderacion.dao;

import com.moderacion.modelo.Mensaje;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * Implementación en memoria de MensajeDAO.
 * Los mensajes se almacenan en un Map, simulando una base de datos.
 */
public class InMemoryMensajeDAO implements MensajeDAO {

    private static InMemoryMensajeDAO instancia;
    private Map<Integer, Mensaje> mensajes;
    private static int proximoID = 1;

    private InMemoryMensajeDAO() {
        this.mensajes = new HashMap<>();
    }

    public static synchronized InMemoryMensajeDAO instancia() {
        if (instancia == null) {
            instancia = new InMemoryMensajeDAO();
        }
        return instancia;
    }

    public static int generarID() {
        return proximoID++;
    }

    @Override
    public Mensaje obtenerMensaje(int id) {
        return this.mensajes.get(id);
    }

    @Override
    public void agregarMensaje(String contenido, int usuarioId) {
        Mensaje mensaje = new Mensaje(InMemoryMensajeDAO.generarID(), contenido, Mensaje.EstadoMensaje.NUEVO, usuarioId);
        this.mensajes.put(mensaje.getId(), mensaje);
    }

    @Override
    public void actualizarMensaje(int id, String nuevoContenido, Mensaje.EstadoMensaje nuevoEstado, String razonBloqueo) {
        Mensaje mensaje = this.obtenerMensaje(id);
        if (mensaje != null) {
            mensaje.setContenido(nuevoContenido);
            mensaje.setEstado(nuevoEstado);
            mensaje.agregarRazonBloqueo(razonBloqueo);
            this.mensajes.put(mensaje.getId(), mensaje);
        }
    }

    @Override
    public void eliminarMensaje(int id) {
        this.mensajes.remove(id);
    }

    @Override
    public List<Mensaje> listarMensajes() {
        return List.copyOf(this.mensajes.values());
    }

    @Override
    public List<Mensaje> obtenerMensajesRecientes(int usuarioId) {
        List<Mensaje> recientes = new ArrayList<>();
        for (Mensaje mensaje : this.listarMensajes()) {
            if (mensaje.getUsuarioId() == usuarioId) {
                recientes.add(mensaje);
            }
        }
        return recientes;
    }

    @Override
    public Mensaje obtenerUltimoMensajeDelUsuario(int usuarioId) {
        return this.obtenerMensajesRecientes(usuarioId).stream()
                .max((m1, m2) -> m1.getTimestamp().compareTo(m2.getTimestamp()))
                .orElse(null);
    }
    
    @Override
    public int contarMensajesConHashYVentana(int usuarioId, int hash, Date tiempoInicio, Date tiempoFin) {
        return (int) mensajes.values().stream()
                .filter(mensaje -> mensaje.getUsuarioId() == usuarioId)
                .filter(mensaje -> mensaje.getContenido().hashCode() == hash)
                .filter(mensaje -> !mensaje.getTimestamp().before(tiempoInicio) && !mensaje.getTimestamp().after(tiempoFin))
                .count();
    }
}
