package exception;

public class AccountNotFoundException extends Exception {
  private final String iban;

  public AccountNotFoundException(String iban) {
    super("Contul nu a fost gasit: " + iban);
    this.iban = iban;
  }

  public String getIban() { return iban; }
}
