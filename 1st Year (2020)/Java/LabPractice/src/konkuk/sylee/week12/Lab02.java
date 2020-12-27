package konkuk.sylee.week12;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Lab02 {

  public static void main(String[] args) {
    UI02 ui = new UI02();
  }
}

class UI02 extends JFrame {

  Container c = this.getContentPane();
  GamePanel gamePanel = new GamePanel();

  public UI02() {
    super("202011339 이승윤");
    this.setSize(700, 500);
    this.setLocation(400, 400);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.init();
    this.setVisible(true);
  }

  private void init() {
    c.add(gamePanel);
    gamePanel.setFocusable(true);
    gamePanel.requestFocus();
  }


}

class GamePanel extends JPanel {

  final int AVATAR_MOVE = 10;
  String[] imgSource = {"img/Down.png", "img/Up.png", "img/Left.png", "img/Right.png",
      "img/monster.jpg"};
  ImageIcon[] icons = new ImageIcon[imgSource.length];
  JLabel avatar;
  JLabel monster;
  Point pos = new Point(50, 50);

  public GamePanel() {
    for (int i = 0; i < imgSource.length; i++) {
      icons[i] = new ImageIcon(imgSource[i]);
    }

    icons[4] = new ImageIcon(
        icons[4].getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)); // 이미지 축소

    avatar = new JLabel(icons[0]);

    this.setLayout(null);
    this.setBackground(Color.white);
    this.addKeyListener(new AvatarMove());
    avatar.setLocation(pos);
    avatar.setSize(icons[0].getIconWidth(), icons[0].getIconHeight());
    this.add(avatar);

    monster = new JLabel(icons[4]);
    monster.setLocation(250, 50);
    monster.setSize(icons[4].getIconWidth(), icons[4].getIconHeight());
    this.add(monster);
    MonsterThread t = new MonsterThread(avatar, monster);
    t.start();
    CollisionCheckThread ct = new CollisionCheckThread(t);
    ct.start();
  }

  class AvatarMove extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
      super.keyPressed(e);
      switch (e.getKeyCode()) {
        case KeyEvent.VK_DOWN:
          pos.y = pos.y + AVATAR_MOVE;
          avatar.setIcon(icons[0]);
          break;
        case KeyEvent.VK_UP:
          pos.y = pos.y - AVATAR_MOVE;
          avatar.setIcon(icons[1]);
          break;
        case KeyEvent.VK_LEFT:
          pos.x = pos.x - AVATAR_MOVE;
          avatar.setIcon(icons[2]);
          break;
        case KeyEvent.VK_RIGHT:
          pos.x = pos.x + AVATAR_MOVE;
          avatar.setIcon(icons[3]);
          break;
      }
      avatar.setLocation(pos);
      avatar.repaint();
    }
  }

  class MonsterThread extends Thread {

    final int MONSTER_MOVE = 5;
    JLabel avatar;
    JLabel monster;
    Point pos;
    boolean flag = true;

    public MonsterThread(JLabel avatar, JLabel monster) {
      this.avatar = avatar;
      this.monster = monster;
      this.pos = new Point(monster.getX(), monster.getY());
    }

    @Override
    public void run() {
      while (flag) {
        if (avatar.getX() < monster.getX()) {
          pos.x -= MONSTER_MOVE;
        } else {
          pos.x += MONSTER_MOVE;
        }
        if (avatar.getY() < monster.getY()) {
          pos.y -= MONSTER_MOVE;
        } else {
          pos.y += MONSTER_MOVE;
        }
        monster.setLocation(pos);
        monster.getParent().repaint();
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }


  }

  class CollisionCheckThread extends Thread {

    MonsterThread mt;

    public CollisionCheckThread(MonsterThread mt) {
      this.mt = mt;
    }

    @Override
    public void run() {
      while (true) {
        if (checkCollision()) {
          mt.flag = false;
          JOptionPane.showMessageDialog(null, "몬스터에게 잡혔습니다. 게임을 종료합니다.", "Game Over",
              JOptionPane.INFORMATION_MESSAGE);
          break;
        }
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    private boolean checkCollision() {
      Rectangle mRec = new Rectangle(monster.getX(), monster.getY(),
          monster.getIcon().getIconWidth(), monster.getIcon().getIconHeight());
      Rectangle aRec = new Rectangle(avatar.getX(), avatar.getY(),
          avatar.getIcon().getIconWidth(), avatar.getIcon().getIconHeight());
      return aRec.intersects(mRec);
    }
  }

}