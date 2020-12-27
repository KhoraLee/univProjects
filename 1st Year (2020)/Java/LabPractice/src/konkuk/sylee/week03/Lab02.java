package konkuk.sylee.week03;

public class Lab02 {

  public static void main(String[] args) {
    System.out.println("202011339 이승윤");

    String[] name = new String[] {"홍길동", "고길동", "김길동", "이길동"};
    int[][] score = new int[][] {
        {10, 20, 30, 0, 1}, // 국어 영어 수학 총점(Default : 0) 순위(Default : 1)
        {20, 30, 40, 0, 1},
        {10, 25, 30, 0, 1},
        {30, 30, 40, 0, 1}
    };
    calc(score);
    for (int i = 0; i < name.length; i++) {
      System.out.print(name[i] + " >> ");
      print_arr(score[i]);
    }
  }

  public static void calc(int[][] score) {
    // 평균 구하기
    for (int[] arr : score) {
      for (int i = 0; i < 3; i++) {
        arr[3] += arr[i];
      }
    }

    // 등수 구하기
    for (int i = 0; i < score.length; i++) {
      for (int j = 0; j < score.length; j++) {
        if (score[i][3] < score[j][3]) {
          score[i][4]++;
        }
      }
    }

  }

  public static void print_arr(int[] arr) {
    int count = 0;
    for (int i : arr) {
      System.out.print(i + " ");
      count++;
      if (count > 2 && count < 5) {
        System.out.print(": ");
      }
    }
    System.out.println();
  }

}
