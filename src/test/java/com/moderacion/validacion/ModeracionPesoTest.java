package com.moderacion.validacion;

import com.moderacion.modelo.Mensaje;
import com.moderacion.dao.MockPalabraPesoDAO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ModeracionPesoTest {

    @Test
    void testValidarPesoExcesivo() {
        MockPalabraPesoDAO palabraPesoDAO = new MockPalabraPesoDAO();
        ModeracionPeso moderacionPeso = new ModeracionPeso(palabraPesoDAO);

        Mensaje mensaje = new Mensaje(1, "banana de carne fiesta", Mensaje.EstadoMensaje.NUEVO, 1);
        assertTrue(moderacionPeso.validar(mensaje), "El mensaje debe ser bloqueado por peso excesivo");
    }

    @Test
    void testValidarPesoAceptable() {
        MockPalabraPesoDAO palabraPesoDAO = new MockPalabraPesoDAO();
        ModeracionPeso moderacionPeso = new ModeracionPeso(palabraPesoDAO);

        Mensaje mensaje = new Mensaje(1, "hola soy un gato azul", Mensaje.EstadoMensaje.NUEVO, 1);
        assertFalse(moderacionPeso.validar(mensaje), "El mensaje no debe ser bloqueado por peso");
    }
}

