package sample1;

class Task {
    String name;
    boolean status;

    Task(String name, boolean status) {
        this.name = name;
        this.status = status;
    }
}

public class TaskCollection {

    final int size = 2027;
    Task[] tasks = new Task[size];

    int calString(String name) {
        int sum = 0;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == ' ') continue;
            sum += name.charAt(i) - 'A';
        }
        return sum % size;
    }

    // Complexity: O(1)
    public int calcLocation(Task t) {
        return calString(t.name);
    }

    // Complexity: O(1)
    public int addTask(Task t) {
        int location = calcLocation(t);

        while (tasks[location] != null) {
            location++;
            if (location == size) {
                location = 0;
            }
        }
        tasks[location] = t;
        return location;
    }

    // Complexity: O(1)
    public Task getTask(String name) {
        int location = calString(name);

        while (tasks[location] != null && !tasks[location].name.equals(name)) {
            location++;
            if (location == size) {
                location = 0;
            }
        }

        if (tasks[location]!= null && !tasks[location].status) return null;
        return tasks[location];
    }

    public static void main(String[] args) {
        Task t1 = new Task("GET DI", true);
        Task t2 = new Task("GET HD", false);
        Task t3 = new Task("GET D  I", true);
        Task t4 = new Task("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZB", true);
        Task t5 = new Task("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZBA", true);

        TaskCollection taskCol = new TaskCollection();

        System.out.println("Location t1: " + taskCol.calcLocation(t1));  // return 40
        System.out.println("Location t2: " + taskCol.calcLocation(t2));  // return 39
        System.out.println("Location t3: " + taskCol.calcLocation(t3));  // return 40
        System.out.println("Location t4: " + taskCol.calcLocation(t4));  // return 2026
        System.out.println("Location t5: " + taskCol.calcLocation(t5));  // return 2026

        System.out.println();
        System.out.println("Add t1: " + taskCol.addTask(t1));  // return 40
        System.out.println("Add t2: " + taskCol.addTask(t2));  // return 39
        System.out.println("Add t3: " + taskCol.addTask(t3));  // return 41
        System.out.println("Add t4: " + taskCol.addTask(t4));  // return 2026
        System.out.println("Add t5: " + taskCol.addTask(t5));  // return 0

        Task getT1 = taskCol.getTask("GET DI");  // return Task t1
        Task getT5 = taskCol.getTask("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZBA");
        Task getNull = taskCol.getTask("ABFCX D");
        Task getFalse = taskCol.getTask("GET HD");

        System.out.println();
        System.out.println("Get t1:" + getT1.name);
        System.out.println("Get t5:" + getT5.name);
        System.out.println("Get Null: " + (getNull == null ? "Null" : ""));
        System.out.println("Get False: " + (getFalse == null ? "Null" : ""));
    }
}
