package com.pao.laboratory05.audit;

public class AuditEntry {
  private final String action;
  private final String target;
  private final String timestamp;

  public AuditEntry(String action, String target, String timestamp) {
    this.action = action;
    this.target = target;
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "AuditEntry[action=" + action + ", target=" + target + ", timestamp=" + timestamp + "]";
  }
}