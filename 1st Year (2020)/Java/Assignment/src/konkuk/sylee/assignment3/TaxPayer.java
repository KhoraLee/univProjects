package konkuk.sylee.assignment3;

public class TaxPayer {

  protected String name; // 납세자 이름
  protected String number; // 납세자 번호
  protected int earnings; // 소득 금액

  // Contructors
  public TaxPayer(String name, String number, int earnings) {
    this.name = name;
    this.number = number;
    this.earnings = earnings;
  }

  public double getTax() {
    return earnings * 0.1;
  }

  @Override
  public String toString() {
    return "납세자명: " + this.name
        + "\n식별번호: " + this.number
        + "\n소득금액: " + this.earnings
        + "\n세금액: " + this.getTax();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public int getEarnings() {
    return earnings;
  }

  public void setEarnings(int earnings) {
    this.earnings = earnings;
  }
}
