package me.mingshan.heap;

/**
 * 堆抽象类
 * 
 * 堆又分为大根堆和小根堆。大根堆是堆中任何节点（除根节点外）都不大于其父节点的堆，也就是说，大根堆的根节点是堆中的最大值。
 * 小根堆是堆中任何节点（除根节点外）都不小于其父节点的堆，也就是说，小根堆的根节点是堆中的最小值。
 * 
 * 给定了某个节点的下标i(数组从1开始存储)，其父节点PARENT（i）、左儿子LEFT[i]和右儿子RIGHT[i]的下标
 * <ul>
 *   <li>Parent(i) return i/2 </li>
 *   <li>LEFT(i) return 2 * i </li>
 *   <li>RIGHT(i) return 2 * i + 1 </li>
 * </ul>
 * 
 * @author mingshan
 *
 * @param <T>
 */
public abstract class Heap<T extends Comparable<T>> implements IHeap<T> {

    /**
     * 根据给定节点的下标，找到其左儿子的下标
     * @param i 给定节点的下标
     * @return 其左儿子的下标
     */
    public int left(int i) {
        return 2 * i;
    }

    /**
     * 根据给定节点的下标，找到其右儿子的下标
     * @param i 给定节点的下标
     * @return 其右儿子的下标
     */
    public int right(int i) {
        return 2 * i + 1;
    }

    /**
     * 根据给定节点的下标，找到其父节点的下标
     * 
     * @param i 给定节点的下标
     * @return 其父节点的下标
     */
    public int parent(int i) {
        // 为根节点
        if (i == 0)
            return -1;
        return i / 2;
    }
}
