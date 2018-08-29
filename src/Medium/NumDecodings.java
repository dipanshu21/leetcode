package Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/decode-ways/description/
 */
class Solution {
    public static void main(String[] a) {
        List<String> testCases = new ArrayList<>();
        testCases.add("0101");
        testCases.add("101");
        testCases.add("12321");
        testCases.add("1230");
        for (String s : testCases) {
            System.out.println(s + ": " + numDecodings(s));
        }
    }

    private static int numDecodings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int len = s.length();
        int prevNum = s.charAt(0) - '0';
        int possibleCombosTillPrevPrev = prevNum != 0 ? 1 : 0;

        if (possibleCombosTillPrevPrev == 0) {
            return possibleCombosTillPrevPrev;
        }

        if (len == 1) {
            return possibleCombosTillPrevPrev;
        }

        int curNum = s.charAt(1) - '0';
        int combinedNumPrevAndPrevPrev = prevNum * 10 + curNum;
        int possibleCombosTillPrev = (curNum != 0 ? possibleCombosTillPrevPrev : 0) + (combinedNumPrevAndPrevPrev > 26 ? 0 : 1);
        prevNum = curNum;

        if (possibleCombosTillPrev == 0) {
            return possibleCombosTillPrev;
        }

        for (int i = 2; i < len; i++) {
            curNum = s.charAt(i) - '0';
            int combinedNumPrevAndCur = prevNum != 0 ? (prevNum * 10) + curNum : 27;
            int possibleCombosTillCur = (curNum != 0 ? possibleCombosTillPrev : 0) + (combinedNumPrevAndCur > 26 ? 0 : possibleCombosTillPrevPrev);

            if (possibleCombosTillCur == 0) {
                return 0;
            }

            prevNum = curNum;
            possibleCombosTillPrevPrev = possibleCombosTillPrev;
            possibleCombosTillPrev = possibleCombosTillCur;
        }

        return possibleCombosTillPrev;
    }
}