package konkuk.sylee.week11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Lab01 {

  public static void main(String[] args) {
    UI ui = new UI();
  }

}

class UI extends JFrame {

  Container c = this.getContentPane();
  String[] items = {"삼전", "LG", "네이버", "카카오"};
  Color[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE};
  JTextField[] inputText = new JTextField[items.length];
  JPanel panel = new JPanel();
  ChartPanel chart = new ChartPanel();

  int[] data = {0, 0, 0, 0};
  int[] arcAngle = new int[4];

  public UI() {
    super("202011339 이승윤");
    this.setSize(500, 500);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.init();
    this.setVisible(true);
  }

  private void init() {
    panel.setBackground(Color.pink);
    for (int i = 0; i < items.length; i++) {
      inputText[i] = new JTextField("0", 5);
      inputText[i].addActionListener(new tActionListener());
      panel.add(new JLabel(items[i]));
      panel.add(inputText[i]);
    }
    c.add(panel, BorderLayout.NORTH);
    c.add(chart, BorderLayout.CENTER);
  }

  public void drawChart() {
    int sum = 0;
    for (int i = 0; i < items.length; i++) {
      data[i] = Integer.parseInt(inputText[i].getText());
      sum += data[i];
    }

    for (int i = 0; i < items.length; i++) {
      arcAngle[i] = (int) Math.round((double) data[i] / (double) sum * 360);
    }
    chart.repaint();
  }

  class tActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      drawChart();
    }
  }

  class ChartPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      for (int i = 0; i < items.length; i++) {
        g.setColor(colors[i]);
        g.drawString(items[i] + " " + Math.round(arcAngle[i] * 100. / 360) + "%", 50 + i * 100, 20);
      }

      int start = 0;
      ArrayList<DATA> arr = new ArrayList<>();
      for (int i = 0; i < items.length; i++) {
        arr.add(new DATA(items[i], colors[i], arcAngle[i]));
        Collections.sort(arr, new Comparator<DATA>() {

          @Override
          public int compare(DATA o1, DATA o2) {
            return Integer.compare(o1.arc, o2.arc) * -1;
          }
        });
      }
      for (DATA d : arr) {
        g.setColor(d.color);
        g.fillArc(150, 50, 200, 200, start, d.arc);
        start += d.arc;
      }
    }
  }

}

class DATA {

  final String item;
  final Color color;
  final int arc;

  public DATA(String item, Color color, int arc) {
    this.item = item;
    this.color = color;
    this.arc = arc;
  }

}