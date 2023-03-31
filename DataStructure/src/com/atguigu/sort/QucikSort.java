package com.atguigu.sort;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QucikSort {

    public static void main(String[] args) {
        // int[] arr = {-9, 78, 0, 23, -567, 70, -1, 900, 4561};

        // 测试快排的执行速度
        // 创建要给80000个的随机的数组
        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000)数
        }
        System.out.println("排序前");
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);
        quickSort(arr, 0, arr.length - 1);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);


        //System.out.println("arr=" + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left; // 左下标
        int r = right; // 右下标
        // pivot 中轴
        int pivot = arr[(left + right) / 2];
        int temp = 0; // 临时变量，作为交换时使用
        // while循环的目的是让比pivot值小放到左边
        // 比pivot值大放到右边
        while (l < r) {
            // 在pivot的左边一直找，找到大于pivot值，才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            // 在pivot的右边一直找，找到小于等于pivot值，才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            // 如果l>=r说明pivot的左右两的值，一ing按照左边全部是小于等于Pivot值，右边全部是大于等于pivot值
            if (l >= r) {
                break;
            }
            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            // 如果交换完后，发现这个arr[l] == pivot值相等，r--，前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            // 如果交换完后，发现这个arr[r] == pivot值相等，l++，前移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        // 如果l==r，必须l++，r--，否则出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        if (left < r) {
            quickSort(arr, left, r);
        }
        if (right > l) {
            quickSort(arr, l, right);
        }
    }

}
