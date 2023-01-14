package w5;

class RMITStudent {
    String studentId;
    String fullName;
    String major;
    double GPA;

    public RMITStudent(String studentId) {
        this.studentId = studentId;
    }

    public RMITStudent(String studentId, String fullName, String major, double GPA) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.major = major;
        this.GPA = GPA;
    }
}

class RMITStudentCollection {

    final int size = 13;
    RMITStudent[] students = new RMITStudent[size];

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
        while (students[hash] != null) {
            if (students[hash].studentId.equals(s.studentId)) {
                return false;
            }
            hash++;
            if (hash == size) {
                hash = 0;
            }
        }
        students[hash] = s;
        return true;
    }

    RMITStudent get1(String studentId) {
        int hash = hash(studentId);
        int count = 0;
        while (students[hash] != null && !students[hash].studentId.equals(studentId) && count < size) {
            hash++;
            count++;
            if (hash == size) {
                hash = 0;
            }
        }
        if (students[hash] == null) return null;
        return students[hash].studentId.equals(studentId) ? students[hash] : null;
    }
}

public class Problem2 {
    public static void main(String[] args) {
        RMITStudentCollection col = new RMITStudentCollection();

        col.put1(new RMITStudent("32AFC", "Name 1", "A", 290));
        col.put1(new RMITStudent("32AFD", "Name 2", "A", 290));
        col.put1(new RMITStudent("32AFDM", "Name 3", "A", 290));

        System.out.println(col.get1("ADDDS"));
        System.out.println(col.get1("32AFD").fullName);
        System.out.println(col.get1("32AFDM").fullName);

    }
}
