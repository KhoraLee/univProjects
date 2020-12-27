package konkuk.sylee.week12;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Lab01 {

  public static void main(String[] args) {
    UI ui = new UI();
  }

}

class UI extends JFrame {

  Container c = this.getContentPane();

  public UI() {
    super("202011339 이승윤");
    this.setSize(500, 500);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.init();
    this.setVisible(true);
  }

  private void init() {
    c.add(new TimeLabel(), BorderLayout.NORTH);
  }

}


class TimeLabel extends JLabel implements Runnable {

  SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");

  public TimeLabel() {
    this.setOpaque(true);
    this.setFont(new Font("궁서체", Font.BOLD, 24));
    this.setHorizontalAlignment(CENTER);
    this.setBackground(Color.GREEN);
    Thread t = new Thread(this);
    t.start();
  }

  @Override
  public void run() {
    while (true) {
      this.setText(date.format(Calendar.getInstance().getTime()));
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
