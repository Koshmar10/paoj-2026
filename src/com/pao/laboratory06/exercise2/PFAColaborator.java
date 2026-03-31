package com.pao.laboratory06.exercise2;

public class PFAColaborator extends Colaborator implements PersoanaFizica {
  private float cheltuieliLunare;

  private static final double SALARIU_MINIM_ANUAL = 4050 * 12.0; // 48600 lei/an

  public PFAColaborator(String nume, String prenume, float venitLunar, float cheltuieliLunare) {
    super(nume, prenume, venitLunar);
    this.cheltuieliLunare = cheltuieliLunare;
  }

  @Override
  public double calculeazaVenitLunar() {
    return calculeazaVenitNetAnual() / 12.0;
  }

  @Override
  public double calculeazaVenitNetAnual() {
    double venitNet = (venitLunar - cheltuieliLunare) * 12;

    double impozit = 0.10 * venitNet;

    double cass;
    if (venitNet < 6 * SALARIU_MINIM_ANUAL) {
      cass = 0.10 * (6 * SALARIU_MINIM_ANUAL);
    } else if (venitNet <= 72 * SALARIU_MINIM_ANUAL) {
      cass = 0.10 * venitNet;
    } else {
      cass = 0.10 * (72 * SALARIU_MINIM_ANUAL);
    }

    double cas;
    if (venitNet < 12 * SALARIU_MINIM_ANUAL) {
      cas = 0;
    } else if (venitNet <= 24 * SALARIU_MINIM_ANUAL) {
      cas = 0.25 * (12 * SALARIU_MINIM_ANUAL);
    } else {
      cas = 0.25 * (24 * SALARIU_MINIM_ANUAL);
    }

    return venitNet - impozit - cass - cas;
  }

  @Override
  public TipColaborator getTip() {
    return TipColaborator.PFA;
  }
}
