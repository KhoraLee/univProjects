package konkuk.sylee.week04;

public class Lab03 {
  public static void main(String[] args){
    System.out.println("202011339 이승윤");
    Lamp lamp = new Lamp();
    lamp.turnOnOff();
    lamp.changeLight();
    lamp.changeLight();
    lamp.changeLight();
    lamp.changeLight();
    lamp.turnOnOff();
  }
}

class Lamp {

  public boolean isOn;
  public int illuminance;

  public Lamp(boolean isOn, int illuminance) {
    this.isOn = isOn;
    this.illuminance = illuminance;
  }

  public Lamp(boolean isOn) {
    this(isOn, 1);
  }

  public Lamp(int illuminance) {
    this(false, illuminance);
  }

  public Lamp() {
    this(false, 1);
  }

  public void turnOnOff() {
    this.isOn = !this.isOn;
    show();
  }

  public void changeLight() {
    this.illuminance++;
    this.illuminance = this.illuminance % 3 == 0 ? 3 : this.illuminance % 3;
    show();
  }

  public void show() {
    if(isOn) {
      System.out.println("Lamp가 켜져있으며 현재 조도는 "+this.illuminance +"임.");
    }else{
      System.out.println("Lamp가 꺼져있음");

    }
  }

}
