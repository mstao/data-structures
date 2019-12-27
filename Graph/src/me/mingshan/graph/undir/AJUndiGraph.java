package me.mingshan.graph.undir;

import me.mingshan.graph.Graph;

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
   * 广度优先搜索
   *
   * @param s 起始顶点
   * @param t 终止顶点
   */
  public void bfs(int s, int t) {
    int v = adj.length;
    if (s == t) return;
    boolean[] visited = new boolean[v];
    visited[s] = true;
    Queue<Integer> queue = new LinkedList<>();
    queue.add(s);
    int[] prev = new int[v];
    for (int i = 0; i < v; ++i) {
      prev[i] = -1;
    }
    while (queue.size() != 0) {
      int w = queue.poll();
      for (int i = 0; i < adj[w].size(); ++i) {
        int q = adj[w].get(i);
        if (!visited[q]) {
          prev[q] = w;
          if (q == t) {
            print(prev, s, t);
            return;
          }
          visited[q] = true;
          queue.add(q);
        }
      }
    }
  }

  private static void print(int[] prev, int s, int t) { // 递归打印s->t的路径
    if (prev[t] != -1 && t != s) {
      print(prev, s, prev[t]);
    }

    System.out.print(t + " ");
  }

  @Override
  public int getNodeSize() {
    return adj.length;
  }

  @Override
  public int getEdgeSize() {
    return this.edgeSize;
  }
}
