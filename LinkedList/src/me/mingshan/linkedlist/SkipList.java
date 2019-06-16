package me.mingshan.linkedlist;

/**
 * 跳表
 * 
 */
@SuppressWarnings("unchecked")
public class SkipList<T> {
    // 最高层数
    private final int MAX_LEVEL;
    // 当前层数
    private int listLevel;
    // 表头
    private Node<T> listHead;
    // 表尾
    private Node<T> NIL;
    // 生成randomLevel用到的概率值
    private final double P;
    // 论文里给出的最佳概率值
    private static final double OPTIMAL_P = 0.25;

    public SkipList() {
        // 0.25, 15
        this(OPTIMAL_P, (int)Math.ceil(Math.log(Integer.MAX_VALUE) / Math.log(1 / OPTIMAL_P)) - 1);
    }

    public SkipList(double probability, int maxLevel) {
        P = probability;
        MAX_LEVEL = maxLevel;

        listLevel = 1;
        listHead = new Node<T>(Integer.MIN_VALUE, null, maxLevel);
        NIL = new Node<T>(Integer.MAX_VALUE, null, maxLevel);
        for (int i = listHead.forward.length - 1; i >= 0; i--) {
            listHead.forward[i] = NIL;
        }
    }

    /**
     * 内部节点类
     *
     * @param <T> 泛型参数
     */
    private static class Node<T> {
        int key;
        T value;
        /**
         * 每一层单链表指针：
         * 0：最底层
         * ......
         * i：第i层节点
         *
         * p = p.forwards[i] 表示第i层下一个节点
         */
        Node<T>[] forward;

        public Node(int key, T value, int level) {
            this.key = key;
            this.value = value;
            this.forward = new Node[level];
        }
    }

    public T search(int searchKey) {
        Node<T> curNode = listHead;

        for (int i = listLevel; i > 0; i--) {
            while (curNode.forward[i].key < searchKey) {
                curNode = curNode.forward[i];
            }
        }

        if (curNode.key == searchKey) {
            return curNode.value;
        } else {
            return null;
        }
    }

    public void insert(int searchKey, T newValue) {
        // update数组为层级索引，插入新节点需要在索引建立层数
        Node<T>[] update = new Node[MAX_LEVEL];
        Node<T> curNode = listHead;

        // record every level largest value which smaller than insert value in update[]
        // 在update中纪录每一层中 小于value值的最大节点
        for (int i = listLevel - 1; i >= 0; i--) {
            while (curNode.forward[i].key < searchKey) {
                curNode = curNode.forward[i];
            }
            // use update save node in search path
            update[i] = curNode;
        }

        // in search path node next node become new node forwords(next)
        // 插入newNode 串联每一个层级的索引
        curNode = curNode.forward[0];

        if (curNode.key == searchKey) {
            curNode.value = newValue;
        } else {
            int lvl = randomLevel();

            // 随机的层数有可能会大于当前跳表的层数，那么多余的那部分层数对应的update[i]置为sl->head,后面用来初始化
            if (listLevel < lvl) {
                for (int i = listLevel; i < lvl; i++) {
                    update[i] = listHead;
                }
                listLevel = lvl;
            }

            Node<T> newNode = new Node<T>(searchKey, newValue, lvl);

            // 逐层更新节点的指针(这里的层指的是随机的层，比如当前有4层，然后随机的层为2，则只会将新节点插入下面的两层)
            // 如果当前跳表层是4，随机的为6，则会把5、6层也赋值，用到update[i] = sl->head;这里的结果。
            for (int i = 0; i < lvl; i++) {
                // 这里就是说随机几层，就用到update中的那几层，插入到update[i]对应的节点之后
                newNode.forward[i] = update[i].forward[i];
                update[i].forward[i] = newNode;
            }
        }
    }

    public void delete(int searchKey) {
        Node<T>[] update = new Node[MAX_LEVEL];
        Node<T> curNode = listHead;

        for (int i = listLevel - 1; i >= 0; i--) {
            while (curNode.forward[i].key < searchKey) {
                curNode = curNode.forward[i];
            }
            // curNode.key < searchKey <= curNode.forward[i].key
            update[i] = curNode;
        }

        curNode = curNode.forward[0];

        // 逐层删除与普通链表删除一样
        if (curNode.key == searchKey) {
            for (int i = 0; i < listLevel; i++) {
                if (update[i].forward[i] != curNode) {
                    break;
                }
                update[i].forward[i] = curNode.forward[i];
            }

            // 如果删除的节点是最高层的节点，则level--
            while (listLevel > 0 && listHead.forward[listLevel - 1] == NIL) {
                listLevel--;
            }
        }
    }

    private int randomLevel() {
        int level = 1;
        while (level < MAX_LEVEL && Math.random() < P) {
            level++;
        }
        return level;
    }

    public void print() {
        for (int i = listLevel - 1; i >= 0; i--) {
            Node<T> curNode = listHead.forward[i];
            while (curNode != NIL) {
                System.out.print(curNode.key + "->");
                curNode = curNode.forward[i];
            }
            System.out.println("NIL");
        }
    }

    public static void main(String[] args) {
        SkipList<Integer> sl = new SkipList<Integer>();
        sl.insert(1, 1);
        sl.insert(3, 3);
        sl.insert(5, 5);
        sl.insert(6, 6);
        sl.insert(8, 8);
        sl.insert(9, 9);
        sl.insert(2, 2);
        sl.print();
        System.out.println("---");
 
        sl.print();
    }
}
