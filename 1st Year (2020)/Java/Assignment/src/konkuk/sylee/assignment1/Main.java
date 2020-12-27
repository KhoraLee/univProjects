package konkuk.sylee.assignment1;

import java.util.Scanner;

public class Main {

  public static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("202011339 이승윤"); //학번 출력

    while (true) {
      System.out.println("1) 복리 예금 계산기  2) 구구단 출력하기  3) 종료");
      int sel = sc.nextInt();
      if (sel == 1) {
        deposit();
      } else if (sel == 2) {
        multiplication();
      } else if (sel == 3) {
        System.exit(0);

      }
    }
  }

  public static void deposit() {
    System.out.println("===== 복리 예금 계산기 =====");
    System.out.print("원금을 입력하세요 : ");
    int principal = sc.nextInt();
    System.out.print("연이율을 입력하세요 : ");
    double interest = sc.nextDouble();
    System.out.println("--------------------");
    System.out.println(" 년도수   원리금");
    System.out.println("--------------------");
    double total = principal;
    int year = 0;
    while (total < 2 * principal) {
      year++;
      total *= (1 + interest / 100);
      System.out.printf(" %-7d %7.2f\n", year, total);
    }
    System.out.println("--------------------");
    System.out.println(year + "년 걸림\n");
  }

  public static void multiplication() {
    System.out.println("====== 구구단 출력하기 ======");
    int row = 0;
    while (true) {
      System.out.print("출력 단수 : ");
      row = sc.nextInt();
      if (1 > row || row > 8) {
        System.out.println("출력 단수 입력이 잘못되었습니다.");
      } else {
        break;
      }
    }
    float col = (float) 8 / row;

    for (int k = 0; k < col; k++) {
      for (int j = 1; j < 10; j++) {
        for (int i = 2 + (k * row); i < ((row + 2 + (k * row)) > 10 ? 10 : (row + 2 + (k * row)));
            i++) {
          System.out.printf("%d * %d = %2d \t", i, j, i * j);
        }
        System.out.println();
      }
      System.out.println();
    }
  }

}
