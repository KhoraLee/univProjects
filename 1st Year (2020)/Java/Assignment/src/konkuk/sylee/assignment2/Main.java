package konkuk.sylee.assignment2;

import java.util.Random;
import java.util.Scanner;

public class Main {

  public static Scanner sc = new Scanner(System.in);
  static int[][] bingofield; // -1 은 user, -2는 com 이 선택한 자리
  static int[] num_addr = new int[26]; // 숫자 위치 num_addr[7] : '7' 이 있는 위치 (ex 23 -> bingofield[2][3])
  static boolean ongoing = true; // 게임 진행 상태
  static boolean user_turn = true; // 순서
  static int count = 0; // 선택된 숫자의 개수

  public static void main(String[] args) {
    System.out.println("202011339 이승윤"); //학번 출력
    bingofield = initbingo(); // 빙고판 생성
    printBingo(bingofield);  // 빙고판 출력
    while (ongoing) {
      if (count < 25) {
        if (user_turn) {
          userturn(); // 유저 턴
        } else {
          comturn(); // 컴퓨터 턴
        }
      } else {
        System.out.println("모든 숫자가 선택되었지만 빙고가 없어 게임을 종료합니다.");
        break;
      }
    }
  }

  /**
   * 유저의 턴 유저의 입력을 받아 칸에 표시 후 빙고 여부를 체크 빙고인 경우 승리를 출력 후 게임을 종료
   */
  public static void userturn() {
    int user, addr;
    while (true) {
      System.out.print("숫자를 선택하세요 (User) : ");
      user = sc.nextInt();
      if (user > 0 && user <= 25) { // array out of index 를 해결하기 위한 방법
        addr = num_addr[user];
        if (bingofield[addr / 10][addr % 10] != -1 && bingofield[addr / 10][addr % 10] != -2) {
          break; //이미 선택된 숫자인지 체크 후 아니면 break
        }
      }
      System.out.println("이미 선택되었거나 존제하지 않는 숫자입니다.");
    }
    bingofield[addr / 10][addr % 10] = -1; // 숫자 선택 기록 (유저 : -1)
    user_turn = false; // 턴 넘기기
    count++; // 선택된 숫자 개수 기록
    printBingo(bingofield); // 빙고판 출력
    if (checkBingo(-1)) {
      System.out.println("유저 빙고!");
    }

  }

  /**
   * 컴퓨터의 턴 랜덤한 수를 칸에 표시 후 빙고 여부를 체크 빙고인 경우 승리를 출력 후 게임을 종료
   */
  public static void comturn() {
    Random r = new Random();
    int com, addr;
    while (true) {
      com = r.nextInt(25) + 1;
      addr = num_addr[com];
      if (bingofield[addr / 10][addr % 10] != -1 && bingofield[addr / 10][addr % 10] != -2) {
        break; //이미 선택된 숫자인지 체크 후 아니면 break
      }
    }
    System.out.println("컴퓨터는 " + com + "을 선택했습니다.");
    bingofield[addr / 10][addr % 10] = -2; // 숫자 선택 기록 (컴퓨터 : -2)
    user_turn = true; // 턴 넘기기
    count++; // 선택된 숫자 개수 기록
    printBingo(bingofield);  // 빙고판 출력
    if (checkBingo(-2)) {
      System.out.println("컴퓨터 빙고!");
    }
  }

  /**
   * k에 따른 빙고 여부 체크 -1:User, -2:Com
   *
   * @param k User와 Com 을 비교하기 위한 변수
   */
  public static boolean checkBingo(int k) {
    //대각선 체크
    if (bingofield[0][0] == k && bingofield[1][1] == k && bingofield[2][2] == k
        && bingofield[3][3] == k && bingofield[4][4] == k) {
      ongoing = false;
      return true;
    } else if (bingofield[0][4] == k && bingofield[1][3] == k && bingofield[2][2] == k
        && bingofield[3][1] == k && bingofield[4][0] == k) {
      ongoing = false;
      return true;
    }
    //가로 세로 체크
    for (int i = 0; i < 5; i++) {
      if (bingofield[i][0] == k && bingofield[i][1] == k && bingofield[i][2] == k
          && bingofield[i][3] == k && bingofield[i][4] == k) {
        ongoing = false;
        return true;
      } else if (bingofield[0][i] == k && bingofield[1][i] == k && bingofield[2][i] == k
          && bingofield[3][i] == k && bingofield[4][i] == k) {
        ongoing = false;
        return true;
      }
    }
    return false;
  }

  /**
   * 1~25의 숫자를 랜덤한 위치에 배열한 5*5의 2차원 배열을 리턴
   *
   * @return 2차원의 5*5 배열
   */
  public static int[][] initbingo() {
    int[][] arr = new int[5][5];
    int[] ranarr = new int[25];
    Random r = new Random();
    // 무작위로 1~25 숫자 나열
    for (int i = 0; i < 25; i++) {
      ranarr[i] = r.nextInt(25) + 1;
      for (int j = 0; j < i; j++) {
        if (ranarr[i] == ranarr[j]) {
          i--;
          break;
        }
      }
    }
    // 1차원 배열을 2차원 5*5 배열로 복사
    for (int i = 0; i < 5; i++) {
      System.arraycopy(ranarr, 5 * i, arr[i], 0, 5);
    }
    // 숫자들 위치를 index에 기록
    num_addr[0] = -1;
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        num_addr[arr[i][j]] = 10 * i + j;
      }
    }
    return arr;
  }

  /**
   * 중첩 for 문을 통한 2차원 배열의 출력 빙고 게임 진행에 따라 User와 Com의 상황을 고려하여 출력함
   *
   * @param arr 5*5 배열의 빙고판
   */
  public static void printBingo(int[][] arr) {
    System.out.println("===============");
    for (int[] a : arr) {
      for (int i : a) {
        if (i == -1) {
          System.out.print(" U ");
        } else if (i == -2) {
          System.out.print(" C ");
        } else {
          System.out.printf("%2d ", i);
        }
      }
      System.out.println();
    }
    System.out.println("===============");
  }

}
