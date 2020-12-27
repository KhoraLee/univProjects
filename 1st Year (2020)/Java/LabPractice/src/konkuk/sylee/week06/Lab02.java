package konkuk.sylee.week06;

import java.util.Scanner;

interface IoTInterface {

  void turnon();

  void turnoff();

  void control();
}

public class Lab02 {

  public static void main(String[] args) {
    System.out.println("202011339 이승윤");
    Home home = new Home(4);
    home.buyHA(new TV("거실TV", 7));
    home.buyHA(new Refrigerator("주방냉장고", 1));
    home.buyHA(new Boiler("기름보일러", 22));
    home.buyHA(new Vacuum("로봇청소기", 1));

    IoTInterface iot = home.connect();

    if (iot != null) {
      iot.turnon();
      iot.control();
      iot.turnoff();
    } else {
      System.out.println("IoT 장치 연결 실패");
    }
  }

}

abstract class HomeAppliance {

  private String haName;
  private boolean haPower = false;

  public HomeAppliance(String haName) {
    this.haName = haName;
  }

  abstract void showStatus();

  abstract void menu();

  public String getHaName() {
    return haName;
  }

  public void setHaName(String haName) {
    this.haName = haName;
  }

  public boolean isHaPower() {
    return haPower;
  }

  public void setHaPower(boolean haPower) {
    this.haPower = haPower;
  }
}

class TV extends HomeAppliance {

  private int channel;

  public TV(String haName, int channel) {
    super(haName);
    this.channel = channel;
  }

  public int getChannel() {
    return channel;
  }

  public void setChannel(int channel) {
    this.channel = channel;
  }

  @Override
  public void menu() {
    Scanner sc = new Scanner(System.in);
    System.out.println("TV를 제어합니다.");
    System.out.println("1)전원 2)채널 \n원하는 메뉴를 선택하세요: ");
    int choice = sc.nextInt();
    switch (choice) {
      case 1:
        this.setHaPower(!this.isHaPower());
        break;
      case 2:
        if (this.isHaPower()) {
          System.out.println("채널 입력:");
          this.setChannel(sc.nextInt());
        } else {
          System.out.println("전원이 꺼져있습니다.");
        }
        break;
      default:
        System.out.println("입력 메뉴를 확인하세요.");
    }
    System.out.println("TV 제어 종료됨");
  }

  @Override
  public void showStatus() {
    if (this.isHaPower()) {
      System.out.println(
          this.getHaName() + ", 전원상태: " + this.isHaPower() + ", 채널 : " + this.channel + "시청중");
    } else {
      System.out.println(this.getHaName() + ", 전원 꺼져 있음");
    }
  }
}

class Refrigerator extends HomeAppliance {

  private int temp = 2;

  public Refrigerator(String haName, int temp) {
    super(haName);
    this.temp = temp;
  }

  public int getTemp() {
    return temp;
  }

  public void setTemp(int temp) {
    this.temp = temp;
  }

  @Override
  public void menu() {
    Scanner sc = new Scanner(System.in);
    System.out.println("냉장고를 제어합니다.");
    System.out.println("1)전원 2)냉장온도 \n원하는 메뉴를 선택하세요: ");
    int choice = sc.nextInt();
    switch (choice) {
      case 1:
        this.setHaPower(!this.isHaPower());
        break;
      case 2:
        if (this.isHaPower()) {
          System.out.println("냉장온도 입력:");
          this.setTemp(sc.nextInt());
        } else {
          System.out.println("전원이 꺼져있습니다.");
        }
        break;
      default:
        System.out.println("입력 메뉴를 확인하세요.");
    }
    System.out.println("냉장고 제어 종료됨");
  }

  @Override
  public void showStatus() {
    if (this.isHaPower()) {
      System.out.println(
          this.getHaName() + ", 전원상태: " + this.isHaPower() + ", 냉장온도 : " + this.getTemp() + "시청중");
    } else {
      System.out.println(this.getHaName() + ", 전원 꺼져 있음");
    }
  }
}

class Vacuum extends HomeAppliance implements IoTInterface {

  private int level;

