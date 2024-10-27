package com.moderacion.validacion;

import com.moderacion.dao.MockCombinacionSospechosaDAO;
import com.moderacion.modelo.Mensaje;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModeracionCombinacionesSospechosasTest {

    @Test
    public void testValidarCombinacionSospechosa() {
        ModeracionCombinacionesSospechosas moderacion = new ModeracionCombinacionesSospechosas(new MockCombinacionSospechosaDAO());
        Mensaje mensaje = new Mensaje(1, "Quiero enviar dinero", Mensaje.EstadoMensaje.NUEVO, 123);

        assertTrue(moderacion.validar(mensaje));
        assertEquals(Mensaje.EstadoMensaje.BLOQUEADO, mensaje.getEstado());
        assertEquals("Contiene combinación sospechosa: enviar dinero", mensaje.getRazonesBloqueo().get(mensaje.getRazonesBloqueo().size() - 1));
    }

    @Test
    public void testValidarSinCombinacionSospechosa() {
        ModeracionCombinacionesSospechosas moderacion = new ModeracionCombinacionesSospechosas(new MockCombinacionSospechosaDAO());
        Mensaje mensaje = new Mensaje(2, "Hola como estas", Mensaje.EstadoMensaje.NUEVO, 456);

        assertFalse(moderacion.validar(mensaje));
        assertEquals(Mensaje.EstadoMensaje.NUEVO, mensaje.getEstado());
        assertTrue(mensaje.getRazonesBloqueo() == null || mensaje.getRazonesBloqueo().isEmpty());
    }
}

