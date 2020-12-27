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
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Map.Entry;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import konkuk.sylee.assignment5.ui.components.WordCard;
import konkuk.sylee.assignment5.word.VocManager;
import konkuk.sylee.assignment5.word.Word;

public class FreqWordTabUI extends JPanel {

  final VocManager voc;

  public FreqWordTabUI(VocManager voc) {
    this.voc = voc;
    init();
  }

  public void init() {
    this.setLayout(new BorderLayout());
    ArrayList<Entry<Word, Integer>> freqWord = voc.getFreqWord();
    Dimension wordDim = new Dimension((int) this.getSize().getWidth() - 50, 50);

    JPanel tmp = new JPanel(new GridLayout(Math.max(freqWord.size(), 11), 1));
    freqWord.forEach((f) -> {
      Word wTmp = f.getKey();
      WordCard wc = new WordCard(
          new Word(wTmp.getEng(), wTmp.getKor() + "  /  " + f.getValue() + "회"));
      wc.setPreferredSize(wordDim);
      tmp.add(wc);
    });
    JScrollPane res = new JScrollPane(tmp,
        freqWord.size() > 11 ? JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
            : JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    this.add(res, BorderLayout.CENTER);
  }
}
