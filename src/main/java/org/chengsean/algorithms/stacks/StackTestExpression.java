package org.chengsean.algorithms.stacks;

import org.chengsean.algorithms.util.NumberUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

/**
 * 栈的用法：表达式运算
 * @auther 程绍壮
 * @date 2019-06-09 22:09
 */
public class StackTestExpression {
    private static final int BRACKETS_LEVEL = 3;
    private static final int MULTIPLY_DIVIDE_LEVEL = 2;
    private static final int PLUS_SUBTRACT_LEVEL = 1;
    private static final int EXP_OPEN_CLOSE_LEVEL = 0;
    // 加法操作符
    private static final char PLUS = '+';
    // 减法操作符
    private static final char SUBTRACT = '-';
    // 乘法操作符
    private static final char MULTIPLY = '*';
    // 除法操作符
    private static final char DIVIDE = '/';
    // 表达式起始和结束符
    private static final char EXP_OPEN_CLOSE = '#';
    // 表达式
    private static final String EXP = "8*7+(5-6/3)*4";
    // 后缀表达式
    private StringBuffer suffixExp;
    // 操作符栈
    private Stack<Character> operatorStack;
    // 操作数栈
    private Stack<Object> operandStack;

    @Before
    public void initObjects() {
        this.suffixExp = new StringBuffer(EXP.length());
        this.operatorStack = new Stack<>();
        this.operatorStack.push(EXP_OPEN_CLOSE);
        this.operandStack = new Stack<>();
    }

    @Test
    public void testExp2SuffixExp() {
        char[] chars = EXP.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            // 处理操作数
            if (NumberUtils.isAsciiNumeric(ch))
                this.suffixExp.append(ch);
            else // 处理操作符
                this.processOperator(ch);
            if (i == chars.length - 1)
                this.processOperator(EXP_OPEN_CLOSE);
        }
        System.out.println("运算表达式："+EXP+" 转换为后缀运算表达式："+ suffixExp);
        this.testCalculateSuffixExp();
    }

    /***
     * 计算前缀表达式
     */
    private void testCalculateSuffixExp() {
        System.out.println("开始计算后缀运算表达式："+ suffixExp);
        char[] chars = suffixExp.toString().toCharArray();
        for (char ch : chars) {
            if (NumberUtils.isAsciiNumeric(ch))
                this.operandStack.push(ch);
            else
                this.calculateOperand(ch);
        }
        Double aDouble = Double.valueOf(this.operandStack.pop().toString());
        System.out.println("计算后缀运算表达式完成，得："+ aDouble.intValue());
    }

    private void calculateOperand(char ch) {
        if (!this.operandStack.isEmpty()) {
            double operandNumber2 = 0L;
            double operandNumber1 = 0L;
            char operandChar2 = (Character) this.operandStack.pop();
            char operandChar1 = (Character)this.operandStack.pop();
            System.out.println(Double.isNaN(0));
            if (NumberUtils.isAsciiNumeric(operandChar2))
                operandNumber2 = Double.valueOf(String.valueOf(operandChar2));
            if (NumberUtils.isAsciiNumeric(operandChar1))
                operandNumber1 = Double.valueOf(String.valueOf(operandChar1));
            if (ch == PLUS)
                this.operandStack.push(operandNumber1 + operandNumber2);
            if (ch == SUBTRACT)
                this.operandStack.push(operandNumber1 - operandNumber2);
            if (ch == MULTIPLY)
                this.operandStack.push(operandNumber1 * operandNumber2);
            if (ch == DIVIDE){
                if (operandNumber1 == 0)
                    throw new IllegalArgumentException(String.valueOf(operandNumber1));
                this.operandStack.push(operandNumber1 / operandNumber2);
            }
        }
    }
    @After
    public void clear() {
        if (suffixExp != null)
            this.suffixExp.setLength(0);
        if (this.operatorStack != null)
            this.operatorStack.clear();
    }

    private void processOperator(char charByExp) {
        //将表达式的第一个操作符入栈
        int charByExpLevel, charInStackLevel;
        char charInStack = this.operatorStack.peek();
        if (charInStack == EXP_OPEN_CLOSE) {
            this.operatorStack.push(charByExp);
            return;
        }

        charByExpLevel = this.getLevel(charByExp);
        charInStackLevel = this.getLevel(charInStack);
        if (charByExpLevel <= charInStackLevel && charByExp != EXP_OPEN_CLOSE) {
            if (charInStackLevel != BRACKETS_LEVEL)
                this.suffixExp.append(this.operatorStack.pop());
            this.operatorStack.push(charByExp);
            return;
        }

        if (this.isClosingParentheses(charByExp)) {
            while (!this.isOpeningParentheses(this.operatorStack.peek()))
                this.suffixExp.append(this.operatorStack.pop());
            this.operatorStack.pop();
            return;
        }
        if (charByExp == EXP_OPEN_CLOSE) {
            while (this.operatorStack.peek() != EXP_OPEN_CLOSE)
                this.suffixExp.append(this.operatorStack.pop());
            this.operatorStack.pop();
            return;
        }
        this.operatorStack.push(charByExp);

    }


    private boolean isClosingParentheses(char char1) {
        return char1 == StackTestBrackets.CLOSING_PARENTHESES.charAt(0);
    }
    private boolean isOpeningParentheses(char char1) {
        return char1 == StackTestBrackets.OPENING_PARENTHESES.charAt(0);
    }

    private int getLevel(char ch) {
        if (this.isParentheses(ch))
            return BRACKETS_LEVEL;
        if (ch == MULTIPLY || ch == DIVIDE)
            return MULTIPLY_DIVIDE_LEVEL;
        if (ch == PLUS || ch == SUBTRACT)
            return PLUS_SUBTRACT_LEVEL;
        if (ch == EXP_OPEN_CLOSE)
            return EXP_OPEN_CLOSE_LEVEL;
        throw new IllegalArgumentException("无法识别符号："+ch+" 目前仅支持+、-、x、/、(、)等符号。");
    }


    private boolean isParentheses(char... chars) {
        boolean matchAll = false;
        for (char ch : chars) {
            matchAll = ch == StackTestBrackets.OPENING_PARENTHESES.charAt(0)
                    || ch == StackTestBrackets.CLOSING_PARENTHESES.charAt(0);
        }
        return matchAll;
    }
}
