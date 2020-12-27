package konkuk.sylee.week05;

public class Lab02 {

  public static void main(String[] args) {
    System.out.println("202011339 이승윤");
    TicketManager tm = new TicketManager("Lab 콘서트", 10);
    tm.register(new Ticket(1, 2000.0));
    tm.register(new GeneralTicket(2, 3000, false));
    tm.register(new GeneralTicket(3, 5000, true));
    tm.register(new AdvancedTicket(4, 2000, 45));
    tm.register(new AdvancedTicket(5, 2000, 15));

    tm.showAdvancedTicket(10);

  }
}

class Ticket {

  protected int number;
  protected double price;

  public Ticket(int number, double price) {
    this.number = number;
    this.price = price;
  }

  public Ticket(int number) {
    this(number, 0.0);
  }

  public Ticket() {
    this(0, 0.0);
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  @Override
  public String toString() {
    return "티켓번호: " + this.number + "\n티켓가격: " + this.price;
  }
}

class GeneralTicket extends Ticket {

  private boolean payByCredit;

  public GeneralTicket(int number, double price, boolean payByCredit) {
    super(number, price);
    this.payByCredit = payByCredit;
  }

  public GeneralTicket(int number, boolean payByCredit) {
    this(number, 0.0, payByCredit);
  }

  public boolean isPayByCredit() {
    return payByCredit;
  }

  public void setPayByCredit(boolean payByCredit) {
    this.payByCredit = payByCredit;
  }

  @Override
  public double getPrice() {
    if (this.payByCredit) {
      return super.getPrice() * 1.1;

    } else {
      return super.getPrice();
    }
  }

  @Override
  public String toString() {
    return super.toString() + "\n카드결재: " + this.isPayByCredit() + "\n결재금액: " + this.getPrice()
        + "\n";
  }
}

class AdvancedTicket extends Ticket {

  private int advancedDays;

  public AdvancedTicket(int number, double price, int advancedDays) {
    super(number, price);
    this.advancedDays = advancedDays;
  }

  public int getAdvancedDays() {
    return advancedDays;
  }

  public void setAdvancedDays(int advancedDays) {
    this.advancedDays = advancedDays;
  }

  @Override
  public double getPrice() {
    if (this.advancedDays >= 30) {
      return super.getPrice() * 0.7;
    } else if (this.advancedDays >= 20) {
      return super.getPrice() * 0.8;
    } else if (this.advancedDays >= 10) {
      return super.getPrice() * 0.9;
    } else {
      return super.getPrice();
    }
  }

  @Override
  public String toString() {
    return super.toString() + "\n예약일: " + this.advancedDays + "일 전\n결재금액: " + this.getPrice()
        + "\n";
  }
}

class TicketManager {

  private final Ticket[] tickets;
  private final String name;
  private final int number; //좌석 수
  private int count = 0;

  public TicketManager(String name, int number) {
    this.number = number;
    this.name = name;
    tickets = new Ticket[this.number];
  }

  public void register(Ticket ticket) {
    if (this.count < this.number) {
      this.tickets[this.count++] = ticket;
    } else {
      System.out.println("티켓 판매 완료");
    }
  }

  public void showAdvancedTicket(int day) {
    for (Ticket t : tickets) {
      if (t != null && t instanceof AdvancedTicket) {
        AdvancedTicket adt = (AdvancedTicket) t;
        if (adt.getAdvancedDays() >= day) {
          System.out.println(adt.toString());
        }
      }
    }
  }

  public double getTotal() {
    double total = 0;
    for (Ticket t : tickets) {
      if (t != null) {
        total += t.getPrice();
      } else {
        break;
      }
    }
    return total;
  }

  @Override
  public String toString() {
    String str = "공연명: " + this.name + "\n";
    str += "좌석수:" + this.number + "\n";
    str += "총 판매티켓 수:" + this.count + "\n";
    str += "--------------\n";
    for (Ticket t : tickets) {
      if (t != null) {
        str += t.toString();
      } else {
        break;
      }
    }
    str += "총 티켓 판매 금액: " + this.getTotal() + "\n";
    return str;
  }

}
