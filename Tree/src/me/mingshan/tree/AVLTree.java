package me.mingshan.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 平衡二叉树:<br/>
 * 它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 *
 * @author mingshan
 */
public class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> {
  /**
   * AVL树的失衡类型枚举，包括: LL, LR, RL, RR
   */
  private enum Balance {
    /**
     * LL
     */
    LEFT_LEFT,

    /**
     * LR
     */
    LEFT_RIGHT,

    /**
     * RL
     */
    RIGHT_LEFT,

    /**
     * RR
     */
    RIGHT_RIGHT
  }

  /**
   * Just for test
   *
   * @param root 根结点
   */
  public void initRoot(AVLNode<E> root) {
    this.root = root;
  }

  @Override
  public boolean add(E value) {
    AVLNode<E> node = addNode(value);
    return node != null;
  }

  /**
   * 添加节点
   *
   * @param value 节点值
   * @return 新添加的节点
   */
  private AVLNode<E> addNode(E value) {
    Objects.requireNonNull(value, "The value must be not null");

    // 生成新结点
    AVLNode<E> newNode = new AVLNode<>(value);
    // 如果根结点不存在
    if (root == null) {
      root = newNode;
      size++;
      return newNode;
    }

    AVLNode<E> node = (AVLNode<E>) root;
    // 按照先序进行遍历二叉树
    while (node != null) {
      // 如果新结点的值比父节点的值小
      if (node.getItem().compareTo(newNode.getItem()) > 0) {
        // 此时应该从父节点的左子树进行搜索
        // 并且新结点作为叶子结点，其父节点的左子结点应为null
        if (node.getLeft() == null) {
          node.setLeft(newNode);
          newNode.setParent(node);
          size++;
          break;
        }
        node = (AVLNode<E>) node.getLeft();
      } else {
        // 如果当前结点的值比父结点的值大，说明应该从父节点的右子树搜索
        // 并且新结点作为叶子结点，其父节点的右子结点应为null
        if (node.getRight() == null) {
          node.setRight(newNode);
          newNode.setParent(node);
          size++;
          break;
        }
        node = (AVLNode<E>) node.getRight();
      }
    }

    // 更新当前节点的高度
    newNode.updateHeight();
    // 平衡当前节点
    rebalanced(newNode);

    // 平衡父结点，使整棵树达到平衡
    rebalanceParent(newNode);
    return newNode;
  }

  /**
   * 平衡父结点，使整棵树达到平衡
   *
   * @param node 节点
   */
  private void rebalanceParent(AVLNode<E> node) {
    AVLNode<E> currParent = (AVLNode<E>) node.getParent();
    while (currParent != null) {
      int h1 = currParent.getHeight();

      currParent.updateHeight();
      rebalanced(currParent);

      // If height before and after balance is the same, stop going up the tree
      int h2 = currParent.getHeight();
      if (h1 == h2) {
        break;
      }

      currParent = (AVLNode<E>) currParent.getParent();
    }
  }

  /**
   * 重新平衡AVL树
   *
   * @param node 当前节点
   */
  private void rebalanced(AVLNode<E> node) {
    Objects.requireNonNull(node, "node must be not null");

    // 获取节点的平衡因子
    int balanceFactor = node.getBalanceFactor();

    // 平衡因子大于1或者小于-1，表示当前树失衡了，需要进行平衡处理
    if (Math.abs(balanceFactor) > 1) {
      Balance balance = null;
      AVLNode<E> childNode;

      // 右子树比左子树高，左旋操作
      if (balanceFactor < 0) {
        // 获取当前节点的右子节点
        childNode = (AVLNode<E>) node.getRight();
        // 如果右子节点的平衡因子小于0，此时对应RR
        if (childNode.getBalanceFactor() < 0) {
          balance = Balance.RIGHT_RIGHT;
        } else if (childNode.getBalanceFactor() > 0) {
          // 如果右子节点的平衡因子大于0，说明此时出现左边子树比右边高的情况，对应RL
          balance = Balance.RIGHT_LEFT;
        }
      } else {
        // 获取当前节点的左子节点
        childNode = (AVLNode<E>) node.getLeft();
        if (childNode.getBalanceFactor() > 0) {
          balance = Balance.LEFT_LEFT;
        } else if (childNode.getBalanceFactor() < 0) {
          balance = Balance.LEFT_RIGHT;
        }
      }

      // 根据上面的平衡类型需要分四种情况来考虑
      // 1. 失衡为LL，说明左子树比右子树高，需要进行右旋操作
      if (Balance.LEFT_LEFT.equals(balance)) {
        rotateRight(node);
      } else if (Balance.RIGHT_RIGHT.equals(balance)) {
        // 2. 失衡为RR，说明右子树比左子树高，需要进行左旋操作
        rotateLeft(node);
      } else if (Balance.RIGHT_LEFT.equals(balance)) {
        // 3. 失衡为RL，需要旋转两次
        // 以较高子树为根结点向右旋转
        rotateRight(childNode);
        // 然后再以当前节点向左旋转
        rotateLeft(node);
      } else if (Balance.LEFT_RIGHT.equals(balance)) {
        // 4. 失衡为LR，需要两次旋转
        // 以较高子树为根结点向左旋转
        rotateLeft(childNode);
        // 然后再以当前节点向右旋转
        rotateRight(node);
      }

      childNode.updateHeight();
      node.updateHeight();
    }
  }

