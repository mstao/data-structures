package me.mingshan.graph.test;

import me.mingshan.graph.undir.AJUndiGraph;
import org.junit.Assert;
import org.junit.Test;

public class AJUndiGraphTest {

  @Test
  public void initTest() {
    AJUndiGraph graph = new AJUndiGraph(4);

    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(2, 3);
    graph.addEdge(1, 3);

    Assert.assertEquals(4, graph.getNodeSize());
    Assert.assertEquals(4, graph.getEdgeSize());
  }

  @Test
  public void bfsTest() {
    AJUndiGraph graph = new AJUndiGraph(4);

    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(2, 3);
    graph.addEdge(1, 3);

    graph.bfs(0, 3);
  }

  @Test
  public void bfsTest2() {
    AJUndiGraph graph = new AJUndiGraph(4);

    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(2, 3);
    graph.addEdge(1, 3);

    graph.bfs2(0, 3);
  }

  @Test
  public void printTest() {
    AJUndiGraph graph = new AJUndiGraph(4);

    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(2, 3);
    graph.addEdge(1, 3);

    System.out.println(graph);
  }

  @Test
  public void dfsTest() {
    AJUndiGraph graph = new AJUndiGraph(4);

    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(2, 3);
    graph.addEdge(1, 3);

    graph.dfs(0, 3);
  }

  @Test
  public void dfs2Test() {
    AJUndiGraph graph = new AJUndiGraph(4);

    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(2, 3);
    graph.addEdge(1, 3);

    graph.dfs2(0, 5);
  }
}