package com.pao.laboratory06.exercise2;

public class CIMColaborator extends Colaborator implements PersoanaFizica {
  private boolean bonus;

  public CIMColaborator(String nume, String prenume, float venitLunar, boolean bonus) {
    super(nume, prenume, venitLunar);
    this.bonus = bonus;
  }

  @Override
  public double calculeazaVenitLunar() {
    // salariu brut lunar * 0.55, +10% daca are bonus
    double net = venitLunar * 0.55;
    if (bonus) net *= 1.1;
    return net;
  }

  @Override
  public TipColaborator getTip() {
    return TipColaborator.CIM;
  }

  @Override
  public boolean areBonus() {
    return bonus;
  }
}
