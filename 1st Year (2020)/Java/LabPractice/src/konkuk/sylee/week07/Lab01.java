package konkuk.sylee.week07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lab01 {

  public static void main(String[] args) {
    System.out.println("202011339 이승윤");
    System.out.println("7주차 실습 1. 파일 정렬해서 합치기");
    fileMerge("words1.txt", "words2.txt", "words.txt");
  }

  public static void fileMerge(String input1, String input2, String output) {
    Scanner in1 = null;
    Scanner in2 = null;
    PrintWriter out = null;
    try {
      in1 = new Scanner(new File(input1));
      in2 = new Scanner(new File(input2));
      int totalLine = 0; // 두개 파일 전체의 라인 수
      int currentLine = 0;
      //1번 파일 라인수 카운팅
      while (in1.hasNext()) {
        in1.nextLine();
        totalLine++;
      }
      //2번 파일 라인수 카운팅
      while (in2.hasNext()) {
        in2.nextLine();
        totalLine++;
      }
      //사용 끝난 Scanner 닫기
      if (in1 != null) {
        in1.close();
      }
      if (in2 != null) {
        in2.close();
      }
      String[] tmp = new String[totalLine]; // 두 파일에서 읽어와서 임시로 저장할 String 배열
      //새로 파일 일기
      in1 = new Scanner(new File(input1));
      in2 = new Scanner(new File(input2));
      //임시 배열에 저장
      while (in1.hasNextLine()) {
        tmp[currentLine++] = in1.nextLine();
      }
      while (in2.hasNextLine()) {
        tmp[currentLine++] = in2.nextLine();
      }
      String[] sorted = sort(tmp); // 오름차순 정렬 실행
      out = new PrintWriter(output);
      fileWriter(sorted, out); // 파일 쓰기
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    } finally {
      if (in1 != null) {
        in1.close();
      }
      if (in2 != null) {
        in2.close();
      }
      if (out != null) {
        out.close();
      }
    }
  }

  //Selection Sort ; 오름차순
  public static String[] sort(String[] str) {
    String tmp = null;
    String[] res = new String[str.length];
    for (int i = 0; i < str.length - 1; i++) {
      for (int j = i + 1; j < str.length; j++) {
        if (str[i].compareTo(str[j]) > 0) {
          tmp = str[i];
          str[i] = str[j];
          str[j] = tmp;
        }
      }
    }
    return str;
  }

  public static void fileWriter(String[] str, PrintWriter out) {
    for (String s : str) {
      out.println(s);
    }
  }

}
