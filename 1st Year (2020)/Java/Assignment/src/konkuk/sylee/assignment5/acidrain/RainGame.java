/*
 * Copyright (c) 2020 SeungyunLee
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of the copyright holders nor the
 *    names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 */

package konkuk.sylee.assignment5.acidrain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import konkuk.sylee.assignment5.ui.MainUI;
import konkuk.sylee.assignment5.utils.UITool;
import konkuk.sylee.assignment5.word.VocManager;

public class RainGame extends JFrame {

  final VocManager voc;
  final MainUI mUI;
  RainEngine re;

  // UI Component Variables declaration
  JPanel game;
  JTextField input;
  JLabel score;
  JLabel life;
  JLabel diff;
  ButtonGroup bg;
  JRadioButton[] diffs;
  JButton start;
  // End of UI Component Variables declaration

  public RainGame(VocManager voc, MainUI mUI) {
    super("202011339 이승윤");
    this.voc = voc;
    this.mUI = mUI;
    init();
    initUI();
  }

  private void init() {
    this.setSize(800, 600);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        e.getWindow().setVisible(false);
        e.getWindow().dispose();
        mUI.setVisible(true);
        if (re != null && re.isRunning) {
          re.interrupt(); // RainEngine 종료
          re.isRunning = false;
        }
      }
    });
    this.setVisible(true);
  }

  private void initUI() {
    // 게임 패널 생성
    game = new JPanel(null) {
      @Override
      public void paint(Graphics g) {
        super.paint(g);
        // 판정선 그리기
        g.setColor(Color.BLACK);
        g.drawLine(0, 500, 800, 500);
      }
    }; // 게임 페널

    // 입력 부분 구현
    input = new JTextField();
    input.setBounds(330, 510, 140, 35);
    input.setHorizontalAlignment(JTextField.CENTER);
    game.add(input);
    input.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          re.checkRain(input.getText());
          input.setText("");
        }
        super.keyPressed(e);
      }
    });

    // 점수 & 생명
    score = new JLabel("점수 : 0");
    score.setBounds(650, 10, 80, 20);
    game.add(score);
    life = new JLabel("생명 3 / 3");
    life.setBounds(650, 30, 80, 20);
    game.add(life);

    // 난이도 선택 (상 중 하)
    diff = new JLabel("난이도를 선택하세요 (1:하 2:중 3:상)");
    diff.setBounds(10, 10, 200, 20);
    game.add(diff);
    bg = new ButtonGroup();
    diffs = new JRadioButton[3];
    for (int i = 0; i < 3; i++) {
      diffs[i] = new JRadioButton(Integer.toString(i + 1));
      diffs[i].setBounds(10 + 40 * i, 30, 40, 30);
      game.add(diffs[i]);
      bg.add(diffs[i]);
    }
    diffs[0].setSelected(true);

    // 시작 버튼
    start = new JButton("게임 시작");
    start.setBounds(220, 10, 160, 50);
    start.addActionListener((e) -> this.startGame());
    game.add(start);

    this.add(game);
  }

  private void startGame() {
    String ease = UITool.getSelectedButtonText(bg);
    // 난이도 선택 및 시작 버튼 숨기기
    start.setVisible(false);
    for (JRadioButton diff : diffs) {
      diff.setVisible(false);
    }
    diff.setVisible(false);
    re = new RainEngine(this, Integer.parseInt(ease));
    re.start();
  }

  public void reStart() {
    JOptionPane.showMessageDialog(null, score.getText(), "게임 종료!",
        JOptionPane.INFORMATION_MESSAGE);
    this.remove(game);
    this.initUI();
    this.revalidate();
    this.repaint();
  }

  public void update() {
    score.setText("점수 : " + re.score);
    life.setText("생명 " + re.life + " / 3");
  }

}
