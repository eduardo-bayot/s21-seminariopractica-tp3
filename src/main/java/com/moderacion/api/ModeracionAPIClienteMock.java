package com.moderacion.api;

import com.moderacion.modelo.Mensaje;
import com.moderacion.modelo.Mensaje.EstadoMensaje;
import java.util.Random;

public class ModeracionAPIClienteMock implements ModeracionAPICliente {
    private Random random;

    public ModeracionAPIClienteMock() {
        this.random = new Random();
    }

    @Override
    public EstadoMensaje analizarMensaje(Mensaje mensaje) {
        int resultado = random.nextInt(3);
        switch (resultado) {
            case 0:
                return EstadoMensaje.APROBADO;
            case 1:
                return EstadoMensaje.BLOQUEADO;
            default:
                return EstadoMensaje.SOSPECHOSO;
        }
    }
}

