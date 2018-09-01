package Hard;


import java.util.Stack;

public class MaximalRectangle {

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        System.out.println(new MaximalRectangle().maxRectangleInHistogram(
                new int[]{2, 1, 2},
                s
        ));
    }

    public int maximalRectangle(char[][] matrix) {
        int maxRectangle = 0;
        if (matrix == null || matrix.length == 0) {
            return maxRectangle;
        }


        int[] dp = new int[matrix[0].length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = matrix[0][i] - '0';
        }
        Stack<Integer> s = new Stack<>();
        maxRectangle = maxRectangleInHistogram(dp, s);

        for(int i = 1; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                int cur = matrix[i][j] - '0';
                if(cur == 1) {
                    dp[j]++;
                } else {
                    dp[j] = 0;
                }
            }

            maxRectangle = Math.max(maxRectangle, maxRectangleInHistogram(dp, s));
        }

        return maxRectangle;
    }

    public int maxRectangleInHistogram(int[] heights, Stack<Integer> s) {
        s.clear();
        int maxRectangle = 0;

        for (int i = 0; i < heights.length; i++) {
            int cur = heights[i];
            maxRectangle = Math.max(maxRectangle, cur);
            if (s.isEmpty() || heights[s.peek()] <= cur) {
                s.push(i);
                continue;
            }

            while (!s.isEmpty()) {
                int topElement = heights[s.pop()];
                int prevIndex = s.isEmpty() ? 0 : s.peek() + 1;
                int width = i - prevIndex;
                maxRectangle = Math.max(maxRectangle, width * topElement);

                if (!s.isEmpty() && heights[s.peek()] <= cur) {
                    break;
                }
            }

            s.push(i);
        }

        while (!s.isEmpty()) {
            int topElement = heights[s.pop()];
            int prevIndex = s.isEmpty() ? 0 : s.peek() + 1;
            int width = heights.length - prevIndex;
            maxRectangle = Math.max(maxRectangle, width * topElement);
        }

        return maxRectangle;
    }


}
