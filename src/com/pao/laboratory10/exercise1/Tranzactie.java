package com.pao.laboratory10.exercise1;

public class Tranzactie {
  private final int id;
  private final double suma;
  private String data;
  private TipTranzactie tip;


  public Tranzactie(String id2, double suma, String data, String tip2) {
    this.id = Integer.parseInt(id2);
    this.suma = suma;
    this.data = data;
    this.tip = TipTranzactie.valueOf(tip2);
  }


  @Override
  public String toString() {
    return String.format(java.util.Locale.US, "[%d] %s %s: %.2f RON", id, data, tip, suma);
  }


  public int getId() {
    return id;
  }


  public double getSuma() {
    return suma;
  }


  public String getData() {
    return data;
  }


  public void setData(String data) {
    this.data = data;
  }


  public TipTranzactie getTip() {
    return tip;
  }


  public void setTip(TipTranzactie tip) {
    this.tip = tip;
  }
}
