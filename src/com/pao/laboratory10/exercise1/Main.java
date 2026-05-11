package com.pao.laboratory10.exercise1;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      LinkedList<Tranzactie> list = new LinkedList<>();  
      try (Scanner in = new Scanner(System.in)) {
        while (in.hasNextLine()){
          String line = in.nextLine();
          String[] parts = line.split(" ");
            switch (parts[0]) {
              case "ENQUEUE" -> {
                String id = parts[1];
                double suma = Double.parseDouble(parts[2]);
                String data = parts[3];
                String tip = parts[4];
                list.addLast(new Tranzactie(id, suma, data, tip));
              }
              case "DEQUEUE" -> {
                if (list.isEmpty()) {
                  System.out.println("Coada goala.");
                } else {
                  Tranzactie t = list.removeFirst();
                  System.out.printf(java.util.Locale.US, "Procesat: [%d] %s %s: %.2f RON%n", t.getId(), t.getData(), t.getTip(), t.getSuma());
                }
              }
              case "PUSH" -> {
                String id = parts[1];
                double suma = Double.parseDouble(parts[2]);
                String data = parts[3];
                String tip = parts[4];
                list.addFirst(new Tranzactie(id, suma, data, tip));
              }
              case "POP" -> {
                if (list.isEmpty()) {
                  System.out.println("Coada goala.");
                } else {
                  Tranzactie t = list.removeFirst();
                  System.out.printf(java.util.Locale.US, "Extras: [%d] %s %s: %.2f RON%n", t.getId(), t.getData(), t.getTip(), t.getSuma());
                }
              }
              case "REMOVE_DEBIT" -> {
                int count = 0;
                var iterator = list.iterator();
                while (iterator.hasNext()) {
                  if (iterator.next().getTip() == TipTranzactie.DEBIT) {
                    iterator.remove();
                    count++;
                  }
                }
                System.out.printf("Eliminat %d tranzactii DEBIT.%n", count);
              }
              case "REMOVE_BELOW" -> {
                double threshold = Double.parseDouble(parts[1]);
                int count = 0;
                var iterator = list.iterator();
                while (iterator.hasNext()) {
                  if (iterator.next().getSuma() < threshold) {
                    iterator.remove();
                    count++;
                  }
                }
                System.out.printf(java.util.Locale.US, "Eliminat %d tranzactii sub %.2f RON.%n", count, threshold);
              }
              case "PRINT" -> list.forEach(System.out::println);
              case "SIZE" -> System.out.printf("Dimensiune coada: %d%n", list.size());
              default -> throw new AssertionError("Comanda necunoscuta: " + parts[0]);
            }
        }
        
      }
    }
}
