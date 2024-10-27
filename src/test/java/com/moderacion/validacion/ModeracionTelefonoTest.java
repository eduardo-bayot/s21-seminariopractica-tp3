package com.moderacion.validacion;

import com.moderacion.modelo.Mensaje;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba unitaria para la clase ModeracionTelefono.
 */
public class ModeracionTelefonoTest {

    @Test
    public void testValidarConTelefono() {
        ModeracionTelefono moderacion = new ModeracionTelefono();
        Mensaje mensaje = new Mensaje(1, "Mi teléfono es +54 9 11 1234-5678", Mensaje.EstadoMensaje.NUEVO, 7);

        boolean resultado = moderacion.validar(mensaje);

        assertTrue(resultado, "El mensaje debe ser bloqueado por contener un número de teléfono");
        assertEquals(Mensaje.EstadoMensaje.BLOQUEADO, mensaje.getEstado());
        assertEquals("Contiene número de teléfono", mensaje.getRazonesBloqueo().get(mensaje.getRazonesBloqueo().size() - 1));
    }

    @Test
    public void testValidarSinTelefono() {
        ModeracionTelefono moderacion = new ModeracionTelefono();
        Mensaje mensaje = new Mensaje(2, "No contiene teléfono", Mensaje.EstadoMensaje.NUEVO, 8);

        boolean resultado = moderacion.validar(mensaje);

        assertFalse(resultado, "El mensaje no debe ser bloqueado");
        assertEquals(Mensaje.EstadoMensaje.NUEVO, mensaje.getEstado());
        assertTrue(mensaje.getRazonesBloqueo() == null || mensaje.getRazonesBloqueo().isEmpty());
    }
}

