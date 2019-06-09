package org.chengsean.algorithms.strings;

import org.junit.Test;

/**
 * 字符串：模式匹配
 * @auther 程绍壮
 * @date 2019-06-09 12:27
 */
public class StringTestKMP {
    /**
     *源字符串
     */
    private static final String SOURCE_STRING = "赵钱孙李，周吴郑王。冯陈褚卫，蒋沈韩杨。朱秦尤许，何吕施张。";
    /**
     *查源字符串子串
     */
    private static final String SEARCH_STRING = "赵钱孙李周";

    /**
     * 模式匹配KMP算法
     * @param
     * @auther 程绍壮
     * @date 2019/6/9 16:23
     */
    @Test
    public void testKPM() {
        char[] sourceChars = SOURCE_STRING.toCharArray();
        char[] searchChars = SEARCH_STRING.toCharArray();
        int k = searchChars.length;
        int numberOfMatches = 0;
        boolean matches = false;
        for (int i = 0; i < sourceChars.length; i++) {
            char sourceChar = sourceChars[i];
            if (!this.searchChar(sourceChar, searchChars) && i + k - 1 < sourceChars.length) {
                numberOfMatches  = numberOfMatches > 0 ? 0 : numberOfMatches;
                i += k - 1;
            }
            else if (matches = ++numberOfMatches == k) {
                break;
            }
        }
        System.out.println("字符串【"+SOURCE_STRING+"】"+(matches ? "包含" :"不包含")+"子字串 【"+SEARCH_STRING+"】");
    }

    private boolean searchChar(char sourceChar, char[] searchChars) {
        for (char searchChar : searchChars)
            if (sourceChar == searchChar)
                return true;
        return false;
    }
}
