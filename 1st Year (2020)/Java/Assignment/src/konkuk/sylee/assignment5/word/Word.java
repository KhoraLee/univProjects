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

package konkuk.sylee.assignment5.word;

public class Word {

  private final String eng;
  private final String kor;

  public Word(String eng, String kor) {
    this.eng = eng;
    this.kor = kor;
  }

  public String getEng() {
    return eng;
  }

  public String getKor() {
    return kor;
  }

  @Override
  public String toString() {
    return eng + " : " + kor;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Word) {
      return ((Word) obj).eng.equals(eng) && ((Word) obj).kor.equals(kor);
    } else {
      return super.equals(obj);
    }
  }

  @Override
  public int hashCode() {
    return eng.hashCode() + kor.hashCode();
  }
}
