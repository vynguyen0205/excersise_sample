package sample3;

import sample2.FriendshipGroup;

import java.util.ArrayList;
import java.util.Stack;

class Course {
    String name;
    String code;
    ArrayList<Course> prerequisites;

    Course(String name, String code) {
        this.name = name;
        this.code = code;
        prerequisites = new ArrayList<>();
    }
}

public class CourseList {
    ArrayList<Course> courses = new ArrayList<>();

    public void addCourse(Course c) {
        courses.add(c);
    }

    public void addPrerequisite(Course c, Course pre) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).code.equals(c.code)) {
                courses.get(i).prerequisites.add(pre);
                break;
            }
        }
    }

    public boolean takeFirst(Course c) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).code.equals(c.code)) {
                if (courses.get(i).prerequisites.size() > 0) return false;
            }
        }
        return true;
    }

    public String coursesTaken() {
        Stack<Course> stack = new Stack<>();
        boolean[] visited = new boolean[courses.size()];

        for (int i = 0; i < courses.size(); i++) {
            if (visited[i]) continue;
            process(stack, visited, i);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.append(stack.pop().name + ", ");
        }
        return sb.toString();
    }

    void process(Stack<Course> stack, boolean[] visited, int idx) {
        if (visited[idx]) return;

        visited[idx] = true;

        Course thisCourse = courses.get(idx);

        for (int i = 0; i < courses.size(); i++) {
            Course tmp = courses.get(i);

            boolean isPre = false;
            for (int j = 0; j < tmp.prerequisites.size(); j++) {
                if (tmp.prerequisites.get(j).code.equals(thisCourse.code)) {
                    isPre = true;
                    break;
                }
            }
            if (isPre) {
                process(stack, visited, i);
            }
        }

        stack.push(thisCourse);
    }

    public static void main(String[] args) {
        Course c1 = new Course("Programming 1", "C123");
        Course c2 = new Course("Web Programming", "C456");
        Course c3 = new Course("Data Structures", "C789");
        Course c4 = new Course("Database Application", "C000");

        CourseList list = new CourseList();
        list.addCourse(c1);
        list.addCourse(c2);
        list.addCourse(c3);
        list.addCourse(c4);

        list.addPrerequisite(c2, c1);  // make Programming 1 a prerequisite of Web Programming
        list.addPrerequisite(c3, c1);  // make Programming 1 a prerequisite of Data Structures
        list.addPrerequisite(c4, c2);  // make Web Programming a prerequisite of Database Application

        System.out.println("Take first C1: " + list.takeFirst(c1));  // true
        System.out.println("Take first C3: " + list.takeFirst(c3));  // false

        System.out.println("List course taken: " + list.coursesTaken()); // return "Programming 1, Web Programming, Data Structures, Database Application"
    }
}
