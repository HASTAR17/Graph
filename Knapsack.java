public class Knapsack {

    // Function to solve the 0/1 Knapsack problem
    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length; // Number of items
        int[][] dp = new int[n + 1][capacity + 1]; // DP table

        // Build the DP table
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    // Include the current item or exclude it
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                } else {
                    // Exclude the current item
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity]; // Maximum value that can be carried
    }

    public static void main(String[] args) {
        // Example input
        int[] weights = {2, 3, 4, 5}; // Weights of items
        int[] values = {3, 4, 5, 6};  // Values of items
        int capacity = 5;             // Capacity of the knapsack

        // Solve the problem
        int maxValue = knapsack(weights, values, capacity);

        // Print the result
        System.out.println("Maximum value in the knapsack: " + maxValue);
    }
}
