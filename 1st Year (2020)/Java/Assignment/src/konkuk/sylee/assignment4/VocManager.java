package konkuk.sylee.assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class VocManager {

  //Fields
  static Scanner sc = new Scanner(System.in);
  private final String userName;
  private final Word[] voc = new Word[100];
  private int count = 0;
  //End of Fields declaration

  /**
   * 유저 이름을 받아서 initialize 하는 생성자
   *
   * @param userName 유저 이름
   */
  public VocManager(String userName) {
    this.userName = userName;
  }

  /**
   * VocManager 내부의 단어장에 단어를 추가하는 메소드
   *
   * @param word 단어장 배열 voc 에 추가하려는 Word(단어)
   */
  public void addWord(Word word) {
    if (count < voc.length) {
      voc[count++] = word;
    } else {
      System.out.println("더이상 단어장에 단어를 추가할 수 없습니다.");
    }
  }

  /**
   * 단어장 파일로부터 단어 배열 만들기
   *
   * @param path 단어장 파일 경로
   */
  public void makeVoc(String path) {
    try (Scanner sc = new Scanner(new File(path))) {
      while (sc.hasNextLine()) {
        String read = sc.nextLine();
        String[] tmp = read.split("\t");
        this.addWord(new Word(tmp[0].trim(), tmp[1].trim()));
      }
      System.out.println(userName + "의 단어장 생성이 완료되었습니다.");
    } catch (FileNotFoundException e) {
      System.out.println("파일을 찾을 수 없습니다."
          + "\n파일 이름을 확인해 주세요");
    }
  }

  /**
   * VocManager 메뉴
   */
  public void menu() {
    int choice = -1;
    while (choice != 3) {
      System.out.println("\n------ " + userName + "의 단어장 ------");
      try {
        System.out.println("1) 단어검색 2) 객관식 퀴즈 3) 종료");
        System.out.print("메뉴를 선택하세요 : ");
        choice = sc.nextInt();
        sc.nextLine(); // to flush '\n'
      } catch (InputMismatchException e) {
        sc.nextLine(); // to flush '\n'
        choice = -1;
        System.out.println("잘못 입력하셨습니다.");
      }

      switch (choice) {
        case 1:
          searchVoc2();
          choice = -1;
          break;
        case 2:
          multipleChoiceQuiz();
          choice = -1;
          break;
        case 3:
          System.out.println(userName + "의 단어장을 종료합니다.");
          break;
        default:
          if (choice != -1) {
            System.out.println("잘못 입력하셨습니다.");
          }
          break;
      }

    }
  }

  /**
   * 정확히 일치하는 단어를 찾는 메뉴
   */
  private void searchVoc() {
    System.out.println("\n------ 단어 검색 ------");
    System.out.print("검색할 단어를 입력하세요 : ");
    String input = sc.nextLine().trim();
    for (Word word : voc) {
      if (word != null) {
        if (word.eng.equals(input)) {
          System.out.println("단어의 뜻 : " + word.kor);
          break;
        }
      } else {
        System.out.println("단어장에 등록된 단어가 아닙니다.");
        break;
      }
    }
    System.out.println("--------------------\n");
  }

  /**
   * 입력한 문자열을 포함하는 모든 단어를 찾는 메뉴
   */
  private void searchVoc2() {

    System.out.println("\n------ 단어 검색 ------");
    System.out.print("검색할 단어를 입력하세요 : ");
    String input = sc.nextLine().trim();
    boolean isExist = false;
    for (Word word : voc) {
      if (word != null) {
        if (word.eng.contains(input)) {
          System.out.println("단어 : " + word.eng + ", 단어의 뜻 : " + word.kor);
          isExist = true;
        }
      } else {
        break;
      }
    }
    if (!isExist) {
      System.out.println("문자열을 포함하는 단어가 단어장에 등록어있지 않습니다.");
    }
    System.out.println("--------------------");
  }

  /**
   * 총 10문제의 객관식 퀴즈를 시행하는 메뉴
   */
  private void multipleChoiceQuiz() {
    System.out.println();
    long startTime = System.currentTimeMillis(); // 시작 시간 기록

    //정답이 될 단어 10개 선택
    Word[] quiz = new Word[10];
    Random r = new Random();
    for (int i = 0; i < 10; i++) {
      quiz[i] = voc[r.nextInt(10)];
      for (int j = 0; j < i; j++) {
        if (quiz[i].equals(quiz[j])) {
          i--;
          break;
        }
      }
    }

    //문제 출제
    int correct = 0; // 맞춘 문제 수
    for (int i = 0; i < 10; i++) {
      System.out.println("------ 객관식 퀴즈 " + (i + 1) + "번 ------");
      System.out.println(quiz[i].eng + "의 뜻은 무엇일까요?");

      //선택지 생성
      int ans = r.nextInt(4); // 0~3
      String[] choices = new String[4];
      choices[ans] = quiz[i].kor;
      for (int j = 0; j < 4; j++) {
        boolean isSame = false;
        String tmp = null;
        if (choices[j] == null) {
          tmp = voc[r.nextInt(count)].kor;
          for (String s : choices) {
            if (s != null && s.equals(tmp)) {
              isSame = true;
            }
          }
          if (isSame) {
            j--;
          } else {
            choices[j] = tmp;
          }
        }
      }

      //선택지 출력
      for (int j = 0; j < 4; j++) {
        System.out.println(j + 1 + ") " + choices[j]);
      }

      //정답 입력 및 체크
      int sel = -1;
      while (true) {
        try {
          System.out.print("답을 입력하세요 : ");
          sel = sc.nextInt() - 1;
          sc.nextLine(); // to flush '\n'
          if (sel >= 0 && sel <= 3) {
            break;
          } else {
            throw new InputMismatchException(
                "Int out of Range"); // 1~4 사이의 값이 아닌경우 exception을 throw
          }
        } catch (InputMismatchException e) {
          //int값이지만 범위 바깥인경우 이미 '\n'를 처리했기에 그 외에만 처리
          if (e.getMessage() == null) {
            sc.nextLine(); // to flush '\n'
          }
          System.out.println("잘못된 입력입니다. 1~4의 선택지중에 선택해 주세요.");
        }
      }
      if (sel == ans) {
        System.out.println("정답입니다.");
        correct++;
      } else {
        System.out.println("오답입니다. 정답은 " + (ans + 1) + "번 입니다.");
      }
    }

    //결과 출력
    long endTime = System.currentTimeMillis();
    double spentTime = (endTime - startTime) / 1000d; // 소수점 자리를 살리기 위해 double로 결과를 만듬
    // long 나누기 int 의 경우 정수로 반환됨
    System.out.println(userName + "님 10문제 중 " + correct + "개 맞추셨고, 총" + spentTime + "초 소요되었습니다.");
  }

}
