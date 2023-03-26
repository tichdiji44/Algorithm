package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {
        // 先定义给逆波兰表达式
        // (3+4)x5-6 => 3 4 + 5 x 6 - => 29
        // (330+4)x5-6 => 3 4 + 5 x 6 - => 164
        // 4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / +
        // 测试
        // 说明为了方便，逆波兰表达式的数字和符号使用空格隔开
        //        String suffixExpression = "4 * 5 - 8 + 60 + 8 2 /";
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        // 思路
        // 1. 先将 "3 4 + 5 x 6 - " => 放到ArrayList
        // 2. 将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println("rpnList=" + list);
        int res = calculate(list);
        System.out.println("计算的结果是=" + res);
    }

    // 将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        // 将suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    // 完成对逆波兰表达式的运算
	    /*
	    1)从左至右扫描，将3和4压入堆栈;
	    2)遇到+运算符，因此弹出4和3(4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈;
	    3)将5入栈;
	    4)接下来是×运算符,因此弹出5和7，计算出7×5=35，将35入栈;
	    5)将6入栈,
	    6)最后是-运算符,计算出35-6的值,即29，由此得出最终结果
	     */
    public static int calculate(List<String> ls) {
        // 创建给栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        // 遍历ls
        for (String item : ls) {
            // 这里使用正则表达式来取出数
            if (item.matches("\\d+")) { // 匹配的是多位数
                // 入栈
                stack.push(item);
            } else {
                // pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                // 把res入栈
                stack.push("" + res);
            }
        }
        // 最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }

}