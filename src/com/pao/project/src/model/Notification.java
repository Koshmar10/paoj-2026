package model;

public class Notification {
  private final int userId;
  private final String title;
  private final String body;
  private boolean isRead;
  private final String createdDate;

  public Notification(int userId, String title, String body) {
    this.userId = userId;
    this.title = title;
    this.body = body;
    this.isRead = false;
    this.createdDate = java.time.LocalDateTime.now().toString();
  }

  public int getUserId() { return userId; }
  public String getTitle() { return title; }
  public String getBody() { return body; }
  public boolean isRead() { return isRead; }
  public String getCreatedDate() { return createdDate; }

  public void markAsRead() { this.isRead = true; }

  @Override
  public String toString() {
    return String.format("Notification[userId=%d | %s | %s | read=%b]",
        userId, title, body, isRead);
  }
}
