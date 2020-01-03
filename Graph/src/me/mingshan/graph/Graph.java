package me.mingshan.graph;

/**
 * 图的存储结构
 *
 * 图包含：无向图，有向图，无向加权图，有向加权图
 *
 * 1. 邻接矩阵
 * 2. 邻接表
 * 3. 十字链表
 * 4. 邻接多重表
 * 5. 边集数组
 *
 * @author hanjuntao
 */
public interface Graph {

  /**
   * 获取节点数量
   *
   * @return 节点数量
   */
  int getNodeSize();

  /**
   * 获取边的数量
   *
   * @return 边的数量
   */
  int getEdgeSize();

}
