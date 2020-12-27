package konkuk.sylee.assignment3;

public class Main {
  public static void main(String[] args){
    System.out.println("202011339 이승윤");

    TaxManager seoul = new TaxManager("서울",50);
    seoul.addTaxPayer(new TaxPayer("최길동","1001",1000));
    seoul.addTaxPayer(new SalaryTaxPayer("홍길동","2001","우리",6500));
    seoul.addTaxPayer(new BusinessTaxPayer("이길동","3001",5000,6000));
    seoul.addTaxPayer(new SalaryTaxPayer("김길동","2002","나라",3000));
    seoul.addTaxPayer(new BusinessTaxPayer("박길동","3002",6000,1000));
    seoul.addTaxPayer(new BusinessTaxPayer("고길동","3003",4000,2000));

    seoul.showTax();
    System.out.println();
    seoul.showSalaryTaxPayer();
    System.out.println();
    seoul.showBusinessTaxPayer();

    System.out.println();

    System.out.println("202011339 이승윤");

    TaxManager pyeongchang = new TaxManager("평창",3);
    pyeongchang.addTaxPayer(new TaxPayer("김평창","1001",2000));
    pyeongchang.addTaxPayer(new SalaryTaxPayer("이평창","3001","용평리조트",4105));
    pyeongchang.addTaxPayer(new BusinessTaxPayer("박평창","2001",10000,3500));
    pyeongchang.addTaxPayer(new TaxPayer("최평창","1002",800));
    System.out.println();
    pyeongchang.showTax();
    System.out.println();
    pyeongchang.showSalaryTaxPayer();
    System.out.println();
    pyeongchang.showBusinessTaxPayer();

  }
}
