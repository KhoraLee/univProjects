package konkuk.sylee0408;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.util.concurrent.Flow;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SyleeFrame extends JFrame {

  private SyleeVendingMachine vm;
  private JPanel panel;

  public SyleeFrame(String title) {
    super(title);
    this.setSize(300, 200);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
    initUI();
  }

  private void initUI() {
    Container container = this.getContentPane();
    container.setLayout(new BorderLayout());
    JPanel buttonPanel = new JPanel(new FlowLayout());
    JButton createVM = new JButton("VM생성");
    createVM.addActionListener((e) -> {
      JFileChooser jfc = new JFileChooser();
      jfc.setFileFilter(new FileNameExtensionFilter("Text 파일", "txt"));
      jfc.setAcceptAllFileFilterUsed(true);
      int code = jfc.showOpenDialog(null);
      if (code != JFileChooser.APPROVE_OPTION) {
        JOptionPane.showMessageDialog(null, "파일을 선택하지 VM을 생성하지 않습니다.", "Error",
            JOptionPane.WARNING_MESSAGE);
        return;
      }
      if (panel != null) {
        container.remove(panel);
      }
      vm = FinalExamSylee0408.readFile(jfc.getSelectedFile().getAbsolutePath());
      JPanel tmp = getVM();
      container.add(getVM());
      this.revalidate();
      this.repaint();
    });

    JButton creator = new JButton("만든이");
    creator.addActionListener((e) -> {
      JDialog j = new JDialog(this, "만든이", true);
      j.setSize(200, 200);
      j.setLocationRelativeTo(null);
      JLabel img = new JLabel();
      j.add(img, BorderLayout.CENTER);
      ImageIcon icon = new ImageIcon("null.gif");
      Image i = icon.getImage();
      img.setIcon(new ImageIcon(i.getScaledInstance(180, 180, Image.SCALE_DEFAULT)));
      j.pack();
      JLabel sid = new JLabel("202011339 이승윤");
      sid.setHorizontalAlignment(JLabel.CENTER);
      j.add(sid, BorderLayout.SOUTH);
      j.setVisible(true);
      j.pack();
    });
    JLabel total = new JLabel("총 판매금액:0원");
    total.setHorizontalAlignment(JLabel.CENTER);
    buttonPanel.add(createVM);
    buttonPanel.add(creator);
    container.add(buttonPanel, BorderLayout.NORTH);
    container.add(total, BorderLayout.SOUTH);

  }

  private JPanel getVM() {
    panel = new JPanel(new GridLayout(3, 2));
    JLabel[] items = new JLabel[vm.getItems().size()];
    JButton[] buyButtons = new JButton[vm.getItems().size()];
    for (int i = 0; i < vm.getItems().size(); i++) {
      if (vm.getItems().get(i) instanceof SyleeCoffee){
        SyleeCoffee coffee  = (SyleeCoffee)vm.getItems().get(i);
        items[i] = new JLabel(vm.getItems().get(i).getName()+"("+coffee.getCountry()+")");
      } else if (vm.getItems().get(i) instanceof SyleeSnack){
        SyleeSnack snack  = (SyleeSnack)vm.getItems().get(i);
        items[i] = new JLabel(vm.getItems().get(i).getName()+"("+snack.getCalorie()+")");
      }
      buyButtons[i] = new JButton("판매중");
      panel.add(items[i]);
      panel.add(buyButtons[i]);
      if (vm.getItems().get(i).getNumber() == 0){
        buyButtons[i].setText("매진");
        buyButtons[i].setBackground(Color.RED);
      }
    }
    return panel;
  }
}
