package model;

import java.util.Objects;

public class Transaction {
  private final String date;
  private final Float amount;
  private final Integer transactionId;
  private final TransactionType transactionType;

  public Transaction(Float amount, Integer transactionId, TransactionType transactionType) {
    this.amount = amount;
    this.transactionId = transactionId;
    this.transactionType = transactionType;
    this.date = java.time.LocalDate.now().toString();
  }

  public String getDate() { return date; }
  public Float getAmount() { return amount; }
  public Integer getTransactionId() { return transactionId; }
  public TransactionType getTransactionType() { return transactionType; }

  public void printTransactionInformation() {
    System.out.printf("  %-12s | %-12s | %10.2f RON%n", date, transactionType, amount);
  }

  @Override
  public String toString() {
    return String.format("Transaction[%s | %s | %.2f RON]", date, transactionType, amount);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Transaction)) return false;
    Transaction t = (Transaction) o;
    return Objects.equals(transactionId, t.transactionId)
        && Float.compare(amount, t.amount) == 0
        && transactionType == t.transactionType
        && Objects.equals(date, t.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId, amount, transactionType, date);
  }
}
