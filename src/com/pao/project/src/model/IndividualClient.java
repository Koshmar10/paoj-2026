package model;

import java.util.List;

public class IndividualClient extends Client {
  private String cnp;

  public IndividualClient(Integer id, String username, String passwordHash,
      List<Account> accounts, String cnp) {
    super(id, username, passwordHash, accounts);
    this.cnp = cnp;
  }

  public String getCnp() { return cnp; }
  public void setCnp(String cnp) { this.cnp = cnp; }

  @Override
  public String getRole() { return "INDIVIDUAL_CLIENT"; }

  @Override
  public String toString() {
    return String.format("IndividualClient[id=%d, username=%s, cnp=%s, conturi=%d]",
        getId(), getUsername(), cnp, getAccounts().size());
  }
}
