package konkuk.sylee.week05;

import java.util.Scanner;

public class Lab01 {

  public static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("202011339 이승윤");
    System.out.println("**** 팀 생성 ****");
    System.out.print("팀원 수 : ");
    int num = sc.nextInt();
    SalesReport team1 = new SalesReport(num);

    team1.computeStats();

    System.out.println();
    System.out.println("**** 최고사원 정보 ****");
    System.out.println(team1.getBestClerk());

    System.out.println();
    System.out.println("**** 팀 정보 ****");
    System.out.println(team1);
  }
}

class Salesman {

  public static Scanner sc = new Scanner(System.in);

  private String name;
  private double sales;

  public Salesman() {
    readInput();
  }

  public void readInput() {
    System.out.print("이름: ");
    name = sc.nextLine();
    System.out.print("실적: ");
    sales = sc.nextDouble();
    sc.nextLine(); // to Flush '\n'

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getSales() {
    return sales;
  }

  @Override
  public String toString() {
    String str = "이름: " + this.name + " / 실적: " + sales;
    return str;
  }
}

class SalesReport {

  private final Salesman[] team;
  private double highestSales;
  private double averageSales;
  private final int number; // 팀원 수

  public SalesReport(int number) {
    this.number = number;
    team = new Salesman[this.number];
    for (int i = 0; i < this.number; i++) {
      System.out.println((i + 1) + "번째 팀원 정보 입력");
      team[i] = new Salesman();
      System.out.println("입력완료");
    }

  }

  public void computeStats() {
    highestSales = team[0].getSales();
    averageSales = team[0].getSales();
    for (int i = 0; i < this.number - 1; i++) {
      if (team[i + 1].getSales() >= highestSales) {
        highestSales = team[i + 1].getSales();
      }
      averageSales += team[i + 1].getSales();
    }
    averageSales /= number;
  }

  public Salesman getBestClerk() {
    Salesman highest = team[0];
    for (int i = 0; i < this.number - 1; i++) {
      if (highest.getSales() <= team[i + 1].getSales()) {
        highest = team[i + 1];
      }
    }
    return highest;
  }

  @Override
  public String toString() {
    String str = "총 팀원 수: " + this.number;
    str += "\n최고판매액: " + highestSales;
    str += "\n평균판매액: " + averageSales;
    str += "\n---------------------\n";
    for (Salesman s : team) {
      str += s.toString() + "\n";
    }
    return str;
  }
}