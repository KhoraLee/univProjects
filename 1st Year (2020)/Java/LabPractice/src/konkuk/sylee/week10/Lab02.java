package konkuk.sylee.week10;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Lab02 {

  public static void main(String[] args) {
    UI02 ui = new UI02();
  }

}

class UI02 extends JFrame {

  String[] fonts = {"바탕체", "돋움체", "굴림체", "궁서체"};
  Font f;
  JComboBox<String> font;
  JComboBox<Integer> size;
  JCheckBox bold;
  JCheckBox italic;
  JTextField textInput;
  JLabel label;
  JPanel north = new JPanel();

  public UI02() {
    super("202011339 이승윤");
    this.setSize(500, 500);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.init();
    this.setVisible(true);
  }

  private void init() {
    f = new Font("바탕체", Font.PLAIN, 10);
    font = new JComboBox<>();
    size = new JComboBox<>();
    bold = new JCheckBox("Bold");
    italic = new JCheckBox("Italic");
    textInput = new JTextField(20);
    label = new JLabel();
    for (int i = 0; i < 9; i++) {
      size.addItem(i * 2 + 10);
    }
    for (String s : fonts) {
      font.addItem(s);
    }

    north.setLayout(new FlowLayout());
    north.add(font);
    north.add(size);
    north.add(bold);
    north.add(italic);
    north.add(textInput);
    label.setHorizontalAlignment(JLabel.CENTER);
    this.initListener();
    this.add(north, BorderLayout.NORTH);
    this.add(label, BorderLayout.CENTER);
  }

  private void initListener() {
    textInput.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        label.setText(textInput.getText());
        label.setFont(f);
      }
    });
    bold.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        f = new Font(f.getFontName(), f.getStyle() ^ 1, f.getSize());
        label.setFont(f);
      }
    });
    italic.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        f = new Font(f.getFontName(), f.getStyle() ^ 2, f.getSize());
        label.setFont(f);
      }
    });
    font.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        f = new Font(font.getSelectedItem().toString(), f.getStyle(), f.getSize());
        label.setFont(f);
      }
    });
    size.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        f = new Font(f.getFontName(), f.getStyle(),
            Integer.parseInt(size.getSelectedItem().toString()));
        label.setFont(f);
      }
    });
  }

}
