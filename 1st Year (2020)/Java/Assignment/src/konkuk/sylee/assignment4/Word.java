package konkuk.sylee.assignment4;

public class Word {

  String eng;
  String kor;

  public Word(String eng, String kor) {
    this.eng = eng;
    this.kor = kor;
  }

  @Override
  public String toString() {
    return eng + " : " + kor;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Word) {
      return ((Word) obj).eng == this.eng && ((Word) obj).kor == this.kor;
    } else {
      return super.equals(obj);
    }
  }
}
