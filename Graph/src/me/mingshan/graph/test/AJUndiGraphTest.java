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
}