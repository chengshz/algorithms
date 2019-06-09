package org.chengsean.algorithms.stacks;

import org.junit.Test;

import java.util.Stack;

/**
 * 栈的用法：检查括弧、中括号、大括号书写格式
 * @auther 程绍壮
 * @date 2019-06-09 21:45
 */
public class StackTestBrackets {

    /**
     *  检查括弧、中括号、大括号书写格式是否正确
     */
    private static final String BRACKETS_QUOTATION_MARKS = "{[(})]";
    /***
     * 左括弧
     */
    static final String OPENING_PARENTHESES = "(";
    /***
     * 右括弧
     */
    static final String CLOSING_PARENTHESES = ")";
    /***
     * 括弧
     */
    private static final String OPENING_CLOSING_PARENTHESES = OPENING_PARENTHESES+CLOSING_PARENTHESES;
    /***
     * 左中括号
     */
    private static final String OPENING_BRACES = "{";
    /***
     * 右中括号
     */
    private static final String CLOSING_BRACES = "}";
    /***
     * 中括号
     */
    private static final String OPENING_CLOSING_BRACES = OPENING_BRACES+CLOSING_BRACES;
    /***
     * 左大括号
     */
    private static final String OPENING_BRACKETS = "[";
    /***
     * 右大括号
     */
    private static final String CLOSING_BRACKETS = "]";

    /***
     * 大括号
     */
    private static final String OPENING_CLOSING_BRACKETS = OPENING_BRACKETS+CLOSING_BRACKETS;

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        int index = 0;
        while (index < 10) {
            stack.add(index == 9 ? index+"" : index + ",");
            index ++;
        }
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty())
            builder.insert(0, stack.pop());
        System.out.println(builder.toString());
    }


    /***
     * 测试括弧，大括号，中括号，单/双引号是否成对出现
     */
    @Test
    public void testBrackets() {
        Stack<String> stack = new Stack<>();
        char[] chars = BRACKETS_QUOTATION_MARKS.toCharArray();
        boolean checked = true;
        for (int i = 0; i < chars.length; i++) {
            try {
                this.processOnePairSymbol(String.valueOf(chars[i]),
                        stack, i == chars.length - 1);
            } catch (Exception ex) {
                ex.printStackTrace();
                checked = false;
                break;
            }
        }
        if (checked)
            System.out.println("该符号【"+ BRACKETS_QUOTATION_MARKS +"】书写格式正确");
    }

    /**
     * 遇到左括号（括号起始符）进栈，否则将左括号出栈与右括号（括号结束符）进行匹配
     * 若匹配不成功，则抛出异常，否则将下一个字符进行上一步操作
     * @param symbol
     * @param stack
     * @param endPosition
     */
    private void processOnePairSymbol(String symbol, Stack<String> stack, boolean endPosition) {
        if (this.isOpening(symbol))
            stack.push(symbol);
        else
            this.removeSymbol(symbol, stack);
        if (endPosition)
            this.finishedCondition(stack);
    }

    private void finishedCondition(Stack<String> stack) {
        if (!stack.isEmpty())
            throw new IllegalArgumentException("符号'"+stack.peek()+"'在'"+BRACKETS_QUOTATION_MARKS+"'中没有正确的关闭");
    }

    private boolean isOpening(String symbol) {
        return symbol.equals(OPENING_BRACES)
                || symbol.equals(OPENING_BRACKETS)
                || symbol.equals(OPENING_PARENTHESES);
    }

    private void removeSymbol(String symbol, Stack<String> stack) {
        if (this.containsOnePairSymbol(stack.peek(), symbol))
            stack.pop();
    }

    private boolean containsOnePairSymbol(String symbol, String symbol2) {
        String symbols = symbol+symbol2;
        return symbols.equals(OPENING_CLOSING_BRACES)
                || symbols.equals(OPENING_CLOSING_BRACKETS)
                || symbols.equals(OPENING_CLOSING_PARENTHESES);
    }
}
