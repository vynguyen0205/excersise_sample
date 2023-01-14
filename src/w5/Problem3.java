package w5;

import java.util.ArrayList;
import java.util.List;

class StudentList {
    List<RMITStudent> list = new ArrayList<>();
}
class RMITStudentCollection2 {

    final int size = 13;
    StudentList[] students = new StudentList[size];

    RMITStudentCollection2(){
        for (int i = 0; i < students.length; i++) {
            students[i] = new StudentList();
        }
    }

    int hash(String str) {
        int total = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                total += c - '0' + 26;
            }
            if (Character.isLetter(c)) {
                total += c - 'A';
            }
        }
        return total % size;
    }

    boolean put1(RMITStudent s) {
        int hash = hash(s.studentId);
        List<RMITStudent> chain = students[hash].list;

        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).studentId.equals(s.studentId)){
                return false;
            }
        }
        chain.add(s);
        return true;
    }

    RMITStudent get1(String studentId) {
        int hash = hash(studentId);
        List<RMITStudent> chain = students[hash].list;
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).studentId.equals(studentId)){
                return chain.get(i);
            }
        }
        return null;
    }
}

public class Problem3 {
    public static void main(String[] args) {
        RMITStudentCollection2 col = new RMITStudentCollection2();

        col.put1(new RMITStudent("32AFC", "Name 1", "A", 290));
        col.put1(new RMITStudent("32AFD", "Name 2", "A", 290));
        col.put1(new RMITStudent("32AFDM", "Name 3", "A", 290));

        System.out.println(col.get1("ADDDS"));
        System.out.println(col.get1("32AFD").fullName);
        System.out.println(col.get1("32AFDM").fullName);
    }
}