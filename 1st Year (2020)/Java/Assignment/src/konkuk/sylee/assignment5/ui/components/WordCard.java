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

package konkuk.sylee.assignment5.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.JComponent;
import konkuk.sylee.assignment5.word.Word;

public final class WordCard extends JComponent {

  private static final Rectangle viewRect = new Rectangle();
  private static Dimension size = new Dimension();
  private final Word word;
  private boolean f_custom = false;
  private String highlight;
  private Color highlightColor;


  public WordCard(Word word, Font font) {
    this.word = word;
    setFont(font);
    f_custom = true;
  }

  public WordCard(Word word, String highlight) {
    this.word = word;
    this.highlight = highlight;
    this.highlightColor = Color.RED;
  }

  public WordCard(Word word, String highlight, Color highlightColor) {
    this.word = word;
    this.highlight = highlight;
    this.highlightColor = highlightColor;
  }

  public WordCard(Word word) {
    this.word = word;
  }

  public Word getWord() {
    return word;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g); // To avoid repaint issue
    int arc = 25;

    Graphics2D g2 = (Graphics2D) g;
    JComponent c = this;
    Font f = new Font("나눔고딕", Font.PLAIN, 12);
    Insets i = c.getInsets();

    size = c.getSize(size);
    viewRect.x = i.left;
    viewRect.y = i.top;
    viewRect.width = size.width - (i.right + viewRect.x);
    viewRect.height = size.height - (i.bottom + viewRect.y);
    if (!f_custom) {
      f = new Font(f.getFontName(), f.getStyle(), (int) (viewRect.height / 3.5));
      g2.setFont(f);
    }
    // 배경 체우기
    g2.setColor(Color.WHITE);
    g2.fillRoundRect(3, 3, size.width - 10, size.height - 10, arc, arc);

    //텍스트 출력
    FontMetrics fm = g.getFontMetrics(g.getFont());
    int x_e = 15; //고정값
    int y = ((viewRect.height - fm.getHeight()) / 2) + fm.getHeight() / 2 + 4;
    int x_k = viewRect.width - fm.stringWidth(word.getKor()) - (int)(x_e*1.5);

    g2.setColor(Color.BLACK);

    if (highlight != null) {
      String eng = word.getEng();
      int index = eng.toUpperCase().indexOf(highlight.toUpperCase()); // 일치하는 부분 시작 위치
      int prevSize = 0; // 이전 글자의 크기
      int cur = 0; // 현재 읽고있는 글자 위치

      for (int j = 0; j < eng.length(); j++) {
        if (j >= index && j < index + highlight.length()) {
          g2.setColor(highlightColor);

        } else {
          g2.setColor(Color.BLACK);
        }
        g2.drawString(
            String.valueOf(eng.charAt(j)), x_e + 5 + prevSize,
            y);
        prevSize += fm.stringWidth(String.valueOf(eng.charAt(j)));
        cur++;
      }

    } else {
      g2.setColor(Color.BLACK);
      g2.drawString(word.getEng(), x_e + 5, y);
    }

    g2.setColor(Color.BLACK);
    g2.drawString(word.getKor(), x_k, y);

  }
}
