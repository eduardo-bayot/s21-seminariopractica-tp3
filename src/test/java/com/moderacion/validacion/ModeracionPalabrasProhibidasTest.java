package com.moderacion.validacion;

import com.moderacion.dao.MockPalabraProhibidaDAO;
import com.moderacion.modelo.Mensaje;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModeracionPalabrasProhibidasTest {

    private MockPalabraProhibidaDAO mockDAO;
    private ModeracionPalabrasProhibidas moderacion;

    @BeforeEach
    public void setUp() {
        mockDAO = new MockPalabraProhibidaDAO();
        mockDAO.agregarPalabraProhibida("palabraprohibida1");
        moderacion = new ModeracionPalabrasProhibidas(mockDAO);
    }

    @Test
    public void testValidarConPalabraProhibida() {
        Mensaje mensaje = new Mensaje(1, "Este mensaje contiene palabraprohibida1", Mensaje.EstadoMensaje.NUEVO, 1);
        boolean resultado = moderacion.validar(mensaje);

        assertTrue(resultado, "El mensaje debe ser bloqueado por contener palabras prohibidas");
        assertEquals(Mensaje.EstadoMensaje.BLOQUEADO, mensaje.getEstado());
        assertEquals("Contiene palabra prohibida: palabraprohibida1", mensaje.getRazonBloqueo());
    }

    @Test
    public void testValidarSinPalabraProhibida() {
        Mensaje mensaje = new Mensaje(1, "Este mensaje es seguro", Mensaje.EstadoMensaje.NUEVO, 1);
        boolean resultado = moderacion.validar(mensaje);

        assertFalse(resultado, "El mensaje no debe ser bloqueado porque no contiene palabras prohibidas");
        assertEquals(Mensaje.EstadoMensaje.NUEVO, mensaje.getEstado());
        assertNull(mensaje.getRazonBloqueo());
    }
}

