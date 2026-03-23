package com.pao.laboratory05.angajati;

public class Departament {
  private String nume;
  private String locatie;

  public Departament(String nume, String locatie) {
    this.nume = nume;
    this.locatie = locatie;
  }

  public String nume() {
    return nume;
  }

  public String locatie() {
    return locatie;
  }

  @Override
  public String toString() {
    return "Departament[nume=" + nume + ", locatie=" + locatie + "]";
  }
}