package sample2;

public class FriendshipGroup {
    int[][] F;
    int size;
    public FriendshipGroup(int[][] F) {
        this.F = F;
        this.size = F.length;
    }

    public int countDirectFriends(int i) {
        int count = 0;

        for (int j = 0; j < size; j++) {
            if (j == i) continue;
            count+= F[i][j];
        }

        return count;
    }

    public int countFriends(int i) {
        boolean[] added = new boolean[size];
        added[i] = true;

        return countAllFriends(added, i);
    }

     int countAllFriends(boolean[] added, int i) {
         int count = 0;
         for (int j = 0; j < size; j++) {
             if (added[j]) continue;
             if (j == i) continue;
             if (F[i][j] == 1) {
                 count++;
                 added[j] = true;
                 count+= countAllFriends(added, j);
             }
         }
         return count;
     }

    public int countGroups() {
        boolean[] added = new boolean[size];

        int count = 0;

        for (int i = 0; i < size; i++) {
            if (added[i]) continue;
            added[i] = true;
            findAllFriends(added, i);
            count++;
        }

        return count;
    }

    void findAllFriends(boolean[] added, int i) {
        for (int j = 0; j < size; j++) {
            if (added[j]) continue;
            if (j == i) continue;
            if (F[i][j] == 1) {
                added[j] = true;
                findAllFriends(added, j);
            }
        }
    }

    public static void main(String[] args) {
        int[][] f = {
                {1, 0, 0, 0, 1},
                {0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 1, 1},
                {1, 0, 0, 1, 1}
        };

       FriendshipGroup friendshipGroup = new FriendshipGroup(f);

        System.out.println("Count direct friends: " + friendshipGroup.countDirectFriends(0));
        System.out.println("Count friends: " + friendshipGroup.countFriends(4));
        System.out.println("Count groups: " + friendshipGroup.countGroups());
    }
}
