package konkuk.sylee.week02;

import java.util.Random;
import java.util.Scanner;

public class Lab03 {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println("202011339 이승윤");
    Random r = new Random();
    Scanner sc = new Scanner(System.in);

    System.out.println("가위(0), 바위(1), 보(2)중에 하나를 입력해주세요.");
    System.out.print("입력 : ");
    int input = sc.nextInt();
    int com = r.nextInt(3);
    System.out.println("user : " + (input == 0 ? "가위" : (input == 1 ? "바위" : "보")));
    System.out.println(" com : " + (com == 0 ? "가위" : (com == 1 ? "바위" : "보")));

    System.out.println((com == input) ? "무승부" : (((com + 1) % 3 == input) ? "유저 승리" : "유저 패배"));
    sc.close();
  }

}
