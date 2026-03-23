package com.pao.laboratory05.biblioteca;

import java.util.Arrays;
import java.util.Comparator;

public class BibliotecaService {
  private static BibliotecaService instance;
  private Carte[] carti = new Carte[0];

  private BibliotecaService() {
  }

  public static BibliotecaService getInstance() {
    if (instance == null) {
      instance = new BibliotecaService();
    }
    return instance;
  }

  public void addCarte(Carte carte) {
    Carte[] tmp = new Carte[carti.length + 1];
    System.arraycopy(carti, 0, tmp, 0, carti.length);
    tmp[tmp.length - 1] = carte;
    carti = tmp;
    System.out.println("Cartea \"" + carte.getTitlu() + "\" a fost adăugată");
  }

  public void listSortedByRating() {
    Carte[] sorted = carti.clone();
    Arrays.sort(sorted);
    for (Carte carte : sorted) {
      System.out.println(carte.getTitlu() + " (rating: " + carte.getRating() + ")");
    }
  }

  public void listSortedBy(Comparator<Carte> comparator) {
    Carte[] sorted = carti.clone();
    Arrays.sort(sorted, comparator);
    for (Carte carte : sorted) {
      System.out.println(carte.getTitlu());
    }
  }
}
