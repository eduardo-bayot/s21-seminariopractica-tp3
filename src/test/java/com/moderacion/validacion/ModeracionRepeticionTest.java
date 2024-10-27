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

public class ModeracionRepeticionTest {

    private MensajeDAO mensajeDAO;
    private ModeracionRepeticion moderacion;

    @BeforeEach
    public void setUp() {
        mensajeDAO = Mockito.mock(MensajeDAO.class);
        moderacion = new ModeracionRepeticion(mensajeDAO);
    }

    @Test
    public void testValidarMensajeRepetidoEnVentana() {
        // Crear un mensaje actual y definir la ventana de tiempo
        Mensaje mensajeActual = new Mensaje(1, "Mensaje repetido", Mensaje.EstadoMensaje.NUEVO, 1);
        int hashActual = mensajeActual.getContenido().hashCode();

        Calendar cal = Calendar.getInstance();
        Date tiempoFin = cal.getTime();
        cal.add(Calendar.MINUTE, -5);
        Date tiempoInicio = cal.getTime();

        // Configurar el mock para que devuelva un conteo de mensajes repetidos mayor a 1
        when(mensajeDAO.contarMensajesConHashYVentana(mensajeActual.getUsuarioId(), hashActual, tiempoInicio, tiempoFin))
            .thenReturn(2);

        boolean resultado = moderacion.validar(mensajeActual);

        assertTrue(resultado, "El mensaje debe ser bloqueado por ser repetido en la ventana de tiempo");
        assertEquals(Mensaje.EstadoMensaje.BLOQUEADO, mensajeActual.getEstado());
        assertEquals("Mensaje repetido en ventana de tiempo", mensajeActual.getRazonesBloqueo().get(mensajeActual.getRazonesBloqueo().size() - 1));
    }

    @Test
    public void testValidarMensajeFueraDeVentana() {
        // Crear un mensaje actual y definir la ventana de tiempo
        Mensaje mensajeActual = new Mensaje(2, "Mensaje único", Mensaje.EstadoMensaje.NUEVO, 1);
        int hashActual = mensajeActual.getContenido().hashCode();

        Calendar cal = Calendar.getInstance();
        Date tiempoFin = cal.getTime();
        cal.add(Calendar.MINUTE, -5);
        Date tiempoInicio = cal.getTime();

        // Configurar el mock para que devuelva un conteo de 1 (solo el mensaje actual)
        when(mensajeDAO.contarMensajesConHashYVentana(mensajeActual.getUsuarioId(), hashActual, tiempoInicio, tiempoFin))
            .thenReturn(1);

        boolean resultado = moderacion.validar(mensajeActual);

        assertFalse(resultado, "El mensaje no debe ser bloqueado porque no se considera repetido en la ventana de tiempo");
        assertEquals(Mensaje.EstadoMensaje.NUEVO, mensajeActual.getEstado());
        assertTrue(mensajeActual.getRazonesBloqueo() == null || mensajeActual.getRazonesBloqueo().isEmpty());
    }
}
