package sample4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Lab {
    String name;
    double x;
    double y;

    public Lab(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}

public class RoadSystem {
    List<Lab> labs = new ArrayList<>();
    int size = 0;

    //Complexity: O()
    public void addLab(Lab l) {
        labs.add(l);
        size++;
    }

    //Complexity: O()
    public double simpleLength() {
        double total = 0;
        for (int i = 0; i < size - 1; i++) {
            Lab l1 = labs.get(i);
            Lab l2 = labs.get(i + 1);
            total += calcDistance(l1, l2);
        }

        return total;
    }

    //Complexity: O()
    public double optimalLength() {
        // Calculate distance matrix
        double[][] matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = calcDistance(labs.get(i), labs.get(j));
            }
        }

        double length = 0;
        double[] distances = new double[size];
        boolean[] added = new boolean[size];

        Arrays.fill(distances, Double.MAX_VALUE);
        distances[0] = 0;
        int nodeCount = 0;

        while (nodeCount < size) {
            double shortest = Double.MAX_VALUE;
            int shortestNode = -1;

            for (int i = 0; i < size; i++) {
                if (added[i]) continue;

                if (shortest> distances[i]) {
                    shortest = distances[i];
                    shortestNode = i;
                }
            }

            added[shortestNode] = true;
            length+= distances[shortestNode];
            nodeCount++;

            for (int i = 0; i < size; i++) {
                if (added[i]) continue;

                if (matrix[shortestNode][i] > 0) {
                    if (distances[i] > matrix[shortestNode][i]) {
                        distances[i] = matrix[shortestNode][i];
                    }
                }
            }
        }

        return length;
    }

    private double calcDistance(Lab l1, Lab l2) {
        return Math.sqrt((l1.x - l2.x) * (l1.x - l2.x) + (l1.y - l2.y) * (l1.y - l2.y));
    }

    public static void main(String[] args) {
        Lab l1 = new Lab("Advanced AI", 0.0, 0.0);
        Lab l2 = new Lab("Cyber Security", 10, 0);
        Lab l3 = new Lab("IoT", 0, 10);
        RoadSystem rs = new RoadSystem();
        rs.addLab(l1);
        rs.addLab(l2);
        rs.addLab(l3);
        System.out.println("Simple length: " + rs.simpleLength());  // return 24.142
        System.out.println("Optimal length: " + rs.optimalLength());  // return 20

    }
}