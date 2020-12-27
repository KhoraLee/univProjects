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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import konkuk.sylee.assignment5.ui.components.WordCard;
import konkuk.sylee.assignment5.word.VocManager;
import konkuk.sylee.assignment5.word.Word;

public class IncorrectAnswerNoteTabUI extends JPanel {

  final VocManager voc;
  final MainUI mUI;
  final QuizTabUI quizUI;

  public IncorrectAnswerNoteTabUI(VocManager voc, MainUI mUI, QuizTabUI quizUI) {
    this.voc = voc;
    this.mUI = mUI;
    this.quizUI = quizUI;
    init();
  }

  private void init() {
    this.setLayout(new BorderLayout());
    JButton reTest = new JButton("시험보기");
    reTest.addActionListener((e)->{
      if (voc.getWrongWord().size() == 0){
        JOptionPane.showMessageDialog(null, "오답노트에 단어가 없어 재시험을 이용할 수 없습니다.", "재시험 불가능",
            JOptionPane.INFORMATION_MESSAGE);
        return;
      }
      mUI.moveTab(3);
      quizUI.quizP(voc.makeUpQuiz());
    });
    JButton reset = new JButton("초기화");
    reset.addActionListener((e) -> {
      voc.resetWrongWord();
      mUI.ian();
    });
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(reTest);
    buttonPanel.add(reset);
    this.add(buttonPanel, BorderLayout.SOUTH);

    HashSet<Word> wrongWord = voc.getWrongWord();

    Dimension wordDim = new Dimension((int) this.getSize().getWidth() - 50, 50);

    JPanel tmp = new JPanel(new GridLayout(Math.max(wrongWord.size(), 13), 1));
    wrongWord.forEach((w) -> {
      WordCard wc = new WordCard(w);
      wc.setPreferredSize(wordDim);
      tmp.add(wc);
    });
    JScrollPane res = new JScrollPane(tmp,
        wrongWord.size() > 13 ? JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
            : JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    this.add(res, BorderLayout.CENTER);
  }

}
