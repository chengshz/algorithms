package org.chengsean.algorithms.stacks;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 栈的用法：递归（汉诺塔）
 * @auther 程绍壮
 * @date 2019-06-09 22:10
 */
public class StackTestHanoi {

    private final Map<Character, Stack<Integer>> stackMap = new HashMap<>();

    @Before
    public void init() {
        Stack<Integer> integers = new Stack<>();
        integers.push(3);
        integers.push(2);
        integers.push(1);
        this.stackMap.put('X', integers);
        this.stackMap.put('Y', new Stack<>());
        this.stackMap.put('Z', new Stack<>());
    }


    /**
     *
     * @param n 盘子的数目
     * @param x 源座
     * @param y 辅助座
     * @param z 目的座
     */
    @Test
    public void hanoi(int n, char x, char y, char z) {
        if (n == 1) {
            move(x, z);
        } else {
            hanoi(n - 1, x, z, y);
            move(x, z);
            hanoi(n - 1, y, x, z);

        }
    }

    // Print the route of the movement
    private synchronized void move(char origin, char destination) {
        int number = this.stackMap.get(origin).pop();
        this.stackMap.get(destination).push(number);
        System.out.println(number+"号圆盘从" + origin + "号塔座移到" + destination+"号塔座");
        System.out.println();
    }


//    private String getValues(Stack<Integer> values) {
//        StringBuilder builder = new StringBuilder();
//        int index = 0;
//        while (!values.isEmpty())
//            builder.append(++index == 1 ? values.pop()+"号" : ","+values.pop()+"号");
//        return builder.toString();
//    }

    public static void main(String[] args) {
        StackTestHanoi hanoi = new StackTestHanoi();
        System.out.println("圆盘和塔座初始位置");
        hanoi.stackMap.forEach((key, values) ->
                System.out.println(key+"号塔座，圆盘："+values));

        System.out.println("开始移动圆盘的位置。");
        hanoi.hanoi(3, 'X', 'Y', 'Z');

        System.out.println("圆盘移动后，塔座和圆盘的位置");
        hanoi.stackMap.forEach((key, values) ->
                System.out.println(key+"号塔座，圆盘："+values));
    }
}
