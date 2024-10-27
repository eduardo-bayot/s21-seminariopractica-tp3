package com.moderacion.validacion;

import com.moderacion.core.IModeracion;
import com.moderacion.dao.CombinacionSospechosaDAO;
import com.moderacion.modelo.CombinacionSospechosa;
import com.moderacion.modelo.Mensaje;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ModeracionCombinacionesSospechosas implements IModeracion {

  private CombinacionSospechosaDAO combinacionSospechosaDAO;
  private static final Set<String> PALABRAS_IGNORADAS = Set.of("de", "el", "la", "y", "a", "con");

  public ModeracionCombinacionesSospechosas(CombinacionSospechosaDAO combinacionSospechosaDAO) {
    this.combinacionSospechosaDAO = combinacionSospechosaDAO;
  }
  @Override
  public boolean validar(Mensaje mensaje) {
    List<String> palabrasMensaje = Arrays.stream(mensaje.getContenido().toLowerCase().split("\\s+"))
      .filter(palabra -> !PALABRAS_IGNORADAS.contains(palabra))
      .collect(Collectors.toList());

    Set<CombinacionSospechosa> combinaciones = combinacionSospechosaDAO.obtenerCombinacionesSospechosas();

    for (int i = 0; i < palabrasMensaje.size() - 1; i++) {
      CombinacionSospechosa combinacion = new CombinacionSospechosa(palabrasMensaje.get(i), palabrasMensaje.get(i + 1));

      // Verifica si la combinación actual es una combinación sospechosa
      if (combinaciones.contains(combinacion)) {
        mensaje.setEstado(Mensaje.EstadoMensaje.BLOQUEADO);
        mensaje.setRazonBloqueo("Contiene combinación sospechosa: " + combinacion);
        return true;
      }
    }
    return false;
  }

}

