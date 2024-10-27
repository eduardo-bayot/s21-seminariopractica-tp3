package com.moderacion.validacion;

import com.moderacion.modelo.Mensaje;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba unitaria para la clase ModeracionPuntuacion.
 */
public class ModeracionPuntuacionTest {

    @Test
    public void testValidarExcesoPuntuacion() {
        ModeracionPuntuacion moderacion = new ModeracionPuntuacion();
        Mensaje mensaje = new Mensaje(1, "!!!!!!!!!!! Esto es un mensaje con exceso de puntuación ???!!!!!!!!!!!!!!", Mensaje.EstadoMensaje.NUEVO, 5);
        
        boolean resultado = moderacion.validar(mensaje);
        
        assertTrue(resultado, "El mensaje debe ser bloqueado por exceso de puntuación");
        assertEquals(Mensaje.EstadoMensaje.BLOQUEADO, mensaje.getEstado());
        assertEquals("Exceso de puntuación", mensaje.getRazonBloqueo());
    }
    
    @Test
    public void testValidarSinExcesoPuntuacion() {
        ModeracionPuntuacion moderacion = new ModeracionPuntuacion();
        Mensaje mensaje = new Mensaje(2, "Este mensaje tiene puntuación normal.", Mensaje.EstadoMensaje.NUEVO, 6);
        
        boolean resultado = moderacion.validar(mensaje);
        
        assertFalse(resultado, "El mensaje no debe ser bloqueado");
        assertEquals(Mensaje.EstadoMensaje.NUEVO, mensaje.getEstado());
        assertNull(mensaje.getRazonBloqueo());
    }
}

