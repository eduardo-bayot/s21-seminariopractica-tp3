package com.moderacion.modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que representa un mensaje enviado en el sistema de chat.
 * Contiene información sobre el contenido del mensaje, su estado actual,
 * la razón del bloqueo (si es aplicable) y el identificador del usuario que lo envió.
 */
public class Mensaje {
    
    private int id;                // Identificador único del mensaje
    private String contenido;       // Contenido textual del mensaje
    private EstadoMensaje estado;   // Estado actual del mensaje
    private int usuarioId;          // Identificador del usuario que envió el mensaje
    private Date timestamp;         // Fecha y hora de envío del mensaje
    private List<String> razonesBloqueo;  // Lista de razones de bloqueo

    /**
     * Enum que representa los posibles estados de un mensaje.
     */
    public enum EstadoMensaje {
        NUEVO, EN_MODERACION, BLOQUEADO, APROBADO, SOSPECHOSO
    }
    
    /**
     * Constructor para crear una instancia de Mensaje sin razones de bloqueo.
     *
     * @param id Identificador único del mensaje
     * @param contenido Contenido del mensaje
     * @param estado Estado actual del mensaje (por ejemplo, nuevo, en moderación, bloqueado, etc.)
     * @param usuarioId Identificador del usuario que envió el mensaje
     */
    public Mensaje(int id, String contenido, EstadoMensaje estado, int usuarioId) {
        this.id = id;
        this.contenido = contenido;
        this.estado = estado;
        this.usuarioId = usuarioId;
        this.timestamp = new Date(); // Asigna la fecha y hora actuales por defecto
        this.razonesBloqueo = new ArrayList<>(); // Inicializa la lista de razones de bloqueo como vacía
    }

    /**
     * Constructor para crear una instancia de Mensaje con razones de bloqueo.
     *
     * @param id Identificador único del mensaje
     * @param contenido Contenido del mensaje
     * @param estado Estado actual del mensaje
     * @param usuarioId Identificador del usuario que envió el mensaje
     * @param razonesBloqueo Lista de razones de bloqueo
     */
    public Mensaje(int id, String contenido, EstadoMensaje estado, int usuarioId, List<String> razonesBloqueo) {
        this.id = id;
        this.contenido = contenido;
        this.estado = estado;
        this.usuarioId = usuarioId;
        this.timestamp = new Date(); // Asigna la fecha y hora actuales por defecto
        this.razonesBloqueo = razonesBloqueo != null ? razonesBloqueo : new ArrayList<>();
    }

    // Métodos getter y setter para acceder y modificar los atributos del mensaje

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public EstadoMensaje getEstado() {
        return estado;
    }

    public void setEstado(EstadoMensaje estado) {
        this.estado = estado;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getRazonesBloqueo() {
        return razonesBloqueo;
    }

    public void agregarRazonBloqueo(String razon) {
        this.razonesBloqueo.add(razon);
    }
}
