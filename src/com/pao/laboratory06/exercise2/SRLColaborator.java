package com.pao.laboratory06.exercise2;

public class SRLColaborator extends Colaborator implements PersoanaJuridica {
  private float cheltuieliLunare;

  public SRLColaborator(String nume, String prenume, float venitLunar, float cheltuieliLunare) {
    super(nume, prenume, venitLunar);
    this.cheltuieliLunare = cheltuieliLunare;
  }

  @Override
  public double calculeazaVenitLunar() {
    // (venit - cheltuieli) * 0.84 (impozit profit 16%)
    return (venitLunar - cheltuieliLunare) * 0.84;
  }

  @Override
  public TipColaborator getTip() {
    return TipColaborator.SRL;
  }
}
