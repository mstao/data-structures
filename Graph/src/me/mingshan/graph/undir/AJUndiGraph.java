package me.mingshan.graph.undir;

import me.mingshan.graph.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 无向图 - 基于邻接表(adjacency list)
 *
 * @author hanjuntao
 */
public class AJUndiGraph implements Graph {
  // 邻接表
  private LinkedList<Integer>[] adj;
  // 节点数目
  private int nodeSize;
  // 边数量
  private int edgeSize;

  public AJUndiGraph(int arrSize) {
    if (arrSize <= 0) {
      throw new IllegalArgumentException("The arrSize [" + arrSize + "] is not valid number");
    }

    adj = new LinkedList[arrSize];
    for (int i = 0; i < arrSize; i++) {
      adj[i] = new LinkedList<>();
    }
  }

  public void addEdge(int start, int end) {
    adj[start].add(end);
    adj[end].add(start);
    this.edgeSize++;
  }

  /**
   * 广度优先搜索 Breadth-First-Search
   *
   * 打印最短路径
   *
   * @param s 起始顶点
   * @param t 终止顶点
   */
  public void bfs(int s, int t) {
    int len = adj.length;
    // 记录节点是否被访问过
    boolean[] visited = new boolean[len];
    int[] prev = new int[len];

    // 存储每一层的顶点
    Queue<Integer> queue = new LinkedList<>();
    visited[s] = true;
    queue.add(s);

    while (!queue.isEmpty()) {
      Integer vertex = queue.poll();
      for (int i = 0; i < adj[vertex].size(); i++) {
        Integer curr = adj[vertex].get(i);
        if (!visited[curr]) {
          prev[curr] = vertex;

          // 如果访问结束，直接返回
          if (curr == t) {
            print(prev, s, t);
            return;
          }

          visited[curr] = true;
          queue.add(curr);
        }
      }
    }
  }

  /**
   * 广度优先搜索 Breadth-First-Search
   *
   * @param s 起始顶点
   * @param t 终止顶点
   */
  public void bfs2(int s, int t) {
    int len = adj.length;
    // 记录节点是否被访问过
    boolean[] visited = new boolean[len];

    // 存储每一层的顶点
    Queue<Integer> queue = new LinkedList<>();
    visited[s] = true;
    queue.add(s);

    while (!queue.isEmpty()) {
      Integer vertex = queue.poll();
      for (int i = 0; i < adj[vertex].size(); i++) {
        Integer curr = adj[vertex].get(i);
        if (!visited[curr]) {
          visited[curr] = true;
          System.out.println("当前顶点：" + vertex + "，当前节点：" + curr);

          if (curr == t) {
            return;
          }

          queue.add(curr);
        }
      }
    }
  }

  /**
   * 递归打印s->t的路径
   *
   * @param prev
   * @param s 起始顶点
   * @param t 终止顶点
   */
  private static void print(int[] prev, int s, int t) {
    if (prev[t] != -1 && t != s) {
      print(prev, s, prev[t]);
    }

    System.out.print(t + " ");
  }

  /**
   * 深度优先搜索 Depth-First-Search
   *
   * 打印最短路径
   *
   * @param s 起始顶点
   * @param t 终止顶点
   */
  public void dfs(int s, int t) {
    int len = adj.length;
    boolean[] visited = new boolean[len];
    int[] prev = new int[len];
    for (int i = 0; i < len; ++i) {
      prev[i] = -1;
    }
    recurDfs(s, t, visited, prev);
    print(prev, s, t);
  }

  private void recurDfs(int vertex, int t, boolean[] visited, int[] prev) {
    visited[vertex] = true;
    if (vertex == t) {
      return;
    }

    for (int i = 0; i < adj[vertex].size(); ++i) {
      int curr = adj[vertex].get(i);
      if (!visited[curr]) {
        prev[curr] = vertex;
        recurDfs(curr, t, visited, prev);
      }
    }
  }

  /**
   * 深度优先搜索 Depth-First-Search
   *
   * 打印走过的路径
   *
   * @param s 起始顶点
   * @param t 终止顶点
   */
  public void dfs2(int s, int t) {
    int len = adj.length;
    boolean[] visited = new boolean[len];
    recurDfs(s, t, visited);
  }

  private void recurDfs(int vertex, int t, boolean[] visited) {
    visited[vertex] = true;
    if (vertex == t) {
      return;
    }

    for (int i = 0; i < adj[vertex].size(); ++i) {
      int curr = adj[vertex].get(i);
      if (!visited[curr]) {
        System.out.println("当前顶点：" + vertex + "，当前节点：" + curr);
        recurDfs(curr, t, visited);
      }
    }
  }

  @Override
  public int getNodeSize() {
    return adj.length;
  }

  @Override
  public int getEdgeSize() {
    return this.edgeSize;
  }

  @Override
  public String toString() {
    return "AJUndiGraph{" +
        "adj=" + Arrays.deepToString(adj) +
        ", nodeSize=" + getNodeSize() +
        ", edgeSize=" + edgeSize +
        '}';
  }
}
