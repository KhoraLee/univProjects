package konkuk.sylee0408;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class SyleeVendingMachine {

  private int number; // 판매가능 제품 수
  private ArrayList<SyleeItem> items; // 판매중인 제품들
  private int totalPrice; // 총 판매 금액
  private HashMap<SyleeItem,Integer> sales;

  public int getNumber() {
    return number;
  }

  public ArrayList<SyleeItem> getItems() {
    return items;
  }

  public int getTotalPrice() {
    return totalPrice;
  }

  public HashMap<SyleeItem, Integer> getSales() {
    return sales;
  }

  public SyleeVendingMachine(int number) {
    this.number = number;
    items = new ArrayList<>();
    sales = new HashMap<>();
  }

  public void addItem(SyleeItem item) {
    for (SyleeItem i : items){
      if (i.isSame(item)){
        i.setNumber(i.getNumber()+item.getNumber());
        System.out.println(item.getName() +" 재고가 추가되었습니다.");
        return;
      }
    }

    if (items.size() >= number){
      System.out.println("더이상 제품을 추가할 수 없습니다.");
      return;
    }

    items.add(item);
    sales.put(item,0);
    System.out.println(item.getName() + " 제품 새롭게 추가되었습니다.");
  }

  public void sellItem(SyleeItem item){
    boolean flag = false;
    for (SyleeItem i : items){
      if (i.isSame(item)){
        if (i.getNumber()-1>-1) {
          i.setNumber(i.getNumber() - 1);
          this.totalPrice += i.getPrice();
          System.out.println(i.getName()+" 판매완료");
          sales.put(i,sales.get(i)+1);
          return;
        }
        System.out.println("재고가 없습니다");
        return;
      }
    }

    System.out.println("해당 제품이 존재하지 않습니다.");
  }

  public void showSales(){
    ArrayList<Entry<SyleeItem, Integer>> sorting = new ArrayList<>(sales.entrySet());
    sorting.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()) * -1);
    for(Entry<SyleeItem, Integer> e : sorting){
      System.out.println("-------------------");
      System.out.println(e.getKey());
      System.out.println("-------------------");
    }

  }

  @Override
  public String toString() {
    String line = "==================="
        + "\n판매가능한 제품 수 : " + this.number
        + "\n판매중인 제품 수 : " + items.size()
        + "\n총 판매금액 : " + totalPrice
        + "\n===================\n";
    for (SyleeItem i : items) {
      line += "-------------------\n";
      line += i.toString();
      line += "\n-------------------\n";
    }
    line += "===================";
    return line;
  }
}
