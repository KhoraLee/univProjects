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

package konkuk.sylee.assignment5;

import java.net.InetAddress;
import java.net.UnknownHostException;
import konkuk.sylee.assignment5.word.VocManager;
import konkuk.sylee.assignment5.ui.MainUI;

public class Main {

  public static void main(String[] args) {
    VocManager voc;
    try {
      voc = new VocManager(InetAddress.getLocalHost().getHostName());
    } catch (UnknownHostException e) {
      voc = new VocManager("유저");
    }
    new MainUI(voc);
  }


}
