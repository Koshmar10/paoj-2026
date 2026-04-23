package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.User;

public class AuthService {
  private static AuthService instance;
  private final Map<String, User> usersByUsername = new HashMap<>();

  private AuthService() {}

  public static AuthService getInstance() {
    if (instance == null) {
      instance = new AuthService();
    }
    return instance;
  }

  public void addUser(User user) {
    usersByUsername.put(user.getUsername(), user);
  }

  public void removeUser(String username) {
    usersByUsername.remove(username);
  }

  public User findByUsername(String username) {
    return usersByUsername.get(username);
  }

  public List<User> listAll() {
    return new ArrayList<>(usersByUsername.values());
  }

  public User authenticate(String username, String password) {
    User user = findByUsername(username);
    if (user != null && password.equals(user.getPasswordHash())) {
      return user;
    }
    return null;
  }
}
