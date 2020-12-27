package konkuk.sylee0408;

public abstract class SyleeItem {

  private String name;
  private String company;
  private int price;
  private int number;

  public SyleeItem(String name, String company) {
    this.name = name;
    this.company = company;
  }

  public SyleeItem(String name, String company, int price, int number) {
    this.name = name;
    this.company = company;
    this.price = price;
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public String getCompany() {
    return company;
  }

  public int getPrice() {
    return price;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  abstract public boolean isSame(SyleeItem item);
}
