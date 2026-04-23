package model;

import java.util.List;

public class CorporateClient extends Client {
  private String companyName;
  private String cui;

  public CorporateClient(Integer id, String username, String passwordHash,
      List<Account> accounts, String companyName, String cui) {
    super(id, username, passwordHash, accounts);
    this.companyName = companyName;
    this.cui = cui;
  }

  public String getCompanyName() { return companyName; }
  public void setCompanyName(String companyName) { this.companyName = companyName; }

  public String getCui() { return cui; }
  public void setCui(String cui) { this.cui = cui; }

  @Override
  public String getRole() { return "CORPORATE_CLIENT"; }

  @Override
  public String toString() {
    return String.format("CorporateClient[id=%d, username=%s, company=%s, cui=%s, conturi=%d]",
        getId(), getUsername(), companyName, cui, getAccounts().size());
  }
}
