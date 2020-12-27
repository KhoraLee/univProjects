package konkuk.sylee.assignment5.ui;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import konkuk.sylee.assignment5.acidrain.RainGame;
import konkuk.sylee.assignment5.word.VocManager;


public class MainUI extends JFrame {

  private final VocManager voc;

  // UI Component Variables declaration
  private JPanel wordListP;
  private JPanel search1P;
  private JPanel search2P;
  private JPanel quizP;
  private JPanel freqWordP;
  private JPanel ianP;
  private JPanel rainGameP;
  private JTabbedPane tab;
  // End of UI Component Variables declaration

  public MainUI(VocManager voc) {
    super("202011339 이승윤");
    this.voc = voc;
    init();
  }

  /**
   * UI Initialize
   */
  private void init() {
    initMenu();
    this.setSize(400, 700);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
  }

  /**
   * UI상단에 위치한 메뉴를 생성과 추가
   */
  private void initMenu() {
    JMenuBar jmb = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu help = new JMenu("Help");

    // Initialize File Menu
    {
      JMenuItem open = new JMenuItem("Open");
      open.addActionListener((e) -> {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new FileNameExtensionFilter("Text 파일","txt"));
        jfc.setAcceptAllFileFilterUsed(false);
        int code = jfc.showOpenDialog(null);
        if (code != JFileChooser.APPROVE_OPTION) {
          JOptionPane.showMessageDialog(null, "파일을 선택하지 않아 단어장을 생성하지 않습니다", "Error",
              JOptionPane.WARNING_MESSAGE);
          return;
        }
        voc.makeVoc(jfc.getSelectedFile());
        initPanel();
        revalidate();
        repaint();
      });
      JMenuItem exit = new JMenuItem("Exit");
      exit.addActionListener((e) -> System.exit(0));
      file.add(open);
      file.addSeparator();
      file.add(exit);
      jmb.add(file);
    }

    // Initialize Help Menu
    {
      JMenuItem license = new JMenuItem("License");
      license.addActionListener((e) -> {
        JFrame licenseView = new JFrame("License");
        licenseView.setLocationRelativeTo(null);
        String[] header = {"Library", "License"};
        String[][] contents = {{"JLayer 1.0.1", "LGPL"},{"Google Translate TTS", "GNU GPL V3"}};
        JTable licenses = new JTable(contents, header);
        licenses.setEnabled(false);
        licenseView.add(new JScrollPane(licenses));
        licenseView.setSize(350, 150);
        licenseView.setVisible(true);
        licenseView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      });
      help.add(license);
      jmb.add(help);
    }

    this.setJMenuBar(jmb);
  }

  /**
   * UI를 구성하기 위한 Component들의 생성과 추가
   */
  private void initPanel() {
    if (tab != null) {
      this.remove(tab);
    }
    tab = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

    // Tab Component Initialize
    {
      wordList(); // 단어장 단어 보기
      search1(); // 검색 1
      search2(); // 검색 2
      quiz(); // 객관식 퀴즈
      freqWord(); // 빈출단어
      ian(); // 오답노트
      rainGame();
    }
    this.add(tab); // 메인 Panel 에 Tab 추가
  }

  // Methods that Initialize Tab Component
  private void wordList() {
    wordListP = new WordListTabUI(voc);
    this.addTab(0, wordListP, "단어");
  }

  private void search1() {
    search1P = new Search1TabUI(voc);
    this.addTab(1, search1P, "검색 1");
  }

  private void search2() {
    search2P = new Search2TabUI(voc);
    this.addTab(2, search2P, "검색 2");
  }

  private void quiz() {
    quizP = new QuizTabUI(voc, this);
    this.addTab(3, quizP, "객관식 퀴즈");
  }

  private void rainGame(){
    rainGameP = new JPanel(null);
    JButton start = new JButton("산성비 시작하기");
    start.addActionListener((e)->{
      this.setVisible(false);

      new RainGame(voc,this);
    });
    start.setBounds(90, 50, 200, 40);
    rainGameP.add(start);
    this.addTab(6,rainGameP,"산성비");
  }

  /*
   * QuizTabUI에서 객관식 퀴즈가 끝난 후 빈출단어와 오답노트탭의 내용을 갱신하기위해 QuizTabUI에서 호출을
   * 할 필요가 있기에 freqWord()와 ian()은 public으로 생성함
   */
  public void freqWord() {
    freqWordP = new FreqWordTabUI(voc);
    this.addTab(4, freqWordP, "빈출 단어");
  }

  public void ian() {
    ianP = new IncorrectAnswerNoteTabUI(voc, this,(QuizTabUI) quizP);
    this.addTab(5, ianP, "오답 노트");
  }

  // End of Methods that Initialize Tab Component

  /**
   * JComponent를 받아 index에 추가를 시도 실패시 맨 끝에 추가
   *
   * @param index      추가하고자 하는 위치
   * @param jcomponent 추가하려는 JCompoent
   * @param title      탭 이름
   */
  private void addTab(int index, JComponent jcomponent, String title) {
    try {
      tab.setComponentAt(index, jcomponent);
    } catch (Exception e) {
      tab.addTab(title, jcomponent);
    }
  }

  public JTabbedPane getTab(){
    return this.tab;
  }

  public void moveTab(int index){
    tab.setSelectedIndex(index);
  }

}
