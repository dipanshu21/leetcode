package Medium;

public class MaximumSquares {
    public int maximalSquare(char[][] matrix) {
        int maxSqr = 0;

        if (matrix == null || matrix.length == 0) {
            return maxSqr;
        }

        int[][] dp = new int[matrix.length][matrix[0].length];
        boolean isOnePresent = false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                dp[i][j] = matrix[i][j] - '0';
                if (dp[i][j] == 1) {
                    isOnePresent = true;
                }
            }
        }

        if(!isOnePresent) {
            return 0;
        }

        maxSqr = 1;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if(dp[i][j] == 1) {
                    int min = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]);
                    dp[i][j] = min + 1;
                    maxSqr = Math.max(maxSqr, dp[i][j]);
                }
            }
        }

        for(int i = 0; i < dp.length; i++) {
            for(int  j = 0; j < dp[i].length; j++) {
                System.out.println(dp[i][j] + "\t");
            }
            System.out.println();
        }

        return maxSqr*maxSqr;
    }
}
