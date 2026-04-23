package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Account implements Comparable<Account> {
  private final String iban;
  private Float balance;
  private final AccountType accountType;
  private List<Transaction> transactions;
  private Set<Card> cards;

  public Account(String iban, Float balance, AccountType accountType) {
    this(iban, balance, accountType, new ArrayList<>(), new HashSet<>());
  }

  public Account(String iban, Float balance, AccountType accountType,
      List<Transaction> transactions, Set<Card> cards) {
    this.iban = iban;
    this.balance = balance;
    this.accountType = accountType;
    this.transactions = transactions;
    this.cards = cards;
  }

  public String getIban() { return iban; }
  public Float getBalance() { return balance; }
  public AccountType getAccountType() { return accountType; }
  public List<Transaction> getTransactions() { return transactions; }
  public Set<Card> getCards() { return cards; }

  public void setBalance(Float balance) { this.balance = balance; }
  public void setTransactions(List<Transaction> transactions) { this.transactions = transactions; }
  public void setCards(Set<Card> cards) { this.cards = cards; }

  public void printAccountInformation() {
    System.out.println("  ===================================");
    System.out.printf("  %-10s | %s%n", accountType, iban);
    System.out.printf("  Sold    : %.2f RON%n", balance);
    System.out.println("  ===================================");
  }

  @Override
  public int compareTo(Account other) {
    int cmp = Float.compare(this.balance, other.balance);
    return cmp != 0 ? cmp : other.iban.compareTo(this.iban);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Account)) return false;
    return Objects.equals(iban, ((Account) o).iban);
  }

  @Override
  public int hashCode() {
    return Objects.hash(iban);
  }

  @Override
  public String toString() {
    return String.format("Account[%s | %s | %.2f RON]", accountType, iban, balance);
  }
}
