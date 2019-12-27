package me.mingshan.graph.undir;

import me.mingshan.graph.Graph;

import java.util.LinkedList;

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
      throw new IllegalArgumentException("The arrSize [" + arrSize+ "] is not valid number");
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

  @Override
  public int getNodeSize() {
    int currNodeSize = adj.length;

    for (LinkedList list : adj) {
      if (list != null) {
        currNodeSize += list.size();
      }
    }

    return currNodeSize;
  }

  @Override
  public int getEdgeSize() {
    return 0;
  }
}
