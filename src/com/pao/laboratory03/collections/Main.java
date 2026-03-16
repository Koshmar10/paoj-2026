package com.pao.laboratory03.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
  public static void main(String[] args) {

    System.out.println("=== PARTEA A: HashMap — frecvența cuvintelor ===");
    String[] words = { "java", "python", "java", "c++", "python", "java", "rust", "c++", "go" };
    HashMap<String, Integer> frequency = new HashMap<>();
    for (String word : words) {
      frequency.put(word, frequency.getOrDefault(word, 0) + 1);
    }

    System.out.println("Frecvență: " + frequency);

    System.out.println("Conține 'rust'? " + frequency.containsKey("rust"));

    System.out.println("Chei: " + frequency.keySet());
    System.out.println("Valori: " + frequency.values());

    for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
      System.out.println(entry.getKey() + " -> " + entry.getValue());
    }

    System.out.println("\n=== PARTEA B: TreeMap — sortare automată ===");
    TreeMap<String, Integer> tree = new TreeMap<>(frequency);
    System.out.println("Sortat: " + tree);
    System.out.println("Prima cheie: " + tree.firstKey());
    System.out.println("Ultima cheie: " + tree.lastKey());

    System.out.println("\n=== PARTEA C: Map cu obiecte ===");
    HashMap<String, List<String>> materii = new HashMap<>();
    materii.put("PAOJ", new ArrayList<>(Arrays.asList("Ana", "Mihai", "Ion")));
    materii.put("BD", new ArrayList<>(Arrays.asList("Ana", "Elena")));

    System.out.println("Studenți la PAOJ: " + materii.getOrDefault("PAOJ", Collections.emptyList()));
    materii.get("BD").add("George");
    System.out.println("Studenți la BD (actualizat): " + materii.getOrDefault("BD", Collections.emptyList()));
  }
}
