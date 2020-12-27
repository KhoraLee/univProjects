package konkuk.sylee.week10;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Lab01 {

  public static void main(String[] args) {
    UI ui = new UI();
  }

}

class UI extends JFrame {

  String[] data = {"사과", "포도", "배"};
  JRadioButton[] rButton = new JRadioButton[3];
  ButtonGroup rGroup = new ButtonGroup();
  JLabel[] label = new JLabel[3];
  JPanel center = new JPanel();
  JPanel north = new JPanel();

  public UI() {
    super("202011339 이승윤");
    this.setSize(500, 500);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.init();
    this.setVisible(true);
  }

  private void init() {
    for (int i = 0; i < data.length; i++) {
      rButton[i] = new JRadioButton(data[i]);
      rButton[i].addItemListener(new rButtonListener());
      rGroup.add(rButton[i]);
      north.add(rButton[i]);
      label[i] = new JLabel(data[i]);
    }
    this.add(north, BorderLayout.NORTH);
    this.add(center, BorderLayout.CENTER);

  }

  class rButtonListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      int i = -1;
      for (int j = 0; j < data.length; j++) {
        if (e.getSource() == rButton[j]) {
          i = j;
          break;
        }
      }
      if (i >= 0) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          center.add(label[i]);
        } else {
          center.remove(label[i]);
        }
      }
      center.revalidate();
      center.repaint();
    }
  }

}