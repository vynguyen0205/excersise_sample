package sample2;

import java.util.Arrays;

public class SolvingPuzzle {
    int size;
    int[][] E;

    public SolvingPuzzle(int[][] e) {
        this.E = e;
        this.size = e.length;
    }

    // Complexity: 0(n)
    public int sequentialOrder() {
        int total = 0;

        for (int i = 0; i < size-1; i++) {
           total += E[i][i+1];
        }
        return total;
    }

    // Complexity: 0(n^2)
    public int greedyOrder() {
        int total = 0;
        boolean[] solved = new boolean[size];
        solved[0] = true;
        int currentIdx = 0;
        int solvedCount = 1;

        while (solvedCount<size) {
            int shortest = Integer.MAX_VALUE;
            int shortestIdx = -1;

            for (int i = 0; i < size; i++) {
                if (currentIdx == i || solved[i]) continue;

                if (E[currentIdx][i] < shortest) {
                    shortest = E[currentIdx][i];
                    shortestIdx = i;
                }
            }

            solved[shortestIdx] = true;
            currentIdx = shortestIdx;
            total+= shortest;

            solvedCount++;
        }

        return total;
    }

    // Complexity: 0()
    public int optimalOrder() {
        min = Integer.MAX_VALUE;

        int[] input = new int[size];
        int[] current = new int[size];
        boolean[] taken = new boolean[size];

        for (int i = 0; i < size; i++) {
            input[i] = i;
        }

        permute(input, taken, current, 0);
        return min;
    }

     void permute(int[] input, boolean[] taken, int[] current, int idx) {
        if (idx == input.length) {
            process(current);
            return;
        }

        for (int i = 0; i < input.length; i++) {
            if (taken[i]) {
                continue;
            }
            current[idx] = input[i];
            taken[i] = true;
            permute(input, taken, current, idx + 1);
            taken[i] = false;
        }
    }

    int min = Integer.MAX_VALUE;
    private void process(int[] solution) {
        int total = 0;
        for (int i = 0; i < size-1; i++) {
            //System.out.print(solution[i]);
            total += E[solution[i]][solution[(i+1)]];
        }
        //System.out.print(solution[size-1] + ": "+ total);

        if (total < min) {
            min = total;
        }
    }

    public static void main(String[] args) {
        int[][] e = new int[][] {
                {0,1,5, 4,5},
                {11,0,9,6,3},
                {6,3,0,5,6},
                {16,13,10,0,16},
                {3,13,10,6,0},
        };

        SolvingPuzzle puzzle = new SolvingPuzzle(e);

        System.out.println("Sequential order: " + puzzle.sequentialOrder());
        System.out.println("Greedy order: " + puzzle.greedyOrder());
        System.out.println("Optimal order: " + puzzle.optimalOrder());
    }

}
