package konkuk.sylee.week02;

import java.util.Scanner;

public class Lab01 {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println("202011339 이승윤");

    Scanner sc = new Scanner(System.in);

    System.out.print("학번:");
    double std_id = sc.nextDouble();
    System.out.print("이름:");
    String name = sc.next();
    System.out.print("나이:");
    int age = sc.nextInt();
    System.out.print("주소:");
    sc.nextLine();
    String addr = sc.nextLine();

    System.out.printf("학번:%.0f\n", std_id);
    System.out.println("이름:" + name);
    System.out.println("나이:" + age);
    System.out.println("주소:" + addr);
    sc.close();
  }

}
