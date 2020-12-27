package konkuk.sylee.week03;

import java.util.Random;

public class Lab01 {

  public static void main(String[] args) {
    System.out.println("202011339 이승윤");
    int[] lotto = genlotto();
    System.out.print("로또 번호 : ");
    print_arr(lotto);
  }

  public static int[] genlotto() {
    Random rand = new Random();
    int[] lotto = new int[6];
    for (int i = 0; i < 6; i++) {

      lotto[i] = rand.nextInt(45) + 1;
      for (int j = 0; j < i; j++) {
        if (lotto[i] == lotto[j]) {
          i--;
          break;
        }
      }
    }
    bubble_sort(lotto); // 오름 차순 정렬
    return lotto;
  }

  public static void bubble_sort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = 0; j < arr.length - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          arr[j] = arr[j] ^ arr[j + 1];
          arr[j + 1] = arr[j + 1] ^ arr[j];
          arr[j] = arr[j] ^ arr[j + 1];
        }
      }
    }
  }

  public static void print_arr(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

}
