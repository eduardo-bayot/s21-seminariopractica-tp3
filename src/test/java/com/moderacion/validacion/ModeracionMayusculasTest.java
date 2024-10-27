package com.moderacion.validacion;

import com.moderacion.modelo.Mensaje;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba unitaria para la clase ModeracionMayusculas.
 */
public class ModeracionMayusculasTest {

    @Test
    public void testValidarExcesoMayusculas() {
        ModeracionMayusculas moderacion = new ModeracionMayusculas();
        Mensaje mensaje = new Mensaje(1, "ESTE MENSAJE TIENE MUCHAS MAYUSCULAS", Mensaje.EstadoMensaje.NUEVO, 3);
        
        boolean resultado = moderacion.validar(mensaje);
        
        assertTrue(resultado, "El mensaje debe ser bloqueado por exceso de mayúsculas");
        assertEquals(Mensaje.EstadoMensaje.BLOQUEADO, mensaje.getEstado());
        assertEquals("Exceso de mayúsculas", mensaje.getRazonesBloqueo().get(mensaje.getRazonesBloqueo().size() - 1));
    }
    
    @Test
    public void testValidarSinExcesoMayusculas() {
        ModeracionMayusculas moderacion = new ModeracionMayusculas();
        Mensaje mensaje = new Mensaje(2, "Este mensaje tiene mayúsculas normales", Mensaje.EstadoMensaje.NUEVO, 4);
        
        boolean resultado = moderacion.validar(mensaje);
        
        assertFalse(resultado, "El mensaje no debe ser bloqueado");
        assertEquals(Mensaje.EstadoMensaje.NUEVO, mensaje.getEstado());
        assertTrue(mensaje.getRazonesBloqueo() == null || mensaje.getRazonesBloqueo().isEmpty());
    }
}

