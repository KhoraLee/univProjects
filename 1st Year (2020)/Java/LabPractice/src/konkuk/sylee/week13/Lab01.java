package konkuk.sylee.week13;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Lab01 {

  public static void main(String[] args) {
    UI ui = new UI();
  }

}

class UI extends JFrame {

  Container c = this.getContentPane();
  JPanel panel = new JPanel();
  MyLabel label = new MyLabel();
  JButton btn1, btn2;

  public UI() {
    super("202011339 이승윤");
    this.setSize(500, 500);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    init();
    this.setVisible(true);
  }

  private void init() {
    JFileChooser jfc = new JFileChooser();
    jfc.setFileFilter(new FileNameExtensionFilter("gif Images", "gif"));
    jfc.setFileFilter(new FileNameExtensionFilter("jpeg Images", "jpg", "jpeg"));
    jfc.setAcceptAllFileFilterUsed(false);

    btn1 = new JButton("불러오기");
    btn1.addActionListener((e) -> {
      int res = jfc.showOpenDialog(null);
      if (res == JFileChooser.APPROVE_OPTION) {
        File imgFile = jfc.getSelectedFile();
        label.setIcon(new ImageIcon(imgFile.getPath()));
        this.pack();
      }
    });
    btn2 = new JButton("저장하기");
    btn2.addActionListener((e) -> {
      int res = jfc.showSaveDialog(null);
      if (res == JFileChooser.APPROVE_OPTION) {
        BufferedImage img = new BufferedImage(label.getWidth(), label.getHeight(),
            BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) img.getGraphics();
        label.printAll(g2);
        g2.dispose();
        File f = jfc.getSelectedFile();
        FileNameExtensionFilter filter = (FileNameExtensionFilter)jfc.getFileFilter();
        String ext = filter.getExtensions()[0];
        if(!filter.accept(f)){
          String fileName= f.getName();
          f = new File(f.getParent(),fileName+"."+ ext);
        }
        try {
          ImageIO.write(img, ext, f);
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
      }

    });
    panel.add(btn1);
    panel.add(btn2);
    c.add(panel, BorderLayout.NORTH);
    c.add(label, BorderLayout.CENTER);
  }

}

class MyLabel extends JLabel {

  Point pt = new Point(-100, -100);

  public MyLabel() {
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        pt = e.getPoint();
        repaint();
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.ORANGE);
    g.fillOval(pt.x - 20, pt.y - 20, 40, 40);
  }
}

