package konkuk.sylee.week09;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Lab02 {

  public static void main(String[] args) {
    UI ui = new UI();
  }
}

class UI extends JFrame {

  //character components
  private int xpos = 100, ypos = 100;
  private int imgWidth, imgHeight;

  //UI Components
  private JPanel controller;
  private JPanel movement;
  private JButton up;
  private JButton down;
  private JButton left;
  private JButton right;
  private JLabel character;

  public UI() {
    super("202011339 이승윤");
    this.setSize(700, 500);
    this.setLocation(400, 400);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.init();
    this.setVisible(true);
  }

  private void init() {
    Container container = this.getContentPane();
    BorderLayout bl = (BorderLayout) container.getLayout();
    this.controllerPanel(); // Button Panel Init
    this.movementPanel(); // Movement Panel Init
    container.add(controller, BorderLayout.WEST);
    container.add(movement, BorderLayout.CENTER);
  }

  private void controllerPanel() {
    controller = new JPanel();
    up = new JButton("△");
    down = new JButton("▽");
    right = new JButton("▷");
    left = new JButton("◁");
    controller.setLayout(new GridLayout(4, 1, 5, 5));

    controller.add(up);
    controller.add(down);
    controller.add(left);
    controller.add(right);
    up.addActionListener(new btnControllListener(btnControllListener.UP));
    down.addActionListener(new btnControllListener(btnControllListener.DOWN));
    left.addActionListener(new btnControllListener(btnControllListener.LEFT));
    right.addActionListener(new btnControllListener(btnControllListener.RIGHT));

  }

  private void movementPanel() {
    movement = new JPanel();
    movement.setLayout(null);
    character = new JLabel();
    ImageIcon img = new ImageIcon("img2.png");
    imgWidth = img.getIconWidth();
    imgHeight = img.getIconWidth();
    character.setIcon(img);
    movement.add(character);
    character.setBounds(xpos, ypos, imgWidth, imgHeight);
  }

  class btnControllListener implements ActionListener {

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    private final int direction;

    public btnControllListener(int direction) {
      this.direction = direction;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      switch (direction) {
        case 0:
          if (ypos >= 15) {
            ypos -= 10;
          }
          break;

        case 1:
          if (ypos <= (movement.getHeight() - imgHeight - 10)) {
            ypos += 10;
          }
          break;

        case 2:
          if (xpos >= 15) {
            xpos -= 10;
          }
          break;

        case 3:
          if (xpos <= (movement.getWidth() - imgWidth - 10)) {
            xpos += 10;
          }
          break;
      }
      character.setBounds(xpos, ypos, imgWidth, imgHeight);
    }
  }

}

