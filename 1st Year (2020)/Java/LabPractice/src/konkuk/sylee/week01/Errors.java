// 빠진 패키지 선언
package konkuk.sylee.week01;


// public class errors : 클래스 이름 불일치(대소문자 구분 errors->Errors)
public class Errors {
  // public Static main(string[] args) { : 대소문자 구분(Static -> static, string[] -> String[] ), 리턴이
  // 없으므로 void 필요
  public static void main(String[] args) {
    System.out.println("202011339 이승윤");
    // System.out.println(“이 프로그램은 많은 오류를 가지고 있다.”); : 따옴표 오류 (” -> ")
    System.out.println("이 프로그램은 많은 오류를 가지고 있다.");
    // System.0ut.println(“그러나 프로그램이 이 문장을 출력된다면”) : 오타(0ut -> out), 따옴표 오류 (” -> "), 마지막에 ; 추가
    System.out.println("그러나 프로그램이 이 문장을 출력된다면");
    // System.out.Println(“모든 오류를 고친 것이다.); : 대소문자 구분(Println -> println), 따옴표 오류 (” -> "), 문자열 끝 빠진
    // 따옴표 " 추가
    System.out.println("모든 오류를 고친 것이다.");
  }
}
