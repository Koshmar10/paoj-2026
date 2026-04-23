package exception;

public class InsufficientFundsException extends Exception {
  private final String iban;
  private final float available;
  private final float requested;

  public InsufficientFundsException(String iban, float available, float requested) {
    super(String.format("Fonduri insuficiente pentru contul %s: disponibil %.2f RON, solicitat %.2f RON",
        iban, available, requested));
    this.iban = iban;
    this.available = available;
    this.requested = requested;
  }

  public String getIban() { return iban; }
  public float getAvailable() { return available; }
  public float getRequested() { return requested; }
}
