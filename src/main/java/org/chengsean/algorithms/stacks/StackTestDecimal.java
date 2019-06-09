package org.chengsean.algorithms.stacks;

import org.junit.Test;

import java.util.Stack;

/**
 *  栈的用法：进制转换
 * @auther 程绍壮
 * @date 2019-06-09 21:55
 */
public class StackTestDecimal {

    /***
     * 进制转换
     * @return
     */
    @Test
    public void convertDecimal() {
        int hexDecimal = 10;
        int octDecimal = 8;
        int binDecimal = 2;
        int hexNumber = 8;
        int octNumber = 8080;
        int binNumber = 10010;
        System.out.println(binDecimal+"进制转换"+hexDecimal+"进制: "+toDecimal(binNumber, hexDecimal));
        System.out.println(binDecimal+"进制转换"+octDecimal+"进制: "+toDecimal(binNumber, octDecimal));
        System.out.println(hexDecimal+"进制转换"+binDecimal+"进制: "+toDecimal(hexNumber, binDecimal));
        System.out.println(octDecimal+"进制转换"+binDecimal+"进制: "+toDecimal(octNumber, binDecimal));
    }

    private String toDecimal(int number, int decimal) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        while (number > 0) {
            //System.out.println("mod : "+mod);
            stack.push( number % decimal);
            number /= decimal;
            //System.out.println("divide : "+number);
        }
        while (!stack.isEmpty())
            result.append(stack.pop());
        return result.length() == 0 ? "0" : result.toString();
    }
}
