package me.mingshan.graph.test;

import me.mingshan.graph.undir.AMUndiGraph;
import org.junit.Assert;
import org.junit.Test;

public class AMUndiGraphTest {

  @Test
  public void initTest() {
    AMUndiGraph graph = new AMUndiGraph(4);

    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(2, 3);
    graph.addEdge(1, 3);

    Assert.assertEquals(4, graph.getNodeSize());
    Assert.assertEquals(4, graph.getEdgeSize());
  }

  @Test
  public void printTest() {
    AMUndiGraph graph = new AMUndiGraph(4);

    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(2, 3);
    graph.addEdge(1, 3);

    System.out.println(graph.toString());
  }

  @Test
  public void bfs() {
    AMUndiGraph graph = new AMUndiGraph(4);

    graph.addEdge(0, 1);
    graph.addEdge(0, 3);
    graph.addEdge(1, 2);
    graph.addEdge(2, 3);

    graph.bfs(0, 2);
  }

  @Test
  public void dfs() {
    AMUndiGraph graph = new AMUndiGraph(4);

    graph.addEdge(0, 1);
    graph.addEdge(0, 3);
    graph.addEdge(1, 2);
    graph.addEdge(2, 3);

    graph.dfs(0, 2);
  }


}