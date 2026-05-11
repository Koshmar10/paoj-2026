package com.pao.laboratory10.exercise3;

import java.util.*;
import java.util.stream.*;

import com.pao.laboratory09.exercise3.Tranzactie;

public class Main {
   

    public static void main(String[] args) {
        List<Tranzactie> tranzactii = List.of(
            new Tranzactie(1,  1500.00, "2024-01-05", "CREDIT", "RO01BCRD"),
            new Tranzactie(2,   300.00, "2024-01-12", "DEBIT",  "RO02INGB"),
            new Tranzactie(3,   850.00, "2024-01-20", "DEBIT",  "RO01BCRD"),
            new Tranzactie(4,  2000.00, "2024-02-03", "CREDIT", "RO03BRDE"),
            new Tranzactie(5,   450.00, "2024-02-14", "DEBIT",  "RO02INGB"),
            new Tranzactie(6,   600.00, "2024-02-22", "CREDIT", "RO01BCRD"),
            new Tranzactie(7,  1200.00, "2024-03-01", "CREDIT", "RO03BRDE"),
            new Tranzactie(8,    75.00, "2024-03-10", "DEBIT",  "RO04RZBR"),
            new Tranzactie(9,  3400.00, "2024-03-18", "CREDIT", "RO02INGB"),
            new Tranzactie(10,  920.00, "2024-03-25", "DEBIT",  "RO04RZBR")
        );

        // 1. filter(tip == CREDIT)
        System.out.println("=== 1. Tranzactii CREDIT ===");
        tranzactii.stream()
                .filter(t -> "CREDIT".equals(t.tip))
                .forEach(System.out::println);

        // 2. mapToDouble(suma).sum()
        System.out.println("\n=== 2. Total procesat ===");
        double total = tranzactii.stream()
                .mapToDouble(t -> t.suma)
                .sum();
        System.out.printf(Locale.US, "Total procesat: %.2f RON%n", total);

        // 3. groupingBy(luna, summingDouble(suma))
        System.out.println("\n=== 3. Total per luna ===");
        tranzactii.stream()
                .collect(Collectors.groupingBy(
                        t -> t.data.substring(0, 7),
                        TreeMap::new,
                        Collectors.summingDouble(t -> t.suma)
                ))
                .forEach((luna, suma) ->
                        System.out.printf(Locale.US, "%s: %.2f RON%n", luna, suma));

        // 4. sorted(comparingDouble.reversed()).limit(3)
        System.out.println("\n=== 4. Top 3 tranzactii ===");
        System.out.println("Top 3 tranzactii:");
        tranzactii.stream()
                .sorted(Comparator.comparingDouble((Tranzactie t) -> t.suma).reversed())
                .limit(3)
                .forEach(System.out::println);

        // 5. map(contSursa).distinct().collect(toList())
        System.out.println("\n=== 5. Conturi sursa unice ===");
        List<String> conturi = tranzactii.stream()
                .map(t -> t.contSursa)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Conturi sursa unice: " + conturi);

        // 6. mapToDouble(suma).average()
        System.out.println("\n=== 6. Suma medie ===");
        double medie = tranzactii.stream()
                .mapToDouble(t -> t.suma)
                .average()
                .orElse(0.0);
        System.out.printf(Locale.US, "Suma medie: %.2f RON%n", medie);

        // 7. groupingBy(luna) cu format extras de cont
        System.out.println("\n=== 7. Extrase de cont lunare ===");
        tranzactii.stream()
                .collect(Collectors.groupingBy(
                        t -> t.data.substring(0, 7),
                        TreeMap::new,
                        Collectors.toList()
                ))
                .forEach((luna, lista) -> {
                    double totalLuna = lista.stream().mapToDouble(t -> t.suma).sum();
                    System.out.printf(Locale.US, "EXTRAS DE CONT - %s: %d tranzactii, total: %.2f RON%n",
                            luna, lista.size(), totalLuna);
                });
    }
}
