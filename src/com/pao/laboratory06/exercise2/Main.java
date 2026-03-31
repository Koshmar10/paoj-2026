package com.pao.laboratory06.exercise2;

import java.util.*;

public class Main {
  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    Scanner in = new Scanner(System.in);
    int count = in.nextInt();
    List<Colaborator> colaborators = new ArrayList<>();
    in.nextLine();

    for (int i = 0; i < count; i++) {
      String line = in.nextLine();
      String[] parts = line.split(" ");
      String tip = parts[0];
      String nume = parts[1];
      String prenume = parts[2];
      float venitLunar = Float.parseFloat(parts[3]);

      switch (tip) {
        case "CIM" -> {
          boolean bonus = parts.length > 4 && parts[4].equals("DA");
          colaborators.add(new CIMColaborator(nume, prenume, venitLunar, bonus));
        }
        case "PFA" -> colaborators.add(new PFAColaborator(nume, prenume, venitLunar, Float.parseFloat(parts[4])));
        case "SRL" -> colaborators.add(new SRLColaborator(nume, prenume, venitLunar, Float.parseFloat(parts[4])));
        default -> throw new AssertionError();
      }
    }

    // Sortează și afișează pe tip, fiecare descrescător după venit net anual
    for (TipColaborator tipColab : TipColaborator.values()) {
      colaborators.stream()
          .filter(c -> c.getTip() == tipColab)
          .sorted((a, b) -> Double.compare(b.calculeazaVenitNetAnual(), a.calculeazaVenitNetAnual()))
          .forEach(Colaborator::afiseaza);
    }

    // Colaborator cu venit net maxim
    Colaborator max = colaborators.stream()
        .max(Comparator.comparingDouble(Colaborator::calculeazaVenitNetAnual))
        .orElse(null);
    System.out.printf("%nColaborator cu venit net maxim: ");
    if (max != null) max.afiseaza();

    // Colaboratori persoane juridice
    System.out.println("\nColaboratori persoane juridice:");
    colaborators.stream()
        .filter(c -> c instanceof PersoanaJuridica)
        .sorted((a, b) -> Double.compare(b.calculeazaVenitNetAnual(), a.calculeazaVenitNetAnual()))
        .forEach(Colaborator::afiseaza);

    // Sume și număr pe tip
    System.out.println("\nSume și număr colaboratori pe tip:");
    Map<TipColaborator, Double> suma = new EnumMap<>(TipColaborator.class);
    Map<TipColaborator, Integer> numar = new EnumMap<>(TipColaborator.class);
    for (Colaborator c : colaborators) {
      TipColaborator t = c.getTip();
      suma.merge(t, c.calculeazaVenitNetAnual(), Double::sum);
      numar.merge(t, 1, Integer::sum);
    }
    for (TipColaborator t : TipColaborator.values()) {
      Double s = suma.get(t);
      Integer n = numar.get(t);
      if (s != null) {
        System.out.printf("%s: suma = %.2f lei, număr = %d%n", t, s, n);
      } else {
        System.out.printf("%s: suma = nu lei, număr = null%n", t);
      }
    }
  }
}
