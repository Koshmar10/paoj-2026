package model;

import java.util.List;

public class Client extends User {
  private List<Account> accounts;

  public Client(Integer id, String username, String passwordHash, List<Account> accounts) {
    super(id, username, passwordHash);
    this.accounts = accounts;
  }

  @Override
  public String getRole() { return "CLIENT"; }

  public List<Account> getAccounts() { return accounts; }

  public void setAccounts(List<Account> accounts) { this.accounts = accounts; }

  public void printAccountsInformation() {
    for (Account account : accounts) {
      account.printAccountInformation();
    }
  }

  public void printTransactionHistory() {
    for (Account account : accounts) {
      for (Transaction trans : account.getTransactions()) {
        trans.printTransactionInformation();
      }
    }
  }

  @Override
  public String toString() {
    return String.format("Client[id=%d, username=%s, conturi=%d]",
        getId(), getUsername(), accounts.size());
  }
}
