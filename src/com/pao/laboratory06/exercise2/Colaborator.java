package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public abstract class Colaborator implements IOperatiiCitireScriere {
  String nume;
  String prenume;
  float venitLunar;

  public Colaborator(String nume, String prenume, float venitLunar) {
    this.nume = nume;
    this.prenume = prenume;
    this.venitLunar = venitLunar;
  }

  public abstract double calculeazaVenitLunar();
  public abstract TipColaborator getTip();

  public double calculeazaVenitNetAnual() {
    return calculeazaVenitLunar() * 12;
  }

  @Override
  public void afiseaza() {
    System.out.printf("%s: %s %s, venit net anual: %.2f lei%n", getTip(), nume, prenume, calculeazaVenitNetAnual());
  }

  @Override
  public void citeste(Scanner in) {}

  @Override
  public String tipContract() { return getTip().name(); }
}
