package me.mingshan.graph.undir;

import me.mingshan.graph.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 无向图 - 基于邻接矩阵(adjacency matrix)
 *
 * @author hanjuntao
 */
public class AMUndiGraph implements Graph {

  /**
   * 邻接矩阵长或宽最大长度
   */
  private static final int DEFAULT_SIDE_LENGTH = 4;
  // 邻接矩阵长或宽长度
  private int sideLength;
  // 邻接矩阵
  private int[][] adjacencyMatrix;
  // 节点数目
  private int nodeSize;
  // 边数量
  private int edgeSize;

  public AMUndiGraph() {
    this (DEFAULT_SIDE_LENGTH);
  }

  public AMUndiGraph(int sideLength) {
    if (sideLength <= 0) {
      throw new IllegalArgumentException("The sideLength [" + sideLength + "] is not valid number");
    }

    this.sideLength = sideLength;
    adjacencyMatrix = new int[sideLength][sideLength];
  }

  /**
   * 添加边
   *
   * @param start 起始节点位置
   * @param end   结束节点位置
   */
  public void addEdge(int start, int end) {
    checkPosition(start);
    checkPosition(end);

    this.adjacencyMatrix[start][end] = 1;
    this.adjacencyMatrix[end][start] = 1;
    this.edgeSize++;
  }

  /**
   * 广度优先搜索 Breadth-First-Search
   *
   * {@code
   *
   * void bfs (传入起始结点的位置）
   *
   * {
   *
   *         定义队列 q
   *
   *         配置、压入首节点，并把首节点pop出队列
   *
   *         访问标记
   *
   *    while(！q.empty（）)
   *
   *    {
   *
   *         取出队列头结点，作为本次处理的点对象。
   *
   *         检验：此点是否已经达到目标状态，如果达到，直接结束bfs。（记住：检验在扩展之前）
   *
   *         如果没达到，开始从这个点发散扩展，并将扩展找到的所有合法结点，压入队列末尾。
   *
   *    }
   *
   *    return ;
   *
   * }
   * }
   *
   * @param s 起始顶点
   * @param t 终止顶点
   */
  public void bfs(int s, int t) {
    boolean[] visited = new boolean[sideLength];
    Queue<Integer> queue = new LinkedList<>();
    queue.add(s);
    visited[s] = true;

    while (!queue.isEmpty()) {
      Integer item = queue.poll();

      // 访问其邻居
      for (int i = 0; i < sideLength; i++) {
        int curr = this.adjacencyMatrix[item][i];
        if (curr != 1) {
          continue;
        }

        if (!visited[i]) {
          System.out.println(i);

          if (i == t) {
            return;
          }

          queue.add(i);
        }
      }
    }
  }

  /**
   * 深度优先搜索 Depth-First-Search
   *
   * @param s 起始顶点
   * @param t 终止顶点
   */
  public void dfs(int s, int t) {
    boolean[] visited = new boolean[sideLength];
    recurDfs(s, t, visited);
  }

  private void recurDfs(int vertex, int t, boolean[] visited) {
    visited[vertex] = true;
    if (vertex == t) {
      return;
    }

    for (int i = 0; i < sideLength; ++i) {
      int curr = adjacencyMatrix[vertex][i];
      if (curr != 1) {
        continue;
      }
      if (!visited[i]) {
        System.out.println("当前顶点：" + vertex + "，当前节点：" + i);
        recurDfs(i, t, visited);
      }
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
    return "AMUndiGraph{" +
        "sideLength=" + sideLength +
        ", adjacencyMatrix=" + Arrays.deepToString(adjacencyMatrix) +
        ", nodeSize=" + nodeSize +
        ", edgeSize=" + edgeSize +
        '}';
  }
}
