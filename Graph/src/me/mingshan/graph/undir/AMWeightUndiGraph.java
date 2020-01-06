package me.mingshan.graph.undir;

import me.mingshan.graph.Graph;

/**
 * 无向加权图 - 基于邻接矩阵(adjacency matrix)
 */
public class AMWeightUndiGraph implements Graph {
  /** 邻接矩阵长或宽最大长度 */
  private static final int maxSideLength = 4;
  // 邻接矩阵长或宽长度
  private int sideLength;
  // 邻接矩阵
  private Node[][] adjacencyMatrix;
  // 节点数目
  private int nodeSize;
  // 边数量
  private int edgeSize;

  public AMWeightUndiGraph() {
    this(maxSideLength);
  }

  public AMWeightUndiGraph(int sideLength) {
    if (sideLength <=0) {
      throw new IllegalArgumentException("The sideLength [" + sideLength + "] is not valid number");
    }

    this.sideLength = sideLength;
    adjacencyMatrix = new Node[sideLength][sideLength];
  }

  /**
   * 添加边
   *
   * @param node 节点
   * @param start 起始节点位置
   * @param end 结束节点位置
   */
  public void addEdge(Node node, int start, int end) {
    checkPosition(start);
    checkPosition(end);

    if (this.adjacencyMatrix[start][end] == null && this.adjacencyMatrix[end][start] == null) {
      this.adjacencyMatrix[start][end] = node;
      this.adjacencyMatrix[end][start] = node;
      this.edgeSize++;
    }
  }

  private void checkPosition(int index) {
    if (index < 0 || index >= adjacencyMatrix.length) {
      throw new IllegalArgumentException("The index [" + index + "] is not valid number");
    }
  }

  @Override
  public int getNodeSize() {
    int currNodeSize = 0;

    for (int i = 0; i < sideLength; i++) {
      for (int j = 0; j < i + 1; j++) {
        if (adjacencyMatrix[i][j] != null) {
          currNodeSize++;
        }
      }
    }

    return currNodeSize;
  }

  @Override
  public int getEdgeSize() {
    return this.edgeSize;
  }

  /**
   * 内部节点
   *
   * @param <E> 节点值泛型
   */
  public static class Node<E> {
    // 节点值
    private E value;
    // 节点权重
    private int weight;

    /**
     * 默认权重为0，相当于不加权
     *
     * @param value 节点值
     */
    Node(E value) {
      this(value, 0);
    }

    public Node(E value, int weight) {
      this.value = value;
      this.weight = weight;
    }
  }
}
