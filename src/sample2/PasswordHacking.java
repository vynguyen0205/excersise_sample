package sample2;

public class PasswordHacking {
    static int charToInt(char c) {
        if (Character.isDigit(c)) {
            return c-'0';
        }
        if (Character.isUpperCase(c)) {
            return c-'A'+36;
        }
        if (Character.isLowerCase(c)) {
            return c-'a'+10;
        }
        return 0;
    }

    static String digits = "0123456789";
    static String lowers = "abcdefghijklmnopqrstuvwxyz";
    static String uppers = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static char intToChar(int i) {
        if (i>=0 && i <=9) {
            return digits.charAt(i);
        }
        if (i>=10 && i<=35){
            return lowers.charAt(i-10);
        }
        if (i>=36 && i<=61){
            return uppers.charAt(i-36);
        }
        return '0';
    }

    public static int hash(String password){
        int hash = 0;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            hash+= charToInt(c);
        }
        return hash;
    }
    public static int maxValue(int length) {
        StringBuilder maxPassword = new StringBuilder();
        for (int i = 0; i < length; i++) {
            maxPassword.append('Z');
        }

        return hash(maxPassword.toString());
    }

    public static String collidedPassword(int hashValue) {
        if (hashValue > maxValue(20)) return "";

        StringBuilder password = new StringBuilder();

        while(hashValue > 61) {
           password.append('Z');
           hashValue-= 61;
        }
        if (hashValue > 0) {
            password.append(intToChar(hashValue));
        }

        if (password.length() < 8) {
            int missing = 8 - password.length();
            for (int i = 0; i < missing; i++) {
                password.append('0');
            }
        }

        return password.toString();
    }

    public static void main(String[] args) {
        System.out.println("Hash 00000001: " + hash("00000001"));
        System.out.println("Hash 0000000Aa1: " + hash("0000000Aa1"));
        System.out.println("Max hash 8: " + maxValue(8));
        System.out.println("Max hash 20: " + maxValue(20));

        System.out.println("Find password for 1220: " + collidedPassword(124));

    }
}
