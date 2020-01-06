package me.mingshan.graph.test;

import me.mingshan.graph.undir.AMWeightUndiGraph;
import org.junit.Assert;
import org.junit.Test;

public class AMWeightUndiGraphTest {

  @Test
  public void initTest() {
    AMWeightUndiGraph graph = new AMWeightUndiGraph();
    AMWeightUndiGraph.Node<Integer> node1 = new AMWeightUndiGraph.Node<>(1, 1);
    AMWeightUndiGraph.Node<Integer> node2 = new AMWeightUndiGraph.Node<>(2, 2);
    AMWeightUndiGraph.Node<Integer> node3 = new AMWeightUndiGraph.Node<>(3, 3);
    AMWeightUndiGraph.Node<Integer> node4 = new AMWeightUndiGraph.Node<>(4, 4);

    graph.addEdge(node1, 0, 1);
    graph.addEdge(node2, 0, 2);
    graph.addEdge(node3, 2, 3);
    graph.addEdge(node4, 1, 3);

    Assert.assertEquals(4, graph.getNodeSize());
    Assert.assertEquals(4, graph.getEdgeSize());

    System.out.println(graph.toString());
  }
}
