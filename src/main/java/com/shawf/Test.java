package com.shawf;

import java.util.*;

/**
 * @author shawf_lee
 * @date 2020/1/3 10:04
 * Content:
 */
public class Test {
    public static void main(String[] args) {
        int nums1[] = {1, 2, 3};
        int nums2[] = {4, 5, 6, 7, 8};
        //double a=new Test().findMedianSortedArrays(nums1,nums2);
        int nums3[][] = {{1, 6}, {2, 4}, {3, 5}, {7, 12}};
        /*int a[][]=new Test().merge(nums3);
        for(int i=0;i<a.length;i++) {
            for(int j=0;j<2;j++) {
                System.out.print(a[i][j]);
                System.out.print(" ");
            }
            System.out.print("|");
        }*/
        //int nums4[]={3,2,1,0,4};
        //boolean a=new Test().canJump(nums4);
//        String a = new Test().reformat("a0b1c2");
//        System.out.print(a);
        int a=1;
        int b=3;
        int c=33;
        double d = c*(a/b);
        System.out.print(d);
    }

    //中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int a = nums1.length;
        int b = nums2.length;
        int[] nums = new int[a + b];
        if (a == 0) {
            if (b % 2 == 0) {
                return (nums2[b / 2 - 1] + nums2[b / 2]) / 2;
            } else {
                return nums2[b / 2];
            }
        }
        if (b == 0) {
            if (a % 2 == 0) {
                return (nums1[a / 2 - 1] + nums1[a / 2]) / 2;
            } else {
                return nums1[a / 2];
            }
        }
        int count = 0;
        int i = 0;
        int j = 0;
        while (count != (a + b)) {
            if (i == a) {
                while (j != b) {
                    nums[count++] = nums2[j++];
                }
                break;
            }
            if (j == b) {
                while (i != a) {
                    nums[count++] = nums1[i++];
                }
                break;
            }
            if (nums1[i] < nums2[j]) {
                nums[count++] = nums1[i++];
            } else {
                nums[count++] = nums2[j++];
            }
        }
        for (int q = 0; q < count; q++) {
            System.out.println(nums[q]);
        }
        if (count % 2 == 0) {
            double temp = Double.valueOf((nums[count / 2 - 1] + nums[count / 2])) / 2;
            return temp;
        } else {
            return nums[count / 2];
        }
    }

    //给出一个区间的集合，请合并所有重叠的区间。
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int l = intervals[i][0];
            int r = intervals[i][1];
            while (i < intervals.length - 1 && r >= intervals[i + 1][0]) {
                r = Math.max(r, intervals[i + 1][1]);
                i++;
            }
            list.add(new int[]{l, r});
        }
        return list.toArray(new int[list.size()][2]);
    }

    public boolean canJump(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }
        int l = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            if (i <= l) {
                l = Math.max(l, nums[i] + i);
            }
        }

        return l >= nums.length - 1;
    }

    public String reformat(String s) {
        Queue<String> queue1 = new LinkedList<String>();
        Queue<String> queue2 = new LinkedList<String>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                queue1.offer(String.valueOf(s.charAt(i)));
            } else {
                queue2.offer(String.valueOf(s.charAt(i)));
            }
        }
        if (Math.abs(queue1.size() - queue1.size()) > 1) {
            return "";
        }
        String res = "";
        boolean flag = queue1.size() > queue2.size() ? true : false;
        for (int i = 0; i < queue1.size(); i++) {
            if (flag == true) {
                res += queue1.peek();
                res += queue2.peek();
            } else {
                res += queue2.peek();
                res += queue1.peek();
            }
        }
        return res;
    }
}
