package com.pao.laboratory09.exercise1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex1.ser";

    public static void main(String[] args) throws Exception {
        // 1. Citește N tranzacții din stdin
        List<Tranzactie> tranzactii = new ArrayList<>();
        try (Scanner in = new Scanner(System.in)) {
            int n = Integer.parseInt(in.nextLine().trim());
            for (int i = 0; i < n; i++) {
                String[] parts = in.nextLine().trim().split(" ");
                Tranzactie t = new Tranzactie();
                t.id             = Integer.parseInt(parts[0]);
                t.suma           = Double.parseDouble(parts[1]);
                t.data           = parts[2];
                t.contSursa      = parts[3];
                t.contDestinatie = parts[4];
                t.tip            = TipTranzactie.valueOf(parts[5]);
                tranzactii.add(t);
            }

            // 2. Setează note = "procesat" înainte de serializare
            for (Tranzactie t : tranzactii) {
                t.note = "procesat";
            }

            // 3. Serializează lista în fișier
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OUTPUT_FILE))) {
                oos.writeObject(tranzactii);
            }

            // 4. Deserializează lista din fișier
            List<Tranzactie> restaurate;
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(OUTPUT_FILE))) {
                restaurate = (List<Tranzactie>) ois.readObject();
            }

            // 5. Procesează comenzile până la EOF
            while (in.hasNextLine()) {
                String[] cmd = in.nextLine().trim().split(" ");
                switch (cmd[0]) {
                    case "LIST" -> {
                        for (Tranzactie t : restaurate) {
                            System.out.printf(Locale.US, "[%d] %s %s: %.2f RON | %s -> %s%n",
                                    t.id, t.data, t.tip, t.suma, t.contSursa, t.contDestinatie);
                        }
                    }
                    case "FILTER" -> {
                        String prefix = cmd[1];
                        List<Tranzactie> filtrate = restaurate.stream()
                                .filter(t -> t.data.startsWith(prefix))
                                .toList();
                        if (filtrate.isEmpty()) {
                            System.out.println("Niciun rezultat.");
                        } else {
                            for (Tranzactie t : filtrate) {
                                System.out.printf(Locale.US, "[%d] %s %s: %.2f RON | %s -> %s%n",
                                        t.id, t.data, t.tip, t.suma, t.contSursa, t.contDestinatie);
                            }
                        }
                    }
                    case "NOTE" -> {
                        int id = Integer.parseInt(cmd[1]);
                        restaurate.stream()
                                .filter(t -> t.id == id)
                                .findFirst()
                                .ifPresentOrElse(
                                        t -> System.out.printf("NOTE[%d]: %s%n", t.id, t.note),
                                        () -> System.out.printf("NOTE[%d]: not found%n", id)
                                );
                    }
                }
            }
        }
    }
}
