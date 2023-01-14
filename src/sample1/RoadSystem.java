package sample1;

import java.lang.reflect.Array;
import java.util.Arrays;

class Lab {
    String name;
    double x;
    double y;

    Lab(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
public class RoadSystem {

    int MAX_SIZE = 100;
    Lab[] labs = new Lab[MAX_SIZE];
    int size = 0;

    // Complexity: O(1)
    public void addLab(Lab l) {
        labs[size] = l;
        size++;
    }

    // Complexity: O(N)
    public double simpleLength() {
        double total = 0;
        for(int i=0; i<size-1; i++) {
            Lab current = labs[i];
            Lab next = labs[i+1];
            total += calDistance(current, next);
        }
        return total;
    }

    // Complexity: O(n^2)
    public double optimalLength() {
        double optimalLength = 0;

        double[][] matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = calDistance(labs[i], labs[j]);
            }
        }

        int numberOfLabsAdded = 0;
        boolean[] addedLabs = new boolean[size];
        double[] distances = new double[size];

        Arrays.fill(distances, Double.MAX_VALUE);
        distances[0] = 0;


        while (numberOfLabsAdded < size) {
            double shortest = Double.MAX_VALUE;
            int shortestPosition = -1;

            for (int i = 0; i < size; i++) {
                if (addedLabs[i]) continue;

                if (distances[i] < shortest) {
                    shortestPosition = i;
                    shortest = distances[i];
                }
            }

            addedLabs[shortestPosition] = true;
            numberOfLabsAdded++;
            optimalLength += distances[shortestPosition];

            for (int i = 0; i < size; i++) {
                if (addedLabs[i]) continue;

                if (distances[i] > matrix[shortestPosition][i]) {
                    distances[i] = matrix[shortestPosition][i];
                }
            }
        }

        return optimalLength;
    }


    private double calDistance(Lab l1, Lab l2) {
        return Math.sqrt((l1.x-l2.x)*(l1.x-l2.x) + (l1.y-l2.y)*(l1.y-l2.y));
    }

    public static void main(String[] args) {
        Lab l1 = new Lab("Advanced AI", 0.0, 0.0);
        Lab l2 = new Lab("Cyber Security", 10, 0);
        Lab l3 = new Lab("IoT", 0, 10);
        RoadSystem rs = new RoadSystem();
        rs.addLab(l1);
        rs.addLab(l2);
        rs.addLab(l3);
        System.out.println("Simple length: " +    rs.simpleLength());  // return 24.142
        System.out.println("Optimal length: " +    rs.optimalLength());  // return 20
    }
}
