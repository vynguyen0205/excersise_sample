package w9;

public class Problem4 {
    public static boolean isBipartite(int[][] matrix) {
        int n = matrix.length;
        boolean[] visited = new boolean[n];
        char[] values = new char[n];  // contains either 'D', 'A', or '' (at the beginning)
        boolean res = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                values[i] = 'A';
                visited[i] = true;
                res = res && DFS(matrix, i, visited, values);
            }
        }
        return res;
    }

    public static boolean DFS(int[][] matrix, int current, boolean[] visited, char[] values) {
        // get all neighbors
        int n = visited.length;
        boolean res = true;
        for (int i = 0; i < n; i++) {
            if (matrix[current][i] != 0) {
                if (!visited[i]) {
                    if (values[current] == 'A') {
                        values[i] = 'D';
                    } else {
                        values[i] = 'A';
                    }
                    visited[i] = true;
                    res = res && DFS(matrix, i, visited, values);
                } else {
                    if (values[i] == values[current]) {
                        return false;
                    }
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
       // one is friend with two others => true
        System.out.println(isBipartite(new int[][] {
                {0, 1, 1},
                {1, 0, 0},
                {1, 0, 0}
        }));
    }
}
