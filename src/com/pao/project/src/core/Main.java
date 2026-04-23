package  core;
import exception.AccountNotFoundException;
import exception.InsufficientFundsException;
import java.util.ArrayList;
import java.util.HashSet;
import model.Account;
import model.AccountType;
import model.Card;
import model.CorporateClient;
import model.Employee;
import model.IndividualClient;
import model.Notification;
import model.Transaction;
import service.AccountService;
import service.AuthService;

public class Main {

  public static void main(String[] args) {
    AuthService authService = AuthService.getInstance();
    AccountService accountService = AccountService.getInstance();

    String parola = "parola123";

    IndividualClient andrei = new IndividualClient(1, "andrei.ionescu", parola, new ArrayList<>(), "1900101123456");
    CorporateClient  elena  = new CorporateClient(2, "elena.mihai",    parola, new ArrayList<>(), "TechCorp SRL", "RO12345678");
    Employee         ion    = new Employee(3, "employee1", parola);

    authService.addUser(andrei);
    authService.addUser(elena);
    authService.addUser(ion);

    Account cont1 = new Account("RO89KOSH0001", 5000f, AccountType.SAVINGS,
        new ArrayList<>(), new HashSet<>());
    Account cont2 = new Account("RO89KOSH0002", 1200f, AccountType.CHECKING,
        new ArrayList<>(), new HashSet<>());
    Account cont3 = new Account("RO89KOSH0003", 800f, AccountType.SAVINGS,
        new ArrayList<>(), new HashSet<>());

    Card card1 = new Card("4532123456789012", "123", "12/26", "ACTIVE");
    cont1.getCards().add(card1);

    accountService.addAccount(cont1);
    accountService.addAccount(cont2);
    accountService.addAccount(cont3);

    andrei.setAccounts(java.util.List.of(cont1, cont2));
    elena.setAccounts(java.util.List.of(cont3));

    separator("1. Autentificare utilizator");
    var logat = authService.authenticate("andrei.ionescu", "parola123");
    System.out.println(logat != null ? "Login reusit: " + logat : "Autentificare esuata");
    var loginGresit = authService.authenticate("andrei.ionescu", "parolaGresita");
    System.out.println("Login cu parola gresita: " + (loginGresit == null ? "REFUZAT" : "ACCEPTAT"));

    separator("2. Inregistrare client nou");
    IndividualClient clientNou = new IndividualClient(4, "cristian.badea", "abc123", new ArrayList<>(), "2001020398765");
    authService.addUser(clientNou);
    System.out.println("Client inregistrat: " + clientNou);

    separator("3. Deschidere cont bancar nou");
    Account contNou = new Account("RO89KOSH0004", 0f, AccountType.CHECKING, new ArrayList<>(), new HashSet<>());
    accountService.addAccount(contNou);
    clientNou.setAccounts(java.util.List.of(contNou));
    System.out.println("Cont deschis: " + contNou);

    separator("4. Vizualizare conturi (sortate dupa balanta)");
    accountService.listAllSorted().forEach(System.out::println);

    separator("5. Depunere numerar");
    try {
      accountService.deposit("RO89KOSH0001", 2000f);
      System.out.println("Depunere 2000 RON -> sold nou: "
          + accountService.findByIban("RO89KOSH0001").getBalance() + " RON");
    } catch (AccountNotFoundException e) {
      System.out.println("Eroare: " + e.getMessage());
    }

    // Actiunea 6: Retragere numerar
    separator("6. Retragere numerar");
    try {
      accountService.withdraw("RO89KOSH0002", 500f);
      System.out.println("Retragere 500 RON -> sold nou: "
          + accountService.findByIban("RO89KOSH0002").getBalance() + " RON");
    } catch (AccountNotFoundException | InsufficientFundsException e) {
      System.out.println("Eroare: " + e.getMessage());
    }
    // Demonstrare exceptie fonduri insuficiente
    try {
      accountService.withdraw("RO89KOSH0002", 9999f);
    } catch (InsufficientFundsException e) {
      System.out.println("Exceptie InsufficientFunds -> " + e.getMessage());
    } catch (AccountNotFoundException e) {
      System.out.println("Eroare: " + e.getMessage());
    }

    // Actiunea 7: Transfer intre conturi proprii
    separator("7. Transfer intre conturi proprii");
    try {
      accountService.transfer("RO89KOSH0001", "RO89KOSH0002", 1000f);
      System.out.println("Transfer efectuat: RO89KOSH0001 -> RO89KOSH0002 | 1000.00 RON");
      System.out.println("Sold RO89KOSH0001: " + accountService.findByIban("RO89KOSH0001").getBalance() + " RON");
      System.out.println("Sold RO89KOSH0002: " + accountService.findByIban("RO89KOSH0002").getBalance() + " RON");
    } catch (AccountNotFoundException | InsufficientFundsException e) {
      System.out.println("Eroare: " + e.getMessage());
    }

    // Actiunea 8: Vizualizare tranzactii cont
    separator("8. Tranzactii cont RO89KOSH0001");
    try {
      var tranzactii = accountService.getTransactions("RO89KOSH0001");
      tranzactii.forEach(Transaction::printTransactionInformation);
    } catch (AccountNotFoundException e) {
      System.out.println("Eroare: " + e.getMessage());
    }

    // Actiunea 9: Blocare card
    separator("9. Blocare card");
    try {
      accountService.blockCard("RO89KOSH0001", "4532123456789012");
      cont1.getCards().forEach(c ->
          System.out.println("Card " + c.getCardNumber() + " -> status: " + c.getStatus()));
    } catch (AccountNotFoundException e) {
      System.out.println("Eroare: " + e.getMessage());
    }

    // Actiunea 10: Cautare client dupa username
    separator("10. Cautare client dupa username");
    var gasit = authService.findByUsername("elena.mihai");
    System.out.println(gasit != null ? "Gasit: " + gasit : "Client negasit");
    System.out.println("Cautare inexistent: "
        + (authService.findByUsername("ghost") == null ? "NEGASIT" : "gasit"));

    separator("Notificare generata automat");
    Notification notif = new Notification(andrei.getId(), "Transfer reusit",
        "Ai trimis 1000 RON catre RO89KOSH0002");
    System.out.println(notif);
    notif.markAsRead();
    System.out.println("Dupa citire: " + notif);

  }

  static void separator(String titlu) {
    System.out.println("\n=== " + titlu + " ===");
  }
}
