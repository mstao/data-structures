package me.mingshan.graph.undir;

import me.mingshan.graph.Graph;

/**
 * 无向图 - 基于邻接矩阵(adjacency matrix)
 *
 * @author hanjuntao
 */
public class AMUndiGraph implements Graph {

  /** 邻接矩阵长或宽最大长度 */
  private static final int maxSideLength = 4;
  // 邻接矩阵长或宽长度
  private int sideLength;
  // 邻接矩阵
  private int[][] adjacencyMatrix;
  // 节点数目
  private int nodeSize;
  // 边数量
  private int edgeSize;

  public AMUndiGraph() {
    this(maxSideLength);
  }

  public AMUndiGraph(int sideLength) {
    if (sideLength <=0) {
      throw new IllegalArgumentException("The sideLength [" + sideLength + "] is not valid number");
    }

    this.sideLength = sideLength;
    adjacencyMatrix = new int[sideLength][sideLength];
  }

  /**
   * 添加边
   *
   * @param start 起始节点位置
   * @param end 结束节点位置
   */
  public void addEdge(int start, int end) {
    checkPosition(start);
    checkPosition(end);

    if (this.adjacencyMatrix[start][end] == 0 && this.adjacencyMatrix[end][start] == 0) {
      this.adjacencyMatrix[start][end] = 1;
      this.adjacencyMatrix[end][start] = 1;
      this.edgeSize++;
    }
  }

  /**
   * 获取节点数量
   *
   * @return 节点数量
   */
  @Override
  public int getNodeSize() {
    int currNodeSize = 0;

    for (int i = 0; i < sideLength; i++) {
      for (int j = 0; j < i + 1; j++) {
        if (adjacencyMatrix[i][j] == 1) {
          currNodeSize++;
        }
      }
    }

    return currNodeSize;
  }

  /**
   * 获取边的数量
   *
   * @return 边的数量
   */
  @Override
  public int getEdgeSize() {
    return this.edgeSize;
  }

  private void checkPosition(int index) {
    if (index < 0 || index >= adjacencyMatrix.length) {
      throw new IllegalArgumentException("The index [" + index + "] is not valid number");
    }
  }

  @Override
  public String toString() {
    String s = "Graph{" +
        "Node=" + nodeSize +
        ", Edge=" + edgeSize +
        '}';

    return s;
  }
}
