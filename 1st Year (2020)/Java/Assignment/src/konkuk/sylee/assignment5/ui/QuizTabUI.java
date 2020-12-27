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

package konkuk.sylee.assignment5.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import konkuk.sylee.assignment5.ui.components.CardRadioButton;
import konkuk.sylee.assignment5.ui.components.WordCard;
import konkuk.sylee.assignment5.utils.UITool;
import konkuk.sylee.assignment5.word.VocManager;
import konkuk.sylee.assignment5.word.Word;

public class QuizTabUI extends JPanel {

  final MainUI mUI;
  final VocManager voc;
  final String[] answerSheet = new String[10];
  HashMap<Word, HashSet<String>> quiz;
  int current;
  Iterator<Word> it;

  public QuizTabUI(VocManager voc, MainUI mUI) {
    this.mUI = mUI;
    this.voc = voc;
    initP();
  }

  private void initP() {
    this.setLayout(null);
    JButton startButton = new JButton("객관식 퀴즈 시작하기");
    startButton.setBounds(90, 50, 200, 40); // 중간 지점에 버튼 생성
    this.add(startButton);
    startButton.addActionListener(e -> quizP());
  }

  private void quizP() {
    quiz = voc.multipleChoiceQuiz();
    it = quiz.keySet().iterator();
    current = 0;
    this.removeAll();
    quizProblem();
  }

  public void quizP(HashMap<Word, HashSet<String>> quiz) {
    this.quiz = quiz;
    it = quiz.keySet().iterator();
    current = 0;
    this.removeAll();
    quizProblem();
  }

  private void quizProblem() {
    if (it.hasNext()) {
      Word key = it.next();
      HashSet<String> value = quiz.get(key);
      this.removeAll();
      this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
      this.add(new JLabel("다음 단어의 뜻은?"));
      WordCard wc = new WordCard(new Word(key.getEng(), "________"));
      wc.setPreferredSize(new Dimension(350, 70));
      this.add(wc);
      // 선택지 생성
      ButtonGroup bg = new ButtonGroup();
      CardRadioButton[] buttons = new CardRadioButton[4];

      for (int i = 0; i < 4; i++) {
        Random r = new Random();
        ArrayList<String> arr = new ArrayList<>(value);
        int index = r.nextInt(4);
        if (buttons[index] == null) {
          buttons[index] = new CardRadioButton(arr.get(i));
          buttons[index].setPreferredSize(new Dimension(350, 70));
          buttons[index].setFont(new Font("맑은고딕", Font.PLAIN, 18));
          bg.add(buttons[index]);
        } else {
          i--;
        }
      }
      for (CardRadioButton crb : buttons) {
        this.add(crb);
      }

      JButton nextB = new JButton("다음 문제로");
      nextB.addActionListener((e) -> {
        String ans = UITool.getSelectedButtonText(bg);
        if (ans != null && it.hasNext()) {
          answerSheet[current++] = ans;
          quizProblem();
        } else if (ans != null && current == quiz.size()-1) {
          answerSheet[current] = ans;
          resP();
        }
      });
      this.add(nextB);
      revalidate();
      repaint();
    }
  }

  private void resP() {
    int score = 0;
    HashMap<Word, Boolean> scoring = scoring();
    Collection<Boolean> s = scoring.values();
    Boolean[] a = s.toArray(new Boolean[0]);
    for (boolean b : a) {
      if (b) {
        score++;
      }
    }
    this.removeAll();
    JLabel jScore = new JLabel("점수 : " + score + "/" + quiz.size() + " 점", null, JLabel.CENTER);
    jScore.setPreferredSize(new Dimension(350, 20));
    this.add(jScore);
    JButton restart = new JButton("다시 시작하기");
    restart.addActionListener((e) -> quizP());
    JButton ian = new JButton("오답노트로");
    ian.addActionListener((e)->mUI.moveTab(5));
    this.add(restart);
    this.add(ian);
    mUI.freqWord();
    mUI.ian();
    revalidate();
    repaint();
  }

  private HashMap<Word, Boolean> scoring() {
    HashMap<Word, Boolean> score = new HashMap<>();
    it = quiz.keySet().iterator();
    int count = 0;
    while (it.hasNext()) {
      Word key = it.next();
      if (key.getKor().equals(answerSheet[count++])) {
        score.put(key, true);
      } else {
        score.put(key, false);
        voc.addWrongWord(key);
      }
    }

    return score;
  }

}
