package konkuk.sylee.assignment3;

public class BusinessTaxPayer extends TaxPayer {

  private int expenses; // 총 지출 비용

  public BusinessTaxPayer(String name, String number, int earnings, int expenses) {
    super(name, number, earnings);
    this.expenses = expenses;
  }

  public int getExpenses() {
    return expenses;
  }

  public void setExpenses(int expenses) {
    this.expenses = expenses;
  }

  @Override
  public double getTax() {
    int taxable = this.earnings - this.expenses;
    if (taxable <= 0) {
      return 0;
    } else if (taxable >= 4000) {
      return taxable * 0.2;
    } else {
      return taxable * 0.1;
    }
  }

  @Override
  public String toString() {
    return "납세자명: " + this.name
        + "\n식별번호: " + this.number
        + "\n소득금액: " + this.earnings
        + "\n총 지출액: " + this.expenses
        + "\n세금액: " + this.getTax();
  }
}
