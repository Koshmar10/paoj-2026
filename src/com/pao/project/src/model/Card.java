package model;

public class Card {
  private final String cardNumber;
  private final String cvv;
  private final String expirationDate;
  private String status;

  public Card(String cardNumber, String cvv, String expirationDate, String status) {
    this.cardNumber = cardNumber;
    this.cvv = cvv;
    this.expirationDate = expirationDate;
    this.status = status;
  }

  public String getCardNumber() { return cardNumber; }
  public String getCvv() { return cvv; }
  public String getExpirationDate() { return expirationDate; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }

  @Override
  public String toString() {
    String masked = "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    return String.format("Card[%s | exp: %s | %s]", masked, expirationDate, status);
  }

  public void printCardInformation() {
    String masked = "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    System.out.println("  -----------------------------------");
    System.out.println("  CARD");
    System.out.println("  -----------------------------------");
    System.out.printf("  Number  : %s%n", masked);
    System.out.printf("  Expires : %s%n", expirationDate);
    System.out.printf("  Status  : %s%n", status);
    System.out.println("  -----------------------------------");
  }
}

