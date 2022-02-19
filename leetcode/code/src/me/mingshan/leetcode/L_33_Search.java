package me.mingshan.leetcode;

/**
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 数组的旋转
 * https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 *
 * @author hanjuntao
 * @date 2022/1/14
 */
public class L_33_Search {

    // TODO

    public static void main(String[] args) {
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};

        //System.out.println(search2(nums1, 6));

        int[] nums2 = {4, 5, 6, 7, 8, 0, 1};

        //System.out.println(search2(nums2, 1));

        int[] nums3 = {1, 3};

        //System.out.println(search2(nums3, 4));

        int[] nums4 = {4, 5, 6, 7, 8, 1, 2,3};

        System.out.println(search2(nums4, 8));
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int result = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }

        return result;
    }

    /**
     * 二分
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        return search2(nums, target, 0, nums.length - 1);
    }

    public static int search2(int[] nums, int target, int begin, int end) {
        if (begin == end) {
            return nums[begin] == target ? begin : -1;
        }

        if (end - begin == 1) {
            if (nums[begin] == target) {
                return begin;
            }
            if (nums[end] == target) {
                return end;
            }

            return -1;
        }

        int mid = (begin + end) / 2;

        if (nums[mid] == target) {
            return mid;
        }

        int first = nums[0];
        int last = nums[nums.length - 1];

        // 开启条件：begin 的值大于等于 first

        // 如果目标值，小于第一个数，那么必然也要从右边区域寻找
        // 例如：4,5,6,7,8,0,1 target = 1
        if (nums[begin] >= first && first > target) {
            return search2(nums, target, mid + 1, end);
        }

        // 开启条件：

        // 如果目标值，小于第一个数，那么必然也要从左边区域寻找
        // 例如： 6,7,0,1,3  target = 6
        if (nums[end] <= last && last < target) {
            return search2(nums, target, begin, mid - 1);
        }

        if (nums[mid] > target) {
            return search2(nums, target, begin, mid - 1);
        } else if (nums[mid] < target) {
            return search2(nums, target, mid + 1, end);
        }

        return -1;
    }
}
