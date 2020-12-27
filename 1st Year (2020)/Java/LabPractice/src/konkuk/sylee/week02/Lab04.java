package konkuk.sylee.week02;

import java.util.Scanner;

public class Lab04 {

  public static void main(String[] args) {
    System.out.println("202011339 이승윤");

    int ptry = 0;
    String passwd = "greenjoa";
    Scanner sc = new Scanner(System.in);

    while (ptry < 3) {
      System.out.print("암호를 입력하세요 : ");
      String input = sc.nextLine();
      if (input.equals(passwd)) {
        System.out.println("환영합니다.");
        sc.close();
        System.exit(0);
      }
      System.out.println("암호가 틀립니다.");
      ptry++;
    }
    System.out.println("접속을 거부합니다.");
    sc.close();
  }

}
