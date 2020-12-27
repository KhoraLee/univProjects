package konkuk.sylee.week02;

public class Lab02 {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println("202011339 이승윤");
    
    int rgb = 0xffffff00; // A : 255, R : 255, G : 255, B:0 -> Yellow
    String strRGB = Integer.toBinaryString(rgb);
    System.out.println("현재 색상 : " + strRGB);
    
    int g_mask = 0xffff00ff; // BitMask to make Green : 0 
    int a_rgb = rgb & g_mask;
    
    String strRGB_a = Integer.toBinaryString(a_rgb);
    System.out.println("변경 색상 : " + strRGB_a);
    
  }

}
