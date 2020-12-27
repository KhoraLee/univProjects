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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javax.swing.JLabel;
import konkuk.sylee.assignment5.word.Word;

public class RainEngine extends Thread {

  int score;
  int life = 3;
  int ease;
  int totalRain;
  int wordTotal;
  JLabel[] rain;
  RainGame rainGame;
  Queue<Word> wordQueue;
  boolean isRunning = true;

  public RainEngine(RainGame rainGame, int ease) {
    this.rainGame = rainGame;
    this.ease = ease;
    this.wordTotal = rainGame.voc.getWords().size();
    ArrayList<Word> tmp = rainGame.voc.getWords();
    Collections.shuffle(tmp); // 단어 셔플
    wordQueue = new LinkedList<>(tmp);
  }

  public void start() {
    rain = new JLabel[wordTotal];
    Thread t = new Thread(this);
    t.start();
  }

  @Override
  public void run() {
    try {
      while (life > 0) {
        if (totalRain < wordTotal) {
          newRain();
          Thread.sleep(2500);
        } else if (totalRain == wordTotal) {
          boolean flag = false;
          for (JLabel l : rain) {
            if (l.isVisible()) {
              flag = true;
              break;
            }
          }
          if (!flag) {
            Thread.sleep(1000); // 단어 확인 처리중이라면 먼저 끝내면 안되기에 sleep
            break;
          }
        }
      }
      rainGame.update();
      gameOver(); // 생명력이 소진됬거나 모든 단어를 맞췄음으로 게임 종료
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  public void gameOver() {
    this.interrupt();
    isRunning = false;
    for (JLabel r : rain) {
      if (r != null) {
        r.setVisible(false);
      }
    }
    rain = null;
    life = 3;
    score = 0;
    rainGame.reStart();
  }

  public void newRain() {
    Random ran = new Random();
    rain[totalRain] = new JLabel(wordQueue.poll().getEng());
    rain[totalRain].setBounds(ran.nextInt(550), 0, 100, 20);
    rainGame.game.add(rain[totalRain]);
    Rain r = new Rain(rain[totalRain++], this);
    r.start();
  }

  public void checkRain(String input) {
    for (JLabel r : rain) {
      if (r != null && r.isVisible()) {
        Word tmp = rainGame.voc.searchVoc(r.getText());
        if (tmp != null && tmp.getKor().equals(input)) {
          r.setVisible(false);
          score++;
          break;
        }
      }
    }
    rainGame.update();
  }
}
