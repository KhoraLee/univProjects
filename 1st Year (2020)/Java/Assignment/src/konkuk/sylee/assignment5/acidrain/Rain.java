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

import javax.swing.JLabel;

public class Rain extends Thread {

  JLabel word;
  RainEngine rainEngine;

  public Rain(JLabel word, RainEngine rainEngine) {
    this.word = word;
    this.rainEngine = rainEngine;
  }

  @Override
  public void run() {
    boolean flag = true;
    if (!rainEngine.isRunning) {
      flag = false;
    }
    while (flag) {
      if (word.isVisible() && word.getY() <= 460) {
        int cx = word.getX();
        int cy = word.getY();
        word.setLocation(cx, cy + 5 * (1 + rainEngine.ease));
      } else if (word.isVisible() && word.getY() >= 460) {
        word.setVisible(false);
        word.setText("");
        rainEngine.life--;
        flag = false;
      }
      rainEngine.rainGame.update();
      try {
        sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }

  }

}
