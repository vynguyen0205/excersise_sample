package w7;

import java.util.Stack;

public class Problem4 {
    String[] courses;
    int[][] matrix;

    public Problem4(String[] courses, int[][] matrix) {
        this.courses = courses;
        this.matrix = matrix;
    }

    public String optimalOrder() {
        boolean[] added = new boolean[courses.length];
        Stack<String> stack = new Stack<>();

        //For all courses
        for (int i = 0; i < courses.length; i++) {
            if (added[i]) continue;
            process(stack, added, i);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.append(stack.pop() + ", ");
        }
        return sb.toString();
    }

    public void process(Stack<String> stack, boolean[] added, int idx) {
        added[idx] = true;

        // Find all inbounds
        for (int j = 0; j < courses.length; j++) {
            if (idx == j) continue;
            if (added[j]) continue;

            if (matrix[j][idx] == 1) {
                process(stack, added, j);
            }

        }
        stack.add(courses[idx]);

    }

    public static void main(String[] args) {
        String[] courses = {"Course 0", "Course 1", "Course 2", "Course 3", "Course 4"};
        int[][] matrix = {
                {0, 0, 0, 0, 1},
                {1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        };

        Problem4 p = new Problem4(courses, matrix);

        System.out.printf(p.optimalOrder());
    }
}
