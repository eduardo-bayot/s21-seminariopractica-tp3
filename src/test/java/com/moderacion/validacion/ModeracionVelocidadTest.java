package com.moderacion.validacion;

import com.moderacion.dao.MensajeDAO;
import com.moderacion.modelo.Mensaje;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ModeracionVelocidadTest {

    private MensajeDAO mensajeDAO;
    private ModeracionVelocidad moderacion;

    @BeforeEach
    public void setUp() {
        mensajeDAO = Mockito.mock(MensajeDAO.class);
        moderacion = new ModeracionVelocidad(mensajeDAO);
    }

    @Test
    public void testValidarMensajeEnviadoDemasiadoRapido() {
        // Crear un mensaje previo para simular el último mensaje del usuario
        Mensaje mensajePrevio = new Mensaje(1, "Mensaje rápido", Mensaje.EstadoMensaje.NUEVO, 1);
        mensajePrevio.setTimestamp(new Date());

        // Configurar el mock para que devuelva el mensaje previo como último mensaje
        when(mensajeDAO.obtenerUltimoMensajeDelUsuario(mensajePrevio.getUsuarioId())).thenReturn(mensajePrevio);

        // Crear el mensaje actual dentro de un intervalo muy corto
        Mensaje mensajeActual = new Mensaje(2, "Mensaje rápido", Mensaje.EstadoMensaje.NUEVO, 1);
        mensajeActual.setTimestamp(new Date(mensajePrevio.getTimestamp().getTime() + 500)); // 0.5 segundos después

        boolean resultado = moderacion.validar(mensajeActual);

        assertTrue(resultado, "El mensaje debe ser bloqueado por haber sido enviado demasiado rápido");
        assertEquals(Mensaje.EstadoMensaje.BLOQUEADO, mensajeActual.getEstado());
        assertEquals("Mensaje enviado demasiado rápido, posible bot", mensajeActual.getRazonesBloqueo().get(mensajeActual.getRazonesBloqueo().size() - 1));
    }

    @Test
    public void testValidarMensajeEnviadoConIntervaloAceptable() {
        // Crear un mensaje previo para simular el último mensaje del usuario
        Mensaje mensajePrevio = new Mensaje(1, "Mensaje normal", Mensaje.EstadoMensaje.NUEVO, 1);
        mensajePrevio.setTimestamp(new Date());

        // Configurar el mock para que devuelva el mensaje previo como último mensaje
        when(mensajeDAO.obtenerUltimoMensajeDelUsuario(mensajePrevio.getUsuarioId())).thenReturn(mensajePrevio);

        // Crear el mensaje actual con un intervalo suficiente
        Calendar cal = Calendar.getInstance();
        cal.setTime(mensajePrevio.getTimestamp());
        cal.add(Calendar.SECOND, 2); // 2 segundos después
        Mensaje mensajeActual = new Mensaje(2, "Mensaje normal", Mensaje.EstadoMensaje.NUEVO, 1);
        mensajeActual.setTimestamp(cal.getTime());

        boolean resultado = moderacion.validar(mensajeActual);

        assertFalse(resultado, "El mensaje no debe ser bloqueado ya que fue enviado en un intervalo aceptable");
        assertEquals(Mensaje.EstadoMensaje.NUEVO, mensajeActual.getEstado());
        assertTrue(mensajeActual.getRazonesBloqueo() == null || mensajeActual.getRazonesBloqueo().isEmpty());
    }
}

