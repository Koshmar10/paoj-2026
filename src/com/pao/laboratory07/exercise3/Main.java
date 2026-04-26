package com.pao.laboratory07.exercise3;

import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Comanda> comenzi = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] t = sc.nextLine().trim().split(" ");
            switch (t[0]) {
                case "STANDARD"   -> comenzi.add(new ComandaStandard(t[1], Double.parseDouble(t[2]), t[3]));
                case "DISCOUNTED" -> comenzi.add(new ComandaRedusa(t[1], Double.parseDouble(t[2]), Integer.parseInt(t[3]), t[4]));
                case "GIFT"       -> comenzi.add(new ComandaGratuita(t[1], t[2]));
            }
        }

        for (Comanda c : comenzi) System.out.println(c.descriere());

        String line;
        while (!(line = sc.nextLine().trim()).equals("QUIT")) {
            if (line.equals("STATS")) {
                printStats(comenzi);
            } else if (line.startsWith("FILTER")) {
                double threshold = Double.parseDouble(line.split(" ")[1]);
                printFilter(comenzi, threshold);
            } else if (line.equals("SORT")) {
                printSort(comenzi);
            } else if (line.equals("SPECIAL")) {
                printSpecial(comenzi);
            }
        }
    }

    private static void printStats(List<Comanda> comenzi) {
        Map<String, Double> medii = comenzi.stream().collect(
            Collectors.groupingBy(
                c -> switch (c) {
                    case ComandaStandard s -> "STANDARD";
                    case ComandaRedusa r   -> "DISCOUNTED";
                    case ComandaGratuita g -> "GIFT";
                },
                Collectors.averagingDouble(Comanda::pretFinal)
            )
        );
        System.out.println("\n--- STATS ---");
        for (String tip : List.of("STANDARD", "DISCOUNTED", "GIFT")) {
            if (medii.containsKey(tip))
                System.out.printf(Locale.US, "%s: medie = %.2f lei%n", tip, medii.get(tip));
        }
    }

    private static void printFilter(List<Comanda> comenzi, double threshold) {
        System.out.printf(Locale.US, "%n--- FILTER (>= %.2f) ---%n", threshold);
        comenzi.stream()
            .filter(c -> c.pretFinal() >= threshold)
            .forEach(c -> System.out.println(c.descriereShort()));
    }

    private static void printSort(List<Comanda> comenzi) {
        System.out.println("\n--- SORT (by client, then by pret) ---");
        comenzi.stream()
            .sorted(Comparator.comparing(Comanda::getClient).thenComparingDouble(Comanda::pretFinal))
            .forEach(c -> System.out.println(c.descriereShort()));
    }

    private static void printSpecial(List<Comanda> comenzi) {
        System.out.println("\n--- SPECIAL (discount > 15%) ---");
        comenzi.stream()
            .filter(c -> c instanceof ComandaRedusa r && r.getDiscountProcent() > 15)
            .forEach(c -> System.out.println(c.descriereShort()));
    }
}
