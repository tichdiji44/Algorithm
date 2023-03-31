package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {

    public static void main(String[] args) {
        // int[] arr = {101, 34, 119, 1, -1, 89};
        // 创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000)数
        }
        System.out.println("排序前");
        // System.out.println(Arrays.toString(arr));

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);
        insertSort(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
        insertSort(arr);
    }

    // 插入排序
    public static void insertSort(int[] arr) {
        // 使用逐步推导的方式来讲解，便于理解
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            arr[insertIndex + 1] = insertVal;
            // System.out.println("第" + i + "轮插入");
            // System.out.println(Arrays.toString(arr));
        }
        /*
        // 第1轮 {101, 34, 119, 1}; => {34, 101, 119, 1}

        // 定义待插入的数
        int insertVal = arr[1];
        int insertIndex = 1 - 1; // 即arr[1]的前面这个数的下标

        // 给insertVal找到插入的位置
        // 说明
        // 1. insertIndex >= 0保证在给insertVal找插入位置，不越界
        // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
        // 3. 就需要将arr[insertIndex]后移
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex]; // arr[insertIndex]
            insertIndex--;
        }
        // 当退出while循环时，说明插入的位置找到，insertIndex + 1
        // 举例：理解不了，我们一会debug
        arr[insertIndex + 1] = insertVal;
        System.out.println("第1轮插入");
        System.out.println(Arrays.toString(arr));

        // 第2轮
        insertVal = arr[2];
        insertIndex = 2 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }

        arr[insertIndex + 1] = insertVal;
        System.out.println("第2轮插入");
        System.out.println(Arrays.toString(arr));

        // 第2轮
        insertVal = arr[3];
        insertIndex = 3 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }

        arr[insertIndex + 1] = insertVal;
        System.out.println("第3轮插入");
        System.out.println(Arrays.toString(arr));
        */
    }

}
