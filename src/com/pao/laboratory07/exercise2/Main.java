package com.pao.laboratory07.exercise2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Comanda> comenzi = new ArrayList<>();
        int nrStandard = 0, nrDiscounted = 0, nrGift = 0;
        double sumaStandard = 0, sumaDiscounted = 0;
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] tokens = line.split(" ");
            switch (tokens[0]) {
                case "STANDARD" -> {
                    Comanda c = new ComandaStandard(tokens[1], Double.parseDouble(tokens[2]));
                    comenzi.add(c);
                    nrStandard++;
                    sumaStandard += c.pretFinal();
                }
                case "DISCOUNTED" -> {
                    Comanda c = new ComandaRedusa(tokens[1], Double.parseDouble(tokens[2]), Integer.parseInt(tokens[3]));
                    comenzi.add(c);
                    nrDiscounted++;
                    sumaDiscounted += c.pretFinal();
                }
                case "GIFT" -> {
                    comenzi.add(new ComandaGratuita(tokens[1]));
                    nrGift++;
                }
            }
        }
        for (Comanda c : comenzi) {
            System.out.println(c.descriere());
        }
        System.out.println();
        System.out.println("Statistici:");
        if (nrStandard > 0)
            System.out.printf(Locale.US, "STANDARD: suma = %.2f lei, numar = %d\n", sumaStandard, nrStandard);
        if (nrDiscounted > 0)
            System.out.printf(Locale.US, "DISCOUNTED: suma = %.2f lei, numar = %d\n", sumaDiscounted, nrDiscounted);
        if (nrGift > 0)
            System.out.printf(Locale.US, "GIFT: suma = 0.00 lei, numar = %d\n", nrGift);
        System.out.printf(Locale.US, "Total platit: %.2f lei\n", sumaStandard + sumaDiscounted);
    }
}
