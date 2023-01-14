package sample3;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

class FitnessExercise {
    String name;
    int duration;
    int fitness;

    FitnessExercise(String n, int d, int f) {
        this.name = n;
        this.duration = d;
        this.fitness = f;
    }
}

public class ExerciseManagement {
    ArrayList<FitnessExercise> list = new ArrayList<>();
    public void add(FitnessExercise ex) {
        list.add(ex);
    }

   public  String exercises(List<FitnessExercise> lookup) {
        StringBuilder sb = new StringBuilder();
       for (int i = 0; i < lookup.size(); i++) {
           for (int j = 0; j < list.size(); j++) {
               FitnessExercise e1 = lookup.get(i);
               FitnessExercise e2 = list.get(j);
                if (e1.name.equals(e2.name)) {
                    sb.append(e1.name + ", ");
                }
           }
       }

        return sb.toString();
    }

   public   List<FitnessExercise> optimalExercises(int N) {
        boolean[] selected = new boolean[list.size()];
        max = 0;
        optimalList = null;

        subset(list, selected, 0, N);

        if (max == 0) {
            return null;
        }
        return optimalList;

    }

     void subset(ArrayList<FitnessExercise> input, boolean[] selected, int idx, int N) {
        if (idx == input.size()) {
            process(input, selected, N);
            return;
        }

        // Not selected
        selected[idx] = false;
        subset(input, selected, idx + 1, N);

        // Selected
        selected[idx] = true;
        subset(input, selected, idx + 1, N);
    }

    private int max = 0;
    private List<FitnessExercise> optimalList;
     void process(ArrayList<FitnessExercise> set, boolean[] selected, int N) {
        int total = 0;
         List<FitnessExercise> temp = new ArrayList<>();

        for (int i = 0; i < set.size(); i++) {
            if (selected[i]) {
                total+= set.get(i).fitness;
                temp.add(set.get(i));
            }
        }

        if (total > N) return;
        if (total > max) {
            max = total;
            optimalList = temp;
        }
    }


    public static void main(String[] args) {
        FitnessExercise e1 = new FitnessExercise("Swimming", 30, 100);  // duration is given before fitness
        FitnessExercise e2 = new FitnessExercise("Football", 45, 120);
        FitnessExercise e3 = new FitnessExercise("Table tennis", 60, 150);
        ExerciseManagement mgmt = new ExerciseManagement();
        mgmt.add(e1);
        mgmt.add(e2);
        mgmt.add(e3);

        ArrayList<FitnessExercise> findParams = new ArrayList<FitnessExercise>();
        findParams.add(e1);
        findParams.add(e3);
        System.out.println("Find exercises: \n" + mgmt.exercises(findParams));  // return "Swimming, Football"
        System.out.println();

        List<FitnessExercise> optimalExercises = mgmt.optimalExercises(250);
        System.out.println("Optimal Exercises: ");
        for (int i = 0; i < optimalExercises.size(); i++) {
            System.out.print(optimalExercises.get(i).name + ", ");
        }
     }
}
