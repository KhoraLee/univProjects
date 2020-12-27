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

package konkuk.sylee.assignment5.resource;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Pattern;
import konkuk.sylee.assignment5.utils.StringTool;
import konkuk.sylee.assignment5.word.Word;

public class ResourceManager {

  /**
   * 파일명을 받아 파일을 읽어 파일 객체를 반환
   *
   * @param fileName 파일명
   * @return 파일 객체
   */
  public static File openFile(String fileName) {
    File file = new File(fileName);
    if (file.exists()) {
      return file;
    } else {
      return null;
    }
  }

  /**
   * 문자열을 받아 Word 객체로 만들어서 반환 가능한 조합이 없다면 null 반환
   *
   * @param line 읽어들인 문자열
   * @return 생성된 Word 객체
   */
  public static Word readWord(String line) {
    String regexReserved = "[\\^\\$\\.\\*\\+\\?\\|\\[\\]\\{\\}\\(\\)]";

    HashSet<String> delimiterSet = StringTool.getDelimiter(line);
    Iterator<String> it = delimiterSet.iterator();
    HashMap<String, Word> res = new HashMap<>();

    while (it.hasNext()) {
      String delimiter = it.next();
      if (delimiter.matches(regexReserved) || line.contains("\\")) {
        int index = line.indexOf(delimiter);
        res.put(delimiter,
            new Word(line.substring(0, index).trim(), line.substring(index + 1).trim()));
      } else {
        try {
          res.put(delimiter, makeWord(line, delimiter, delimiterSet));
        } catch (Exception e) {
          System.err.println("정규식 오류로 건너뜀 cause : "+delimiter);
        }
      }
    }
    Iterator<String> itw = res.keySet().iterator();
    Word w_del_blank = null;
    while (itw.hasNext()) {
      String key = itw.next();
      if (key.equals(" ")) {
        w_del_blank = res.get(key);
      } else if (res.get(key) != null) {
        return res.get(key);
      }
    }
    return w_del_blank;
  }

  /**
   * 구분자를 바탕으로 단어 객체를 생성 만약 불가능하다면 null 반환
   *
   * @param line      문자열
   * @param delimiter 구분자
   * @return 생성된 단어 객체
   */
  private static Word makeWord(String line, String delimiter, HashSet<String> del) {
    StringBuilder regexEb = new StringBuilder("[a-zA-Z");
    StringBuilder regexKb = new StringBuilder("[가-힣");
    for (String deli : del) {
      regexEb.append(deli);
      regexKb.append(deli);
    }
    regexEb.append("]*");
    regexKb.append("]*");

    String regexE = regexEb.toString();
    String regexK = regexKb.toString();

    String[] spliced = line.split(delimiter);
    StringBuilder eng = new StringBuilder();
    StringBuilder eng_b = new StringBuilder();
    int cnt = 0;
    StringBuilder kor_b = new StringBuilder();

    for (String s : spliced) {
      if (Pattern.matches(regexE, eng.toString() + s)) {
        eng.append(s);
        eng_b.append(delimiter).append(s);
        cnt++;
      }
    }
    if (eng.toString().equals("")) {
      return null;
    } else {
      eng_b.deleteCharAt(0); // 맨 앞에 구분자 제거
      if (!Pattern.matches(regexK, spliced[cnt])) {
        return null;
      }
      kor_b.append(spliced[cnt]);
      for (int i = cnt + 1; i < spliced.length; i++) {
        kor_b.append(delimiter).append(spliced[i]);
      }
      return new Word(eng_b.toString().trim(), kor_b.toString().trim());
    }
  }
}