  public Vacuum(String haName, int level) {
    super(haName);
    this.level = level;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  @Override
  public void showStatus() {
    if (this.isHaPower()) {
      System.out.println(
          this.getHaName() + ", 전원상태: " + this.isHaPower() + ", 청소기 세기 : " + this.level + "레벨");
    } else {
      System.out.println(this.getHaName() + ", 전원 꺼져 있음");
    }
  }

  @Override
  public void menu() {
    Scanner sc = new Scanner(System.in);
    System.out.println("청소기를 제어합니다.");
    System.out.println("1)전원 2)청소 레벨 \n원하는 메뉴를 선택하세요: ");
    int choice = sc.nextInt();
    switch (choice) {
      case 1:
        this.setHaPower(!this.isHaPower());
        break;
      case 2:
        if (this.isHaPower()) {
          System.out.println("청소 레벨 입력(1~3):");
          int level = sc.nextInt();
          if (level >= 1 && level <= 3) {
            this.setLevel(level);
          } else {
            this.setLevel(1);
          }
          System.out.println("청소 강도 " + this.level + "로 작동중입니다.");
        } else {
          System.out.println("전원이 꺼져있습니다.");
        }
        break;
      default:
        System.out.println("입력 메뉴를 확인하세요.");
    }
    System.out.println("청소기 제어 종료됨");
  }

  @Override
  public void turnon() {
    this.setHaPower(true);
  }

  @Override
  public void turnoff() {
    this.setHaPower(false);
  }

  @Override
  public void control() {
    this.menu();
  }
}

class Boiler extends HomeAppliance implements IoTInterface {

  private int temp = 27;

  public Boiler(String haName, int temp) {
    super(haName);
    this.temp = temp;
  }

  public int getTemp() {
    return temp;
  }

  public void setTemp(int temp) {
    this.temp = temp;
  }

  @Override
  public void menu() {
    Scanner sc = new Scanner(System.in);
    System.out.println("보일러를 제어합니다.");
    System.out.println("1)전원 2)보일러 온도 \n원하는 메뉴를 선택하세요: ");
    int choice = sc.nextInt();
    switch (choice) {
      case 1:
        this.setHaPower(!this.isHaPower());
        break;
      case 2:
        if (this.isHaPower()) {
          System.out.println("보일러 온도 입력:");
          this.setTemp(sc.nextInt());
        } else {
          System.out.println("전원이 꺼져있습니다.");
        }
        break;
      default:
        System.out.println("입력 메뉴를 확인하세요.");
    }
    System.out.println("보일러 제어 종료됨");
  }

  @Override
  public void showStatus() {
    if (this.isHaPower()) {
      System.out.println(
          this.getHaName() + ", 전원상태: " + this.isHaPower() + ", 보일러 온도 : " + this.temp + "도 ");
    } else {
      System.out.println(this.getHaName() + ", 전원 꺼져 있음");
    }
  }

  @Override
  public void turnon() {
    this.setHaPower(true);
  }

  @Override
  public void turnoff() {
    this.setHaPower(false);
  }

  @Override
  public void control() {
    this.menu();
  }

}

class Home {

  private final HomeAppliance[] apps;
  private final int capacity;
  private int count = 0;

  public Home(int capacity) {
    this.capacity = capacity;
    this.apps = new HomeAppliance[this.capacity];
  }

  public void buyHA(HomeAppliance app) {
    if (this.count < this.capacity) {
      apps[count++] = app;
    } else {
      System.out.println("더이상 공간이 없음");
    }
  }

  public void open() {
    while (true) {
      System.out.println("제어할 가전제품을 선택하세요");
      for (int i = 0; i < count; i++) {
        System.out.println((i + 1) + ") " + apps[i].getHaName());
      }
      System.out.println("제품을 선택해 주세요: ");
      Scanner sc = new Scanner(System.in);
      int index = sc.nextInt();
      if (index >= 1 && index <= count) {
        apps[index - 1].menu();
        apps[index - 1].showStatus();
      } else {
        System.out.println("가전제품의 제어를 종료합니다");
        break;
      }
    }
  }

  public void scanIoTDevice() {
    System.out.println("원격 제어 가능 제품");
    for (int i = 0; i < count; i++) {
      if (apps[i] instanceof IoTInterface) {
        System.out.println((i + 1) + ") " + apps[i].getHaName());
      }
    }
  }

  public IoTInterface connect() {
    System.out.println("원격 제어할 가전제품을 선택하세요");
    for (int i = 0; i < count; i++) {
      if (apps[i] instanceof IoTInterface) {
        System.out.println((i + 1) + ") " + apps[i].getHaName());
      }
    }
    System.out.println("제품을 선택해 주세요: ");
    Scanner sc = new Scanner(System.in);
    int index = sc.nextInt();
    if (index >= 1 && index <= count) {
      return (IoTInterface) apps[index - 1];
    } else {
      return null;
    }

  }

}