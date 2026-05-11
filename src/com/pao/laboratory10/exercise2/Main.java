package com.pao.laboratory10.exercise2;

import com.pao.laboratory10.exercise1.Tranzactie;
import com.pao.laboratory10.exercise1.TipTranzactie;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            ArrayList<Tranzactie> list = new ArrayList<>();
            int n = Integer.parseInt(in.nextLine().trim());
            for (int i = 0; i < n; i++) {
                String[] parts = in.nextLine().trim().split(" ");
                list.add(new Tranzactie(parts[0], Double.parseDouble(parts[1]), parts[2], parts[3]));
            }

            while (in.hasNextLine()) {
                String line = in.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] cmd = line.split(" ");
                switch (cmd[0]) {
                    case "UNIQUE_IDS" -> {
                        LinkedHashSet<Integer> ids = new LinkedHashSet<>();
                        for (Tranzactie t : list) ids.add(t.getId());
                        System.out.printf("IDs unice (%d): %s%n", ids.size(), ids);
                    }
                    case "MONTHLY_REPORT" -> {
                        TreeMap<String, double[]> report = new TreeMap<>();
                        for (Tranzactie t : list) {
                            String month = t.getData().substring(0, 7);
                            report.computeIfAbsent(month, k -> new double[2]);
                            double[] sums = report.get(month);
                            if (t.getTip() == TipTranzactie.CREDIT) sums[0] += t.getSuma();
                            else sums[1] += t.getSuma();
                        }
                        for (Map.Entry<String, double[]> e : report.entrySet()) {
                            System.out.printf(Locale.US, "%s: CREDIT %.2f RON, DEBIT %.2f RON%n",
                                    e.getKey(), e.getValue()[0], e.getValue()[1]);
                        }
                    }
                    case "TOP" -> {
                        int topN = Integer.parseInt(cmd[1]);
                        ArrayList<Tranzactie> copy = new ArrayList<>(list);
                        copy.sort(Comparator.comparingDouble(Tranzactie::getSuma).reversed());
                        System.out.printf("Top %d:%n", topN);
                        copy.subList(0, topN).forEach(System.out::println);
                    }
                    case "SORT_ASC" -> {
                        Collections.sort(list, Comparator.comparingDouble(Tranzactie::getSuma));
                        list.forEach(System.out::println);
                    }
                    case "SORT_DESC" -> {
                        Collections.sort(list, Comparator.comparingDouble(Tranzactie::getSuma).reversed());
                        list.forEach(System.out::println);
                    }
                    case "REVERSE" -> {
                        Collections.reverse(list);
                        list.forEach(System.out::println);
                    }
                    case "MIN_MAX" -> {
                        Tranzactie min = Collections.min(list, Comparator.comparingDouble(Tranzactie::getSuma));
                        Tranzactie max = Collections.max(list, Comparator.comparingDouble(Tranzactie::getSuma));
                        System.out.println("MIN: " + min);
                        System.out.println("MAX: " + max);
                    }
                    case "CME_DEMO" -> {
                        try {
                            for (Tranzactie t : list) list.remove(t);
                        } catch (ConcurrentModificationException e) {
                            System.out.println("ConcurrentModificationException prins: modificare in iteratie detectata.");
                        }
                    }
                }
            }
        }
    }
}
