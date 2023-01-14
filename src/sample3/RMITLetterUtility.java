package sample3;

import java.util.ArrayList;

class RMITLetter {
    char letter;
    int row;
    int col;

    RMITLetter(char l, int r, int c) {
        letter = l;
        row = r;
        col = c;
    }
}

public class RMITLetterUtility {
    ArrayList<RMITLetter> list = new ArrayList<>();

    public ArrayList<RMITLetter> scan(char[][] letters) {
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < letters[i].length; j++) {
                char temp = letters[i][j];
                if (temp == 'R' || temp == 'I' || temp == 'M' || temp == 'T') {
                    list.add(new RMITLetter(temp, i, j));
                }
            }
        }

        return list;
    }

    public boolean canConnect(RMITLetter l1, RMITLetter l2) {
        if ((l1.letter == 'R' && l2.letter == 'M') ||
                (l1.letter == 'M' && l2.letter == 'I') ||
                (l1.letter == 'I' && l2.letter == 'T')) {
            int rowDiff = l2.row - l1.row;
            int colDif = l2.col - l1.col;

            if (rowDiff >= 0 && colDif >= 0) {
                if (rowDiff + colDif <= 4) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean canFindRMIT() {
        for (int i = 0; i < list.size(); i++) {
            RMITLetter currR = list.get(i);
            if (currR.letter == 'R') {
                for (int j = 0; j < list.size(); j++) {
                    RMITLetter currM = list.get(j);
                    if (currM.letter == 'M' && canConnect(currR, currM)) {
                        for (int k = 0; k < list.size(); k++) {
                            RMITLetter currI = list.get(k);
                            if (currI.letter == 'I' && canConnect(currM, currI)) {
                                for (int l = 0; l < list.size(); l++) {
                                    RMITLetter currT = list.get(l);
                                    if (currT.letter == 'T' && canConnect(currM, currI)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private void printList(ArrayList<RMITLetter> list) {
        if (list == null) return;
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).letter + ", " + list.get(i).row + ", " + list.get(i).col);
        }
    }

    public static void main(String[] args) {
        char[][] arr = {
                {'A', 'R', 'N', 'U', 'R', 'M', 'U', 'V' },
                {'X', 'L', 'N', 'U', 'T', 'M', 'J', 'C' },
                {'A', 'Q', 'N', 'H', 'I', 'U', 'V', 'H' },
                {'A', 'R', 'O', 'U', 'R', 'G', 'U', 'I' },
                {'B', 'R', 'N', 'L', 'R', 'M', 'U', 'T' },
        };

        RMITLetterUtility util = new RMITLetterUtility();

        ArrayList<RMITLetter> scannedList = util.scan(arr);
        util.printList(scannedList);

        System.out.println("Can connect: " + util.canConnect(new RMITLetter('R', 0, 1), new RMITLetter('M', 0, 5)));
        System.out.println("Can find RMIT: " + util.canFindRMIT());
    }
}
