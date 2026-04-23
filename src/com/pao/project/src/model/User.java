package model;

import java.util.Objects;

public abstract class User {
  private Integer id;
  private String username;
  private String passwordHash;

  public User(Integer id, String username, String passwordHash) {
    this.id = id;
    this.username = username;
    this.passwordHash = passwordHash;
  }

  public abstract String getRole();

  public Integer getId() { return id; }
  public String getUsername() { return username; }
  public String getPasswordHash() { return passwordHash; }

  public void setId(Integer id) { this.id = id; }
  public void setUsername(String username) { this.username = username; }
  public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

  @Override
  public String toString() {
    return String.format("User[id=%d, username=%s, role=%s]", id, username, getRole());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    return Objects.equals(id, ((User) o).id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
