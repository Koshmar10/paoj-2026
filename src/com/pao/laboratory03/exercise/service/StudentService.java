package com.pao.laboratory03.exercise.service;

import com.pao.laboratory03.exercise.exception.StudentNotFoundException;
import com.pao.laboratory03.exercise.model.Student;
import com.pao.laboratory03.exercise.model.Subject;

import java.util.*;
import java.util.stream.Collectors;

public class StudentService {
  private final List<Student> students = new ArrayList<>();
  private static StudentService instance;

  private StudentService() {
  }

  public static StudentService getInstance() {
    if (instance == null) {
      instance = new StudentService();
    }
    return instance;
  }

  public void addStudent(String name, int age) {
    if (students.stream().anyMatch(s -> s.getName().equals(name))) {
      throw new RuntimeException("Studentul cu numele '" + name + "' există deja.");
    }
    students.add(new Student(name, age));
  }

  public Student findByName(String name) {
    return students.stream()
        .filter(s -> s.getName().equals(name))
        .findFirst()
        .orElseThrow(() -> new StudentNotFoundException("Studentul '" + name + "' nu a fost găsit."));
  }

  public void addGrade(String studentName, Subject subject, double grade) {
    Student student = findByName(studentName);
    student.addGrade(subject, grade);
  }

  public void printAllStudents() {
    int index = 1;
    for (Student student : students) {
      System.out.println(index++ + ". " + student);
      student.printGrades();
    }
  }

  public void printTopStudents() {
    System.out.println("=== Top studenți ===");
    students.stream()
        .sorted(Comparator.comparingDouble(Student::getAverage).reversed())
        .forEach(student -> System.out
            .println(student.getName() + " — media: " + String.format("%.2f", student.getAverage())));
  }

  public Map<Subject, Double> getAveragePerSubject() {
    Map<Subject, List<Double>> subjectGrades = new HashMap<>();
    for (Student student : students) {
      for (Map.Entry<Subject, Double> entry : student.getGrades().entrySet()) {
        subjectGrades.computeIfAbsent(entry.getKey(), k -> new ArrayList<>()).add(entry.getValue());
      }
    }
    return subjectGrades.entrySet().stream()
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            e -> e.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0)));
  }
}