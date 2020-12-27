package konkuk.sylee.assignment4;


public class Main {

  public static void main(String[] args) {
    System.out.println("202011339 이승윤");
    VocManager voc = new VocManager("사용자1");
    voc.makeVoc("words.txt");
    voc.menu();
  }
}
