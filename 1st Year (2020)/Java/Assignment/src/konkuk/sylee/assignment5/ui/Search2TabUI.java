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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import konkuk.sylee.assignment5.ui.components.WordCard;
import konkuk.sylee.assignment5.word.VocManager;
import konkuk.sylee.assignment5.word.Word;

public class Search2TabUI extends JPanel {
  private final VocManager voc;

  public Search2TabUI(VocManager voc) {
    this.voc = voc;
    init();
  }

  private void init() {
    Dimension wordDim = new Dimension((int) this.getSize().getWidth() - 50, 50);

    JTextField searchInput = new JTextField();
    JButton searchButton = new JButton("Search");
    JPanel searchbar = new JPanel(new FlowLayout());
    searchInput.setPreferredSize(new Dimension(280, 20));
    searchButton.setPreferredSize(new Dimension(80, 20));
    searchbar.add(searchInput);
    searchbar.add(searchButton);

    this.setLayout(new BorderLayout());
    this.add(searchbar, BorderLayout.NORTH);

    searchButton.addActionListener((e) -> {
      ArrayList<Word> result = voc.searchVoc2(searchInput.getText());
      if (result.size() != 0) {
        this.removeAll();
        this.add(searchbar, BorderLayout.NORTH);
        JPanel tmp = new JPanel(new GridLayout(Math.max(result.size(), 11), 1));
        result.forEach((w) -> {
          WordCard wc = new WordCard(w, searchInput.getText());
          wc.setPreferredSize(wordDim);
          tmp.add(wc);
        });
        JScrollPane res = new JScrollPane(tmp,
            result.size() > 11 ? JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                : JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(res, BorderLayout.CENTER);
      }
      this.revalidate();
      this.repaint();
    });
  }
}
