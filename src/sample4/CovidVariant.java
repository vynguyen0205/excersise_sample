package sample4;

public class CovidVariant {
    String code;

    String sequence;

    public CovidVariant(String code, String sequence) {
        this.code = code;
        this.sequence = sequence;
    }

    public boolean evenSequence() {
        int length = sequence.length();
        return length%2 == 0;
    }

    public  boolean isSubsequence(String s) {
        int i = 0, j = 0;

        while (i<sequence.length() && j<s.length()) {
            if (sequence.charAt(i) == s.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
        }

        return j == s.length();
    }

    public int longestCommonSubsequence(CovidVariant other) {

        char[] str1 = sequence.toCharArray();
        char[] str2 = other.sequence.toCharArray();

        int l1 = str1.length;
        int l2 = str2. length;

        return calLongestLength(str1, str2, l1, l2);
    }

    private int calLongestLength(char[] str1, char[] str2, int l1, int l2) {
        if (l1==0 || l2==0) return 0;

        if (str1[l1-1] == str2[l2-1]) {
            return 1+ calLongestLength(str1, str2, l1-1, l2-1);
        } else {
            return Math.max(calLongestLength(str1, str2, l1, l2-1), calLongestLength(str1, str2, l1-1, l2));
        }
    }

    public static void main(String[] args) {
        CovidVariant v1 = new CovidVariant("DELTA", "HELLOFROMDELTA");
        System.out.println("Is v1 even: " + v1.evenSequence());  // return true
        System.out.println("Is subsequence: " + v1.isSubsequence("HLOFRDLA")); // return true

        CovidVariant v2 = new CovidVariant("BETA", "HELLOFROMBETA");
        System.out.println("Is v2 even: " + v2.evenSequence());  // return true
        System.out.println("Is subsequence: " + v2.isSubsequence("LLFEA")); // return true

        System.out.println("Longest common subsequence: " + v1.longestCommonSubsequence(v2));  // return 12, the longest common subsequence is "HELLOFROMETA"
    }
}
