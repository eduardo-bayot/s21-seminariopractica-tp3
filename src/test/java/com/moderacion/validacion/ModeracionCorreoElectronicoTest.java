package com.moderacion.validacion;

import com.moderacion.modelo.Mensaje;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba unitaria para la clase ModeracionCorreoElectronico.
 */
public class ModeracionCorreoElectronicoTest {

    @Test
    public void testValidarConCorreo() {
        ModeracionCorreoElectronico moderacion = new ModeracionCorreoElectronico();
        Mensaje mensaje = new Mensaje(1, "Mi correo es ejemplo@dominio.com", Mensaje.EstadoMensaje.NUEVO, 1);

        boolean resultado = moderacion.validar(mensaje);

        assertTrue(resultado, "El mensaje debe ser bloqueado por contener un correo electrónico");
        assertEquals(Mensaje.EstadoMensaje.BLOQUEADO, mensaje.getEstado());
        assertEquals("Contiene correo electrónico", mensaje.getRazonBloqueo());
    }

    @Test
    public void testValidarSinCorreo() {
        ModeracionCorreoElectronico moderacion = new ModeracionCorreoElectronico();
        Mensaje mensaje = new Mensaje(2, "No contiene correo", Mensaje.EstadoMensaje.NUEVO, 2);

        boolean resultado = moderacion.validar(mensaje);

        assertFalse(resultado, "El mensaje no debe ser bloqueado");
        assertEquals(Mensaje.EstadoMensaje.NUEVO, mensaje.getEstado());
        assertNull(mensaje.getRazonBloqueo());
    }
}

