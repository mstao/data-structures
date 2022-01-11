package me.mingshan.leetcode;

import java.util.Arrays;

/**
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author hanjuntao
 * @date 2022/1/11
 */
public class L_31_NextPermutation {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};

        nextPermutation(nums1);
        System.out.println(Arrays.toString(nums1));
        System.out.println("------------------------");// [1, 3, 2]

        int[] nums2 = {3, 2, 1};

        nextPermutation(nums2);
        System.out.println(Arrays.toString(nums2));
        System.out.println("------------------------");// [1, 2, 3]

        int[] nums3 = {1, 1, 5};

        nextPermutation(nums3);
        System.out.println(Arrays.toString(nums3));
        System.out.println("------------------------"); // [1, 5, 1]


        int[] nums4 = {4, 3, 2, 1};

        nextPermutation(nums4);
        System.out.println(Arrays.toString(nums4));
        System.out.println("------------------------");// [1, 2, 3, 4]


        int[] nums5 = {1, 3, 2};

        nextPermutation(nums5);
        System.out.println(Arrays.toString(nums5));
        System.out.println("------------------------");  // [2,1,3]

        int[] nums6 = {2, 3, 1};

        nextPermutation(nums6);
        System.out.println(Arrays.toString(nums6));
        System.out.println("------------------------");  // [3,1,2]

        int[] nums7 = {5,4,7,5,3,2};

        // [5,5,4,7,3,2]

        nextPermutation(nums7);
        System.out.println(Arrays.toString(nums7));
        System.out.println("------------------------");  // [5,5,2,3,4,7]

        int[] nums8 = {2,3,1,3,3};

        // [2, 3, 3, 3, 1]
        nextPermutation(nums8);
        System.out.println(Arrays.toString(nums8));
        System.out.println("------------------------");  // [2,3,3,1,3]

        int[] nums9 = {2,1,2,2,2,2,2,1};

        // [2, 2, 1, 2, 2, 2, 2, 1]
        nextPermutation(nums9);
        System.out.println(Arrays.toString(nums9));
        System.out.println("------------------------");  // [2,2,1,1,2,2,2,2]

        int[] nums10 = { 2,1,4,3,2,1};

        nextPermutation(nums10);
        System.out.println(Arrays.toString(nums10));
        System.out.println("------------------------"); // [2, 2, 1, 1, 3, 4]

    }

    /**
     * 思路：
     *
     * 解此题，思路比较关键，由于此题要求【必须 原地 修改，只允许使用额外常数空间】，那么就需要观察如何原地修改才能符合要求。
     *
     * 经过观察，发现要找一下排列，只需要从后往前遍历，找到第一个比其后后一位小的数，比如数组[2,1,4,3,2,1]，我们要找到的那个数是1【索引为1】，为什么要找这个数？因为只有找到这个数，我们才有处理的基准，剩下操作，是需要从当前位置后面的范围内找到比当前位置数大，且是最小大，什么意思？以上面数组而言，我们就可以找到这个数2【索引为4】，然后交换索引1 和 索引4 位置的数，数组变为[2,2,4,3,1,1]，最后将索引【2】到最后位置之间的数进行排序，最终变为：[2,2,1,1,3,4]。
     *
     * 作者：mingshan
     * 链接：https://leetcode-cn.com/problems/next-permutation/solution/cong-hou-wang-qian-xun-zhao-pai-xu-by-mi-3fwk/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }

        // 需要从后往前遍历，需要以当前位置为基准，找到上一个元素，下一个元素，这三者以当前排列为基准的下一个排列；
        // 如果没有出现，那么让整个数组反转

        int len = nums.length;

        boolean exist = false;

        // 从倒数第二个开始
        for (int i = len - 2; i >= 0; i--) {
            int currValue = nums[i];
            // 如果上一个
            if (currValue < nums[i + 1]) {
                 // 找到最小比当前大的值，与当前位置交换，然后其他倒序排列
                int lastSecondIndex = i + 2;
                if (lastSecondIndex <= len - 1) {
                    int index = i;

                    for (int j = i + 1; j < len; j++) {
                        int tempValue = nums[j];
                        if (tempValue > currValue) {
                            if (index == i) {
                                index = j;
                                continue;
                            }

                            if (nums[index] > tempValue) {
                                index = j;
                            }
                        }
                    }

                    if (index != i) {
                        swap(nums, i, index);

                        sort(nums, i + 1, nums.length);
                    }

                } else {
                    swap(nums, i, i + 1);
                }

                exist = true;
                break;
            }
        }

        if (!exist) {
            // 反转数组
            reverse(nums, 0, nums.length - 1);
        }
    }

    private static void sort(int[] nums, int begin, int end) {
        Arrays.sort(nums, begin, end);
    }

    private static void reverse(int[] nums, int begin, int end) {
        int length = end - begin + 1;
        int mid = length / 2;

        for (int i = 0; i < mid; i++) {
            if (nums[begin + i] > nums[end - i]) {
                swap(nums, begin + i, end - i);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
