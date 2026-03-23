package com.pao.laboratory05.angajati;

import java.util.Arrays;

public class AngajatService {
  private static AngajatService single_instance = null;
  private Angajat[] angajati = new Angajat[0];

  private AngajatService() {
  }

  public static AngajatService getInstance() {
    if (single_instance == null)
      single_instance = new AngajatService();
    return single_instance;
  }

  public void addAngajat(Angajat a) {
    Angajat[] tmp = new Angajat[angajati.length + 1];
    System.arraycopy(angajati, 0, tmp, 0, angajati.length);
    tmp[tmp.length - 1] = a;
    angajati = tmp;
    System.out.println("Angajat adăugat: " + a.getNume());
  }

  public void printAll() {
    for (Angajat a : angajati) {
      System.out.println(a);
    }
  }

  public void listBySalary() {
    Angajat[] copy = angajati.clone();
    Arrays.sort(copy);
    System.out.println("--- Angajați după salariu (descrescător) ---");
    for (int i = 0; i < copy.length; i++) {
      System.out.println((i + 1) + ". " + copy[i]);
    }
  }

  public void findByDepartament(String numeDept) {
    System.out.println("--- Angajați din " + numeDept + " ---");
    boolean gasit = false;
    for (Angajat a : angajati) {
      if (a.getDepartament().nume().equalsIgnoreCase(numeDept)) {
        System.out.println(a);
        gasit = true;
      }
    }
    if (!gasit) {
      System.out.println("Niciun angajat în departamentul: " + numeDept);
    }
  }
}