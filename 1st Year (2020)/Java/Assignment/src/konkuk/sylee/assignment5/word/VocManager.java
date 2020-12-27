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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;
import konkuk.sylee.assignment5.resource.ResourceManager;

public class VocManager {

  //Fields
  private final String userName;
  private final HashSet<Word> voc = new HashSet<>();
  private final HashMap<Word, Integer> freqWord = new HashMap<>();
  private final HashSet<Word> wrongWord = new HashSet<>();
  //End of Fields declaration

  /**
   * 유저 이름을 받아서 initialize 하는 생성자
   *
   * @param userName 유저 이름
   */
  public VocManager(String userName) {
    this.userName = userName;
  }

  /**
   * VocManager 내부의 단어장에 단어를 추가하는 메소드
   *
   * @param word 단어장 배열 voc 에 추가하려는 Word(단어)
   */
  public void addWord(Word word) {
    voc.add(word);
  }

  /**
   * 객체 안에 있는 단어 Set을 List로 변환하여 반환
   *
   * @return 단어 List
   */
  public ArrayList<Word> getWords() {
    ArrayList<Word> a_voc = new ArrayList<>(voc);
    a_voc.sort(Comparator.comparing(Word::getEng));
    return a_voc;
  }

  /**
   * 단어장 파일로부터 단어 배열 만들기
   *
   * @param file 단어장 파일 객체
   */
  public void makeVoc(File file) {
    try (Scanner sc = new Scanner(file)) {
      while (sc.hasNextLine()) {
        Word tmp = ResourceManager.readWord(sc.nextLine());
        if (tmp != null) {
          this.addWord(tmp);
        }
      }
      JOptionPane.showMessageDialog(null, "단어장 불러오기에 성공하였습니다.", "불러오기 성공",
          JOptionPane.INFORMATION_MESSAGE);
    } catch (FileNotFoundException e) {
      System.out.println("파일을 찾을 수 없습니다."
          + "\n파일 이름을 확인해 주세요");
    }
  }

  /**
   * 입력과 정확히 일치하는 단어를 찾아 있다면 반환
   *
   * @param input 찾으려는 영단어
   * @return input과 일치하는 Word 객체
   */
  public Word searchVoc(String input) {
    for (Word word : voc) {
      if (word.getEng().equals(input.trim())) {
        return word;
      }
    }
    return null;
  }

  /**
   * 입력한 내용을 포함하는 단어를 찾아 그 List를 반환
   *
   * @param input 찾으려는 문자열
   * @return input을 포함하는 모든 Word 객체들의 List
   */
  public ArrayList<Word> searchVoc2(String input) {
    ArrayList<Word> result = new ArrayList<>();
    for (Word word : voc) {
      if (word.getEng().contains(input.trim())) {
        result.add(word);
      }
    }
    return result;
  }

  /**
   * 10개의 단어를 선택해 그 단어와 그외의 3개씩을 추가로 선택해 문제와 선택지로 선택될 단어들을 반환한다
   *
   * @return 객관식 퀴즈 문제
   */
  public HashMap<Word, HashSet<String>> multipleChoiceQuiz() {
    return makeQuiz(voc);
  }

  public HashMap<Word, HashSet<String>> makeUpQuiz() {
    return makeQuiz(wrongWord);
  }

  private HashMap<Word, HashSet<String>> makeQuiz(HashSet<Word> words) {
    HashMap<Word, HashSet<String>> quiz = new HashMap<>();
    Random r = new Random();
    ArrayList<Word> wordsList = new ArrayList<>(words);
    for (int i = 0; i < (Math.min(words.size(), 10)); i++) {
      Word tmp = wordsList.get(r.nextInt(wordsList.size()));
      if (quiz.containsKey(tmp)) {
        i--;
        continue;
      }
      HashSet<String> answerSet = new HashSet<>();
      answerSet.add(tmp.getKor());
      quiz.put(tmp, answerSet);
      freqWord.merge(tmp, 1, Integer::sum);
    }
    //나머지 선택지 채우기
    for (Word key : quiz.keySet()) {
      HashSet<String> answer = quiz.get(key);
      for (int i = 0; i < 3; i++) {
        ArrayList<Word> wordarr = new ArrayList<>(voc);
        Word tmp = wordarr.get(r.nextInt(wordarr.size()));
        if (answer.contains(tmp.getKor())) {
          i--;
          continue;
        }
        answer.add(tmp.getKor());
      }
      quiz.put(key, answer);
    }
    return quiz;
  }

  public ArrayList<Entry<Word, Integer>> getFreqWord() {
    ArrayList<Entry<Word, Integer>> sorting = new ArrayList<>(freqWord.entrySet());
    sorting.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()) * -1);
    return sorting;
  }

  public HashSet<Word> getWrongWord() {
    return wrongWord;
  }

  public void addWrongWord(Word word) {
    wrongWord.add(word);
  }

  public void resetWrongWord() {
    wrongWord.clear();
  }

  @Override
  public String toString() {
    StringBuilder tmp = new StringBuilder();
    for (Word word : voc) {
      tmp.append(word).append("\n");
    }
    return tmp.toString();
  }
}
