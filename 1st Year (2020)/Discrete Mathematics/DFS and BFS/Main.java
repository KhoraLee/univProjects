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

package konkuk.sylee.dm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  public static BufferedReader bf;
  static int count = 1; // 그래프 순서 표시를 위한 변수

  public static void main(String[] args) throws Exception {
    ArrayList<int[][]> graphArr = new ArrayList<>(); // 그래프들을 저장할 ArrayList
    bf = new BufferedReader(
        new InputStreamReader(new FileInputStream(new File("input.txt")))); // 파일 읽어들이기

    // 파일에서 모든 그래프 읽어들이기
    int[][] tmpGraph;
    while (!((tmpGraph = readGraph()) == null)) {
      graphArr.add(tmpGraph);
    }
    // End of Graph reading from file

    graphArr.forEach(Main::DBFS); // 모든 그래프에 대하여 DFS / BFS 수행
  }

  /**
   * 맨 처음 줄에서 노드의 개수를 읽어들이고 그이후 라인에서 인접 노드간의 관계를 읽어 2차원 배열로 형성한다.
   *
   * @return 파일로 부터 읽어들인 인접 행렬 한세트
   * @throws IOException
   */
  public static int[][] readGraph() throws IOException {
    String line = bf.readLine();
    // 다음 라인이 없다면 그래프를 다 읽었음으로 null 반환
    if (line == null) {
      return null;
    }
    // Graph reading start
    int vertexNum = Integer.parseInt(line); // 전체 노드의 개수
    int[][] graph = new int[vertexNum + 1][vertexNum + 1]; // 인접 행렬
    for (int i = 1; i < vertexNum + 1; i++) {
      StringTokenizer st = new StringTokenizer(bf.readLine(), " "); // ' ' 단위로 글자 자르기
      while (st.hasMoreTokens()) {
        int parsed = Integer.parseInt(st.nextToken());
        graph[i][parsed] = 1; // 행렬에 정점 기록
      }
    }
    return graph;
  }

  /**
   * 재귀 호출을 이용한 깊이 우선 탐색 (DFS)
   *
   * @param start DFS를 시작할 노드
   * @param graph 전체 그래프
   */
  public static Queue<Integer> dfs(int start, int[][] graph) {
    boolean[] visited = new boolean[graph.length]; // 방문 여부를 표시한 boolean 배열
    Queue<Integer> visitOrder = new LinkedList<>(); // 방문 순서를 표기하기 위한 int Queue
    visited[start] = true; // 현재 노드을 방문으로 표기
    visitOrder.add(start); // 방문 순서 큐에 현재 노드 추가
    for (int i = 1; i < graph.length; i++) {
      // 현재 노드에서 인접해 있는 노드들 중에 방문하지 않은 노드가 있다면
      if (graph[start][i] == 1 && !visited[i]) {
        dfs(i, graph, visited, visitOrder); // 방문하지 않은 이웃 노드 탐방
      }
    }
    return visitOrder;
  }

  /**
   * 재귀 호출을 위해 필요한 DFS Method
   *
   * @param cur        현재 노드
   * @param graph      전체 그래프
   * @param visited    노드의 방문 여부 배열
   * @param visitOrder 방문 순서 큐
   */
  private static void dfs(int cur, int[][] graph, boolean[] visited, Queue<Integer> visitOrder) {
    visited[cur] = true; // 현재 노드의 방문 여부 표기
    visitOrder.add(cur); // 방문 순서에 현재 노드 추가
    for (int i = 1; i < graph.length; i++) {
      // 현재 노드에서 인접해 있는 노드들 중에 방문하지 않은 노드가 있다면
      if (graph[cur][i] == 1 && !visited[i]) {
        dfs(i, graph, visited, visitOrder); // 방문하지 않은 이웃 노드 탐방
      }
    }
  }

  /**
   * Queue를 이용한 너비 우선 탐색 (BFS)
   *
   * @param start BFS를 시작할 정점
   * @param graph 전체 그래프
   */
  public static Queue<Integer> bfs(int start, int[][] graph) {
    Queue<Integer> q = new LinkedList<>(); // bfs 수행을 위한 Queue
    Queue<Integer> visitOrder = new LinkedList<>(); // 방문 순서 기록을 위한 Queue
    boolean[] visited = new boolean[graph.length]; // 방문 여부를 기록한 boolean 배열
    q.add(start); // bfs 큐에 시작 노드 삽입
    visited[start] = true; // 현재 노드를 방문했다고 표기

    // bfs 큐가 공백이 될때 까지 반복
    while (!q.isEmpty()) {
      int poll = q.poll(); // 큐에서 노드 꺼내기
      visitOrder.add(poll); // 방문 순서 기록
      for (int i = 1; i < graph.length; i++) {
        // 현재 노드에서 인접해 있는 노드들 중에 방문하지 않은 노드가 있다면
        if (graph[poll][i] == 1 && !visited[i]) {
          q.add(i); // 인접한 노드들을 큐에 추가
          visited[i] = true; // 인접한 노드들을 방문했다고 표기
        }
      }
    }
    return visitOrder;
  }

  /**
   * 주어진 그래프에 대하여 DFS와 BFS를 수행후 그 결과를 출력
   *
   * @param graph 사용할 그래프의 인접 행렬
   */
  public static void DBFS(int[][] graph) {
    System.out.printf("그래프 [%d]\n",count++);
    System.out.println("---------------------");
    System.out.println("깊이 우선 탐색 (DFS)");
    printVisitQueue(dfs(1, graph));
    System.out.println("너비 우선 탐색 (BFS)");
    printVisitQueue(bfs(1, graph));
    System.out.println("=====================");
  }

  /**
   * 노드들을 방문한 순서대로 입력한 큐를 출력
   * @param q 방문 순서를 나타낸 큐
   */
  public static void printVisitQueue(Queue<Integer> q) {
    int size = q.size();
    for (int i = 0; i < size-1; i++) {
      System.out.print(q.poll() + " -> ");
    }
    System.out.print(q.poll() + "\n");
  }

}
