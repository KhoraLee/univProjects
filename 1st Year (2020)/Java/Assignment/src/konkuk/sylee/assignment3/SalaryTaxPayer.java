package konkuk.sylee.assignment3;

public class SalaryTaxPayer extends TaxPayer {

  private String companyname; // 근무 회사 명

  public SalaryTaxPayer(String name, String number, String companyname, int earnings) {
    super(name, number, earnings);
    this.companyname = companyname;
  }

  @Override
  public String toString() {
    return super.toString();
  }

  public String getCompanyname() {
    return companyname;
  }

  public void setCompanyname(String companyname) {
    this.companyname = companyname;
  }

  @Override
  public double getTax() {
    if (this.earnings >= 8000) {
      return this.earnings * 0.2;
    } else if (this.earnings >= 6000) {
      return this.earnings * 0.15;
    } else if (this.earnings >= 4000) {
      return this.earnings * 0.1;
    } else if (this.earnings >= 2000) {
      return this.earnings * 0.05;
    } else {
      return this.earnings * 0.01;
    }
  }
}
