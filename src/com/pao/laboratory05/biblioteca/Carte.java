package com.pao.laboratory05.biblioteca;

public class Carte implements Comparable<Carte> {
  private String titlu;
  private String autor;
  private int an;
  private double rating;

  public Carte(String titlu, String autor, int an, double rating) {
    this.titlu = titlu;
    this.autor = autor;
    this.an = an;
    this.rating = rating;
  }

  @Override
  public int compareTo(Carte other) {
    return Double.compare(other.rating, this.rating); 
  }

  public String getTitlu() {
    return titlu;
  }

  public void setTitlu(String titlu) {
    this.titlu = titlu;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public int getAn() {
    return an;
  }

  public void setAn(int an) {
    this.an = an;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

}
