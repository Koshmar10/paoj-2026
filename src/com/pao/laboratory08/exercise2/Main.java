package com.pao.laboratory08.exercise2;

import com.pao.laboratory08.exercise1.Adresa;
import com.pao.laboratory08.exercise1.Student;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";

    public static void main(String[] args) throws Exception {
        List<Student> studenti = citesteStudenti();

        Scanner sc = new Scanner(System.in);
        int prag = Integer.parseInt(sc.nextLine().trim());

        List<Student> filtrati = studenti.stream()
                .filter(s -> s.getVarsta() >= prag)
                .toList();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("rezultate.txt"))) {
            for (Student s : filtrati) {
                bw.write(s.toString());
                bw.newLine();
            }
        }

        System.out.println("Filtru: varsta >= " + prag);
        System.out.println("Rezultate: " + filtrati.size() + " studenti");
        System.out.println();
        for (Student s : filtrati) System.out.println(s);
        System.out.println();
        System.out.println("Scris in: rezultate.txt");
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
}
