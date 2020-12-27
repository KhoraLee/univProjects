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

import java.util.HashSet;

public class StringTool {

  /**
   * 읽은 문장에서 가능한 구분자들을 반환함
   *
   * @param string 읽은 문장
   * @return 구분자들의 HashSet
   */
  public static HashSet<String> getDelimiter(String string) {
    HashSet<String> res = new HashSet<>();

    String tmp = string.trim();
    tmp = tmp.replaceAll("[a-zA-Z가-힣]", "");

    for (int i = 0; i < tmp.length(); i++) {
      res.add(tmp.substring(i, i + 1));
    }
    return res;
  }

}
