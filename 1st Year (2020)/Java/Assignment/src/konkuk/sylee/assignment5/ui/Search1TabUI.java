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
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import konkuk.sylee.assignment5.ui.components.WordCard;
import konkuk.sylee.assignment5.word.VocManager;
import konkuk.sylee.assignment5.word.Word;

public class Search1TabUI extends JPanel {

  private final VocManager voc;

  public Search1TabUI(VocManager voc) {
    this.voc = voc;
    init();
  }

  private void init() {

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
      Word result = voc.searchVoc(searchInput.getText());
      if (result != null) {
        this.removeAll();
        this.add(searchbar, BorderLayout.NORTH);
        WordCard wc = new WordCard(result);
        JPanel res = new JPanel(null);
        wc.setBounds(5, 10, (int) this.getSize().getWidth() - 10, 50);
        res.add(wc);
        this.add(res, BorderLayout.CENTER);
      }
      this.revalidate();
      this.repaint();
    });
  }
}
