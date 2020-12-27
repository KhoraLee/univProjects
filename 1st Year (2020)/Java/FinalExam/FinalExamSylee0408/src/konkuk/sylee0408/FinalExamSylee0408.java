package konkuk.sylee0408;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FinalExamSylee0408 {

  public static void main(String[] args) {
    System.out.println("202011339 이승윤");

    System.out.println("2) Item 항목 생성하기");
//    SyleeItem item = new SyleeItem("A커피","A다방");
    System.out.println();

    System.out.println("3) Coffee 항목 생성하기(2.5점) 및 출력하기(2.5점)");
    SyleeItem item1 = new SyleeCoffee("A커피", "A다방", "코스타리카");
    SyleeItem item2 = new SyleeCoffee("A커피", "A다방", 100, 2, "코스타리카");
    System.out.println(item1);
    System.out.println(item2);

    System.out.println("4) Snack 항목 생성하기(2.5점) 및 출력하기(2.5점)");
    SyleeItem item3 = new SyleeSnack("새우과자", "N사", 500);
    SyleeItem item4 = new SyleeSnack("새우과자", "N사", 100, 2, 500);
    System.out.println(item3);
    System.out.println(item4);
    System.out.println();

    System.out.println("5) VendingMachine 생성 (2.5점) 및 출력하기(2.5점)");
    SyleeVendingMachine vm1 = new SyleeVendingMachine(4);
    System.out.println(vm1);

    System.out.println("6) 아이템 추가하기(2.5점) 및 저장하기(5점)");
    vm1.addItem(new SyleeCoffee("A커피", "A다방", 100, 2, "코스타리카"));
    vm1.addItem(new SyleeCoffee("B커피", "B다방", 200, 1, "케냐"));
    vm1.addItem(new SyleeSnack("새우과자", "N사", 100, 2, 500));
    vm1.addItem(new SyleeSnack("감자과자", "N사", 150, 1, 400));

    System.out.println("7) 판매하는 아이템 정보 출력하기 (5점)");
    System.out.println(vm1);

    System.out.println("8) 아이템 추가하기 예외처리(2.5점)");
    vm1.addItem(new SyleeSnack("고구마과자", "S사", 150, 2, 300));

    System.out.println("9) 등록된 아이템 추가시 재고 늘리기 (5점)");
    vm1.addItem(new SyleeCoffee("B커피", "B다방", 200, 1, "케냐"));
    vm1.addItem(new SyleeCoffee("A커피", "A다방", 100, 2, "코스타리카"));
    vm1.addItem(new SyleeSnack("새우과자", "N사", 100, 2, 500));

    System.out.println("10) 아이템 판매시 재고 감소(5점) 및 판매금액 누적 (5점)");
    vm1.sellItem(new SyleeCoffee("A커피", "A다방", "코스타리카"));
    vm1.sellItem(new SyleeSnack("새우과자", "N사", 500));
    vm1.sellItem(new SyleeSnack("새우과자", "N사", 700));
    System.out.println(vm1);

    System.out.println("11) 총 판매금액이 많은 순서로 출력하기 (5점)");
    vm1.showSales();
    System.out.println();

    System.out.println("12) 파일 읽어서 VendingMachine 생성하기 (10점)");
    SyleeVendingMachine vm = readFile("data.txt");
    System.out.println(vm);

    SyleeFrame frame = new SyleeFrame("202011339 이승윤 기말고사");

  }

  public static SyleeVendingMachine readFile(String filename) {
    SyleeVendingMachine vm = null;
    try (Scanner sc = new Scanner(new File(filename))) {
      vm = new SyleeVendingMachine(sc.nextInt());
      sc.nextLine();
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        String[] tmp = line.split(" : ");
        if (tmp[0].trim().equals("Coffee")) {
          vm.addItem(
              new SyleeCoffee(tmp[1].trim(), tmp[2].trim(), Integer.parseInt(tmp[3].trim()),
                  Integer.parseInt(tmp[4].trim()), tmp[5].trim()));
        } else if (tmp[0].trim().equals("Snack")) {
          vm.addItem(new SyleeSnack(tmp[1].trim(), tmp[2].trim(), Integer.parseInt(tmp[3]),
              Integer.parseInt(tmp[4]), Integer.parseInt(tmp[5])));
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return vm;
  }
}
