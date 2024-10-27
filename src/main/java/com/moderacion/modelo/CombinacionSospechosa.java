package com.moderacion.modelo;

import java.util.Objects;

/**
 * Representa una combinación sospechosa de palabras.
 */
public class CombinacionSospechosa {

  private String palabra1;
  private String palabra2;

  public CombinacionSospechosa(String palabra1, String palabra2) {
    this.palabra1 = palabra1;
    this.palabra2 = palabra2;
  }

  public String getPalabra1() {
    return palabra1;
  }

  public void setPalabra1(String palabra1) {
    this.palabra1 = palabra1;
  }

  public String getPalabra2() {
    return palabra2;
  }

  public void setPalabra2(String palabra2) {
    this.palabra2 = palabra2;
  }

  @Override
  public String toString() {
    return palabra1 + " " + palabra2;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    CombinacionSospechosa that = (CombinacionSospechosa) obj;
    return palabra1.equals(that.palabra1) && palabra2.equals(that.palabra2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(palabra1, palabra2);
  }

}

