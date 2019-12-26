package me.mingshan.graph.undir;

import me.mingshan.graph.Graph;

import java.util.LinkedList;

/**
 * 无向图 - 基于邻接表(adjacency list)
 *
 * @author hanjuntao
 */
public class AJUndiGraph<E> implements Graph {
  // 邻接表
  private LinkedList<E>[] adj;
  // 节点数目
  private int nodeSize;
  // 边数量
  private int edgeSize;


  public AJUndiGraph(int nodeSize) {
    if (nodeSize <= 0) {
      throw new IllegalArgumentException("The nodeSize [" + nodeSize + "] is not valid number");
    }

    for (int i = 0; i < nodeSize; i++) {

    }
  }

  @Override
  public int getNodeSize() {
    return 0;
  }

  @Override
  public int getEdgeSize() {
    return 0;
  }
}
