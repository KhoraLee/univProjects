package konkuk.sylee0408;

public class SyleeCoffee extends SyleeItem {

  private String country;

  public SyleeCoffee(String name, String company, String country) {
    super(name, company);
    this.country = country;
  }

  public SyleeCoffee(String name, String company, int price, int number, String country) {
    super(name, company, price, number);
    this.country = country;
  }

  public String getCountry() {
    return country;
  }

  public boolean isSame(SyleeItem item) {
    if (!(item instanceof SyleeCoffee)) {
      return false;
    }
    SyleeCoffee snack = (SyleeCoffee) item;
    return this.getName().equals(snack.getName()) && this.getCompany().equals(snack.getCompany())
        && this.country == snack.getCountry();
  }

  @Override
  public String toString() {
    return "제품이름 : " + this.getName() +
        "\n판매회사 : " + this.getCompany() +
        "\n판매가격 : " + this.getPrice() +
        "\n재고수량 : " + this.getNumber() +
        "\n원산지 : " + this.country;
  }
}