  /**
   * 左旋，失衡情况对应RR
   *
   * @param node 当前子树根结点
   * @return 旋转后的根结点
   */
  public AVLNode<E> rotateLeft(AVLNode<E> node) {
    Objects.requireNonNull(node, "node must be not null");

    // 暂存当前节点
    AVLNode<E> originNode = node;
    // 当前节点的右子结点
    AVLNode<E> rightNode = (AVLNode<E>) node.getRight();
    if (rightNode == null) {
      return node;
    }
    // 以当前根节点的右子树根结点作为根结点
    originNode.setRight(rightNode.getLeft());
    // 替换根结点
    node = rightNode;
    node.setLeft(originNode);

    return node;
  }

  /**
   * 右旋，失衡情况对应LL
   *
   * @param node 当前子树根结点
   * @return 旋转后的根结点
   */
  public AVLNode<E> rotateRight(AVLNode<E> node) {
    Objects.requireNonNull(node, "node must be not null");

    // 暂存当前节点
    AVLNode<E> originNode = node;
    // 当前节点的左子节点
    AVLNode<E> leftNode = (AVLNode<E>) node.getLeft();
    if (leftNode == null) {
      return node;
    }
    originNode.setLeft(leftNode.getRight());
    // 替换根结点
    node = leftNode;
    node.setRight(originNode);

    return node;
  }

  @Override
  public E remove(E value) {
    Objects.requireNonNull(value, "The value must be not null");

    Node<E> node = super.getNode(value);
    if (node == null) {
      return null;
    }

    if (node.isLeaf()) {
      // 直接删除
      node.setParent(null);
    } else if (node.getLeft() == null || node.getRight() == null) {
      // 只是左子节点或者右子节点
      Node<E> subNode = null;
      if (node.getLeft() != null) {
        subNode = node.getLeft();
        node.setLeft(null);
      } else if (node.getRight() != null) {
        subNode = node.getRight();
        node.setRight(null);
      }

      Node<E> parent = node.getParent();
      if (parent.getLeft() == node) {
        parent.setLeft(subNode);
      } else if (parent.getRight() == node) {
        parent.setRight(subNode);
      }

      assert subNode != null;
      subNode.setParent(parent);
    } else {
      // 当前节点拥有左右两子节点

      // 不直接删除节点，而且找一个节点替代，从左右子树中较高的一侧寻找替代节点。
      // 如果是从左子树中寻找，则找其最右端的子节点，也就是左子树中最大的元素作为替代节点；
      // 如果是从右子树中寻找，则找其最左端的子节点，也就是右子树中最小的元素作为替代节点。
      Node<E> left = node.getLeft();
      Node<E> right = node.getRight();

      Node<E> replaceNode;

      if (left.getHeight() > right.getHeight()) {
        replaceNode = left.getPrev();
        Node<E> replaceNodeParent = replaceNode.getParent();
        replaceNodeParent.setLeft(replaceNode.getRight());
      } else {
        replaceNode = right.getSucc();
        Node<E> replaceNodeParent = replaceNode.getParent();
        replaceNodeParent.setRight(replaceNode.getLeft());
      }

      replaceNode.setParent(null);
      replaceNode.setLeft(null);
      replaceNode.setRight(null);

      node.setItem(replaceNode.getItem());
    }

    // 沿着其父节点一路向上平衡
    AVLNode<E> avlNode = (AVLNode<E>) node;
    rebalanceParent(avlNode);
    return value;
  }

  @Override
  public void clear() {
    super.clear();
  }

  @Override
  public boolean contains(E value) {
    return super.contains(value);
  }

  /**
   * AVL树的节点
   *
   * @param <E> 节点的值
   */
  public static class AVLNode<E extends Comparable<E>> extends Node<E> {
    public AVLNode(E item) {
      this(item, null, null, null);
    }

    public AVLNode(E item, AVLNode<E> parent, AVLNode<E> left, AVLNode<E> right) {
      super(item, parent, left, right);
    }

    /**
     * 获取平衡因子，计算方式：左树的高度减去右树的高度
     *
     * @return 平衡因子bf
     */
    int getBalanceFactor() {
      int leftHeight = 0;
      Node<E> left = getLeft();
      if (left != null) {
        leftHeight = left.getHeight();
      }

      int rightHeight = 0;
      Node<E> right = getRight();
      if (right != null) {
        rightHeight = right.getHeight();
      }
      return leftHeight - rightHeight;
    }

    /**
     * 更新当前节点的高度
     *
     * @return 更新后的高度
     */
    int updateHeight() {
      int leftHeight = 0, rightHeight = 0;

      Node<E> left = getLeft();
      Node<E> right = getRight();
      if (left != null) {
        leftHeight = left.getHeight();
      }
      if (right != null) {
        rightHeight = right.getHeight();
      }

      int i = 1 + Math.max(leftHeight, rightHeight);
      setHeight(i);
      return i;
    }

    @Override
    public String toString() {
      Node<E> left = getLeft();
      Node<E> right = getRight();
      return "item=" + getItem() + " left="
          + ((left != null) ? left.getItem() : "NULL") + " right=" + ((right != null) ? right.getItem() : "NULL");
    }
  }

  @Override
  public String toString() {
    if (root == null) {
      return "Tree has no nodes.";
    }
    return TreePrinter.getString(root, "", true);
  }

}
