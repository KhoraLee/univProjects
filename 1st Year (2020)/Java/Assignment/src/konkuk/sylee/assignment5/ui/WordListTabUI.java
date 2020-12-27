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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import konkuk.sylee.assignment5.ui.components.WordCard;
import konkuk.sylee.assignment5.utils.TTS;
import konkuk.sylee.assignment5.word.VocManager;

public class WordListTabUI extends JPanel {

  private final VocManager voc;

  public WordListTabUI(VocManager voc) {
    this.voc = voc;
    init();
  }

  private void init() {

    Dimension wordDim = new Dimension((int) this.getSize().getWidth() - 50, 50);

    JPanel wordList = new JPanel(
        new GridLayout(voc.getWords().size() > 12 ? voc.getWords().size() : 11, 1));
    JScrollPane wordScrollList = new JScrollPane(wordList);
    voc.getWords().forEach((w) -> {
      WordCard wc = new WordCard(w);
      wc.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          Thread tts = new Thread(() -> {
            if (!TTS.isPlaying) {
              TTS.playGoogleTTS(wc.getWord().getEng());
            }
          });
          tts.start();
        }
      });
      wc.setPreferredSize(wordDim);
      wordList.add(wc);
    });
    this.setLayout(new BorderLayout());
    this.add(wordScrollList, BorderLayout.CENTER);
  }
}
