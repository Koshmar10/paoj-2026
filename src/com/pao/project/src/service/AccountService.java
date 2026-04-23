package service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import exception.AccountNotFoundException;
import exception.InsufficientFundsException;
import model.Account;
import model.Card;
import model.Transaction;
import model.TransactionType;


public class AccountService {
  private static AccountService instance;
  private final Map<String, Account> accountsByIban = new HashMap<>();
  private final TreeSet<Account> sortedAccounts = new TreeSet<>();

  private AccountService() {}

  public static AccountService getInstance() {
    if (instance == null) {
      instance = new AccountService();
    }
    return instance;
  }

  public void addAccount(Account account) {
    accountsByIban.put(account.getIban(), account);
    sortedAccounts.add(account);
  }

  public void removeAccount(String iban) throws AccountNotFoundException {
    Account acc = accountsByIban.remove(iban);
    if (acc == null) throw new AccountNotFoundException(iban);
    sortedAccounts.remove(acc);
  }

  public Account findByIban(String iban) throws AccountNotFoundException {
    Account acc = accountsByIban.get(iban);
    if (acc == null) throw new AccountNotFoundException(iban);
    return acc;
  }

  public TreeSet<Account> listAllSorted() {
    return new TreeSet<>(sortedAccounts);
  }

  public void deposit(String iban, float amount) throws AccountNotFoundException {
    Account acc = findByIban(iban);
    acc.setBalance(acc.getBalance() + amount);
    acc.getTransactions().add(new Transaction(amount, null, TransactionType.DEPOSIT));
  }

  public void withdraw(String iban, float amount)
      throws AccountNotFoundException, InsufficientFundsException {
    Account acc = findByIban(iban);
    if (acc.getBalance() < amount) {
      throw new InsufficientFundsException(iban, acc.getBalance(), amount);
    }
    acc.setBalance(acc.getBalance() - amount);
    acc.getTransactions().add(new Transaction(amount, null, TransactionType.WITHDRAWAL));
  }

  public void transfer(String fromIban, String toIban, float amount)
      throws AccountNotFoundException, InsufficientFundsException {
    Account from = findByIban(fromIban);
    Account to = findByIban(toIban);
    if (from.getBalance() < amount) {
      throw new InsufficientFundsException(fromIban, from.getBalance(), amount);
    }
    from.setBalance(from.getBalance() - amount);
    from.getTransactions().add(new Transaction(amount, null, TransactionType.WITHDRAWAL));
    to.setBalance(to.getBalance() + amount);
    to.getTransactions().add(new Transaction(amount, null, TransactionType.DEPOSIT));
  }

  public List<Transaction> getTransactions(String iban) throws AccountNotFoundException {
    return findByIban(iban).getTransactions();
  }

  public void blockCard(String iban, String cardNumber) throws AccountNotFoundException {
    Account acc = findByIban(iban);
    for (Card card : acc.getCards()) {
      if (card.getCardNumber().equals(cardNumber)) {
        card.setStatus("BLOCKED");
        return;
      }
    }
  }
}
