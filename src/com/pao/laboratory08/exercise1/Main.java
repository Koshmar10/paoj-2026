package com.pao.laboratory08.exercise1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";

    public static void main(String[] args) throws Exception {
        List<Student> studenti = citesteStudenti();

        Scanner sc = new Scanner(System.in);
        String linie = sc.nextLine().trim();

        if (linie.equals("PRINT")) {
            for (Student s : studenti) System.out.println(s);
        } else if (linie.startsWith("SHALLOW ")) {
            String nume = linie.substring(8);
            Student original = gaseste(studenti, nume);
            Student clona = original.shallowClone();
            clona.getAdresa().setOras("MODIFICAT");
            System.out.println("Original: " + original);
            System.out.println("Clona: " + clona);
        } else if (linie.startsWith("DEEP ")) {
            String nume = linie.substring(5);
            Student original = gaseste(studenti, nume);
            Student clona = original.deepClone();
            clona.getAdresa().setOras("MODIFICAT");
            System.out.println("Original: " + original);
            System.out.println("Clona: " + clona);
        }
    }

    private static List<Student> citesteStudenti() throws IOException {
        List<Student> studenti = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(FILE_PATH), StandardCharsets.UTF_8))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                if (linie.isBlank()) continue;
                String[] p = linie.split(",");
                studenti.add(new Student(p[0].trim(), Integer.parseInt(p[1].trim()),
                        new Adresa(p[2].trim(), p[3].trim())));
            }
        }
        return studenti;
    }

    private static Student gaseste(List<Student> studenti, String nume) {
        return studenti.stream().filter(s -> s.getNume().equals(nume)).findFirst().orElseThrow();
    }
}
