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

package konkuk.sylee.assignment5.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class TTS {

  private static final String REST_API_KEY_KAKAO = "c0aa72495de4a3446e6d35d7d0c4a21d"; // Private API Key
  public static boolean isPlaying = false;

  /**
   * Kakao Rest API 를 이용한 TTS 구현
   *
   * @param text 음성으로 출력할 문자열
   */
  public static void playKakaoTTS(String text) {
    isPlaying = true;
    try {
      URL url = new URL("https://kakaoi-newtone-openapi.kakao.com/v1/synthesize");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/xml");
      conn.setRequestProperty("Authorization", REST_API_KEY_KAKAO);
      conn.setDoOutput(true);

      OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(),
          StandardCharsets.UTF_8);
      osw.write("<speak>" + text + "</speak>");
      osw.flush();
      osw.close();
      int responseCode = conn.getResponseCode();
      if (responseCode == 200) {
        InputStream is = conn.getInputStream();
        Player pl = new Player(is);
        pl.play();
      } else {
        System.out.println(responseCode + ": Error Occurred");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    isPlaying = false;
  }

  public static void playGoogleTTS(String text){
    isPlaying = true;
    GoogleTranslateTTS tts = new GoogleTranslateTTS("en-US");
    try {
      byte[] data = tts.getData(text);
      InputStream bis = new ByteArrayInputStream(data);
      Player pl = new Player(bis);
      pl.play();
    } catch (IOException | JavaLayerException e) {
      e.printStackTrace();
    }
    isPlaying = false;
  }


}


