package konkuk.sylee.week04;

public class Lab01 {

  public static void main(String[] args) {
    System.out.println("202011339 이승윤");

    TV tv = new TV();
    tv.powerOnOff();
    tv.changeChannel(15);
    tv.changeVolume(19);

  }
}

class TV {

  public boolean power = false;
  public int channel = 1;
  public int volume = 1;

  public void show() {
    if (power) {
      System.out.println("현재 " + channel + "번 시청중이며 볼륨은 " + volume);
    } else {
      System.out.println("TV 전원이 꺼져있음");
    }
  }

  public void powerOnOff() {
    power = !power;
    show();
  }

  public void channelUp() {
    if (power) {
      ++channel;
      show();
    } else {
      System.out.println("TV 전원을 먼져 커세요");
    }
  }

  public void channelDown() {
    if (power) {
      --channel;
      show();
    } else {
      System.out.println("TV 전원을 먼져 커세요");
    }
  }

  public void volumeUp() {
    if (power) {
      if (volume > 19) {
        System.out.println("최대 볼륨은 20입니다.");
      } else {
        ++volume;
      }
      show();
    } else {
      System.out.println("TV 전원을 먼져 커세요");
    }
  }

  public void volumeDown() {
    if (power) {
      if (volume < 1) {
        System.out.println("최소 볼륨은 0입니다.");
      } else {
        --volume;
      }
      show();
    } else {
      System.out.println("TV 전원을 먼져 커세요");
    }
  }

  public void changeChannel(int ch) {
    if (power) {
      channel = ch;
      show();
    } else {
      System.out.println("TV 전원을 먼져 커세요");
    }
  }

  public void changeVolume(int vol) {
    if (power) {
      if (vol > 20) {
        System.out.println("최대 볼륨은 20입니다.");
      } else if (vol < 0) {
        System.out.println("최소 볼륨은 0입니다.");
      } else {
        volume = vol;
      }
      show();
    } else {
      System.out.println("TV 전원을 먼져 커세요");
    }
  }
}