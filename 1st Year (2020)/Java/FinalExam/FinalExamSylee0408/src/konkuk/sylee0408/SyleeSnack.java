package konkuk.sylee0408;

public class SyleeSnack extends SyleeItem {

  private int calorie;

  public SyleeSnack(String name, String company, int calorie) {
    super(name, company);
    this.calorie = calorie;
  }

  public SyleeSnack(String name, String company, int price, int number, int calorie) {
    super(name, company, price, number);
    this.calorie = calorie;
  }

  public int getCalorie() {
    return calorie;
  }

  public boolean isSame(SyleeItem item) {
    if (!(item instanceof SyleeSnack)) {
      return false;
    }
    SyleeSnack snack = (SyleeSnack) item;
    return this.getName().equals(snack.getName()) && this.getCompany().equals(snack.getCompany())
        && this.calorie == snack.getCalorie();
  }

  @Override
  public String toString() {
    return "제품이름 : " + this.getName() +
        "\n판매회사 : " + this.getCompany() +
        "\n판매가격 : " + this.getPrice() +
        "\n재고수량 : " + this.getNumber() +
        "\n칼로리 : " + this.calorie;
  }

}
