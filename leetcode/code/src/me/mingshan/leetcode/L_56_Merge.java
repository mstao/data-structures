package me.mingshan.leetcode;

import java.util.*;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author hanjuntao
 * @date 2022/1/10
 */
public class L_56_Merge {
    public static void main(String[] args) {
        int[][] a1 = {{1, 4}, {4, 5}};

        System.out.println(Arrays.deepToString(merge(a1))); // [[1, 5]]

        System.out.println("-------------------");

        int[][] a2 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        System.out.println("-------------------");
        System.out.println(Arrays.deepToString(merge(a2))); // [[1,6],[8,10],[15,18]]

        int[][] a3 = {{1, 3}, {2, 6}, {8, 11}, {10, 18}};

        System.out.println("-------------------");
        System.out.println(Arrays.deepToString(merge(a3)));
    }

    /**
     * 解题思路：
     *
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }

        int row = intervals.length;
        int col = intervals[0].length;

        // 数组转list
        List<List<Integer>> source = new ArrayList<>(row);

        for (int i = 0; i < row; i++) {
            List<Integer> integers = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                integers.add(intervals[i][j]);
            }

            source.add(integers);
        }

        // 每个lIst 按照第一个元素从小到大排列
        source.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                Integer integer1 = o1.get(0);
                Integer integer2 = o2.get(0);

                return Integer.compare(integer1, integer2);
            }
        });

        List<List<Integer>> result = new ArrayList<>();

        int len = source.size();

        for (int i = 0; i < len; i++) {
            List<Integer> integers1 = source.get(i);

            if (i + 1 < len) {
                List<Integer> integers2 = source.get(i + 1);
                int lastItem = integers1.get(integers1.size() - 1);
                int nextLastItem = integers2.get(integers2.size() - 1);
                if (lastItem <= nextLastItem) {
                    // 合并区间
                    List<Integer> currResult = new ArrayList<>();
                    currResult.add(integers1.get(0));
                    currResult.add(integers2.get(integers2.size() - 1));

                    result.add(currResult);
                } else {
                    result.add(integers1);
                }
            }

        }

        int[][] resultArr = new int[result.size()][result.get(0).size()];

        for (int i = 0; i < result.size(); i++) {
            List<Integer> integers1 = result.get(i);
            for (int j = 0; j < integers1.size(); j++)  {
                resultArr[i][j] = integers1.get(j);
            }
        }

        return resultArr;
    }
}
