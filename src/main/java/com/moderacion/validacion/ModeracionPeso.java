package com.moderacion.validacion;

import com.moderacion.core.IModeracion;
import com.moderacion.modelo.Mensaje;
import com.moderacion.dao.PalabraPesoDAO;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Clase que calcula el peso de un mensaje.
 */
public class ModeracionPeso implements IModeracion {

    private final PalabraPesoDAO palabraPesoDAO;
    private static final double UMBRAL_BLOQUEO = 10.0; // Valor de umbral para bloquear
    private static final int PESO_POR_DEFECTO = 1;

    public ModeracionPeso(PalabraPesoDAO palabraPesoDAO) {
        this.palabraPesoDAO = palabraPesoDAO;
    }

    @Override
    public boolean validar(Mensaje mensaje) {
        List<String> palabras = Arrays.asList(mensaje.getContenido().split("\\s+"));
        double pesoTotal = 0.0;

        for (int i = 0; i < palabras.size(); i++) {
            String palabra = palabras.get(i).toLowerCase();

            // Obtener peso de la palabra o asignar un peso por defecto
            double pesoPalabra = palabraPesoDAO.obtenerPesoPalabra(palabra)
                    .map(PalabraPeso -> PalabraPeso.getPeso())
                    .orElse(PESO_POR_DEFECTO);

            double pesoContexto = 1.0;

            // Multiplicar por el peso de las siguientes dos palabras en el contexto
            for (int j = 1; j <= 2 && i + j < palabras.size(); j++) {
                String palabraContexto = palabras.get(i + j).toLowerCase();
                double pesoPalabraContexto = palabraPesoDAO.obtenerPesoPalabra(palabraContexto)
                        .map(PalabraPeso -> PalabraPeso.getPeso())
                        .orElse(PESO_POR_DEFECTO);

                pesoContexto *= pesoPalabraContexto / (double) (j + 1);
            }

            pesoTotal += pesoPalabra * pesoContexto;
        }

        return pesoTotal >= UMBRAL_BLOQUEO;
    }
}

