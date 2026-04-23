package model;

public class Employee extends User {

  public Employee(Integer id, String username, String passwordHash) {
    super(id, username, passwordHash);
  }

  @Override
  public String getRole() { return "EMPLOYEE"; }

  @Override
  public String toString() {
    return String.format("Employee[id=%d, username=%s]", getId(), getUsername());
  }
}
