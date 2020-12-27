package konkuk.sylee.assignment3;

import java.util.Vector;

public class TaxManager {

  private final String city; // 지역 이름
  private final int number; // 납세 대상수
  private TaxPayer[] payer; // 납세자 배열
  private int count = 0; // 현재 등록된 납세자 수

  public TaxManager(String city, int number) {
    this.city = city;
    this.number = number;
    payer = new TaxPayer[number];
  }

  public void addTaxPayer(TaxPayer taxPayer) {
    if (count < number) {
      payer[count++] = taxPayer;
    } else {
      System.out.println("지역 납세 대상자 수를 넘어섰습니다.");
    }
  }

  public void showTax() {
    double tax_total = 0;
    for (TaxPayer tp : payer) {
      if (tp != null) {
        tax_total += tp.getTax();
      } else {
        break;
      }
    }
    System.out.println(this.city + " 지역의 총 세금 징수액은 " + tax_total + "입니다.");
  }

  public void showSalaryTaxPayer() {
    double tax_total = 0;
    System.out.println("***근로소득자의 징수내역***");
    for (TaxPayer tp : payer) {
      if (tp != null && tp instanceof SalaryTaxPayer) {
        SalaryTaxPayer stp = (SalaryTaxPayer) tp;
        tax_total += stp.getTax();
        System.out.println(stp.toString());
        System.out.println("-----------------");
      }
    }
    System.out.println("총 근로소득세 징수금액 : " + tax_total);
  }

  public void showBusinessTaxPayer() {
    double tax_total = 0;
    System.out.println("***근로소득자의 징수내역***");
    for (TaxPayer tp : payer) {
      if (tp != null && tp instanceof BusinessTaxPayer) {
        BusinessTaxPayer btp = (BusinessTaxPayer) tp;
        tax_total += btp.getTax();
        System.out.println(btp.toString());
        System.out.println("-----------------");
      }else if(tp == null){
        break;
      }
    }
    System.out.println("총 사업소득세 징수금액 : " + tax_total);
  }
}
