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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.plaf.basic.BasicRadioButtonUI;

public class CardRadioButton extends JRadioButton {

  public CardRadioButton(String text) {
    this(text, Color.PINK);
  }

  public CardRadioButton(String text, Color selected) {
    super(text);
    this.setUI(new CardRadioButtonUI(selected));
  }
}

class CardRadioButtonUI extends BasicRadioButtonUI {

  private static final Rectangle viewRect = new Rectangle();
  private static Dimension size = new Dimension();
  private final Color selected;


  public CardRadioButtonUI(Color selected) {
    this.selected = selected;
  }

  @Override
  public synchronized void paint(Graphics g, JComponent c) {
    int arc = 25;
    Graphics2D g2 = (Graphics2D) g;
    AbstractButton b = (AbstractButton) c;
    ButtonModel model = b.getModel();

    Font f = c.getFont();
    g2.setFont(f);
    FontMetrics fm = g.getFontMetrics(c.getFont());
    String text = b.getText();

    Insets i = c.getInsets();
    size = b.getSize(size);
    viewRect.x = i.left;
    viewRect.y = i.top;
    viewRect.width = size.width - (i.right + viewRect.x);
    viewRect.height = size.height - (i.bottom + viewRect.y);

    // fill background
    if (c.isOpaque()) {
      g2.setColor(b.getBackground());
      g2.setColor(new Color(255, 255, 255, 255));
      g2.fillRoundRect(3, 3, size.width - 10, size.height - 10, arc, arc);
      g2.setColor(new Color(0, 0, 0, 80));
      g2.setStroke(new BasicStroke(3));
      g2.drawRoundRect(3, 3, size.width - 10, size.height - 10, arc, arc);
    }

    // 선택 됬을때 태두리 색 변경
    if (model.isSelected()) {
      g2.setColor(selected);
      g2.setStroke(new BasicStroke(3));
      g2.drawRoundRect(3, 3, size.width - 10, size.height - 10, arc, arc);
    }

    // Draw the Text
    if (text != null) {
      // 가운데 정렬
      int x = viewRect.x + (viewRect.width - fm.stringWidth(text)) / 2;
      int y = viewRect.y + ((viewRect.height - fm.getHeight()) / 2) + fm.getHeight() / 2 + 3;

      g2.setColor(Color.BLACK);
      g2.drawString(text, x, y);
    }
  }

}
