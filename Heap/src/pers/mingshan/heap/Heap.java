package pers.mingshan.heap;

/**
 * 堆抽象类
 * 
 * 堆又分为大根堆和小根堆。大根堆是堆中任何节点（除根节点外）都不大于其父节点的堆，也就是说，大根堆的根节点是堆中的最大值。
 * 小根堆是堆中任何节点（除根节点外）都不小于其父节点的堆，也就是说，小根堆的根节点是堆中的最小值。
 * 
 * 给定了某个节点的下标i，其父节点PARENT（i）、左儿子LEFT[i]和右儿子RIGHT[i]的下标
 * <ul>
 *   <li>Parent(i) return i/2 </li>
 *   <li>LEFT(i) return 2i </li>
 *   <li>RIGHT(i) return 2i + 1 </li>
 * </ul>
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
        return (i + 1) * 2 - 1;
    }

    /**
     * 根据给定节点的下标，找到其右儿子的下标
     * @param i 给定节点的下标
     * @return 其右儿子的下标
     */
    public int right(int i) {
        return (i + 1) * 2;
    }

    /**
     * 根据给定节点的下标，找到其父节点的下标
     * @param i 给定节点的下标
     * @return 其父节点的下标
     */
    public int parent(int i) {
        // 为根节点
        if (i == 0)
            return -1;
        return (i - 1) / 2;
    }

    /**
     * 
     * @param a 保存堆的数组
     * @param i 堆中需要下降的元素
     * @param heapLength 堆元素个数
     */
    public abstract void heapify(T[] a, int i, int heapLength);

    /**
     * 建堆
     * @param a 数组
     * @param heapLength 堆元素个数
     */
    public void buildHeap(T[] a, int heapLength) {
        // 从后往前看，lengthParent处的元素是第一个有孩子节点的节点
        int lengthParent = parent(heapLength - 1);
        // 最初，parent(length)之后的所有元素都是叶子结点；
        // 因为大于length/2处元素的孩子节点如果存在，那么
        // 它们的数组下标值必定大于length，这与事实不符；
        // 在数组中，孩子元素必定在父亲元素的后面，从后往前
        // 对元素调用maxHeapify，保证了元素的孩子都是
        // 大根堆
        for(int i = lengthParent; i >= 0; i--){
            heapify(a, i, heapLength);
        }
    }
}
