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
}