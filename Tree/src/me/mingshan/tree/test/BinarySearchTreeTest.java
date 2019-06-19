package me.mingshan.tree.test;

import org.junit.Before;
import org.junit.Test;

import me.mingshan.tree.BinarySearchTree;

/**
 * 二叉搜索树test
 * 
 * @author mingshan
 *
 */
public class BinarySearchTreeTest {
    final BinarySearchTree<Integer> tree = new BinarySearchTree<>();

    /**
     *            10
     *       7         40
     *          8   20    49
     *            13    43  123
     */
    @Before
    public void init() {
        tree.add(10);
        tree.add(40);
        tree.add(20);
        tree.add(7);
        tree.add(8);
        tree.add(49);
        tree.add(43);
        tree.add(13);
        tree.add(123);
    }

    /**
     * 测试添加结点
     */
    @Test
    public void testAdd() {
        System.out.println(tree.toString());
    }

    /**
     * 测试删除叶子结点
     * 
     */
    @Test
    public void testRomove1() {
        tree.remove(13);
        System.out.println(tree.toString());
    }

    /**
     * 测试删除   只有左子结点
     */
    @Test
    public void testRomove2() {
        tree.remove(20);
        System.out.println(tree.toString());
    }

    /**
     * 测试删除   只有右子结点
     */
    @Test
    public void testRomove3() {
        tree.remove(7);
        System.out.println(tree.toString());
    }
    

    /**
     * 测试删除， 左右子结点都有
     */
    @Test
    public void testRomove4() {
        tree.remove(49);
        System.out.println(tree.toString());
    }

    /**
     * 测试contains 
     */
    @Test
    public void testContains() {
        System.out.println(tree.contains(43));
    }
}
