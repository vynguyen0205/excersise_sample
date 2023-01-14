package sample3;

class CovidVariant{
    String name;
    String code;

    CovidVariant(String name, String code) {
        this.name = name;
        this.code = code;
    }
}

class Node {
    CovidVariant data;
    Node left;
    Node right;

    public Node(CovidVariant data){
        this.data = data;
    }

    public static boolean isSmaller(String code1, String code2) {
        int date1 = Integer.parseInt(code1.substring(0,6));
        char order1 = code1.charAt(6);
        int date2 = Integer.parseInt(code2.substring(0,6));
        char order2 = code2.charAt(6);

        if (date1 < date2) return true;
        if (date1 == date2 && order1 < order2) return true;
        return false;
    }

    public void addChild(Node newNode) {
        if (newNode.data.code.equals(data.code)) return;

        if (isSmaller(newNode.data.code, data.code)) {
            if (left==null) {
                left=newNode;
            } else {
                left.addChild(newNode);
            }
        } else {
            if (right==null) {
                right= newNode;
            } else {
                right.addChild(newNode);
            }
        }
    }
}

public class VariantCollection {
    Node root;
    int size;


    public void addVariant(CovidVariant v) {
        if (root == null) {
            root = new Node(v);
            return;
        }
        root.addChild(new Node(v));
    }

    public CovidVariant search(String code) {
        Node node =root;
        while(node!=null) {
            if (node.data.code.equals(code)) {
                return node.data;
            }

            if (Node.isSmaller(code, node.data.code)) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    public CovidVariant previous(String code) {
        return null;
    }

    private void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.data.code + ", ");
        inOrder(node.right);
    }

    public static void main(String[] args) {
        VariantCollection col = new VariantCollection();

        col.addVariant(new CovidVariant("Alpha", "210201A"));
        col.addVariant(new CovidVariant("Delta", "210311D"));
        col.addVariant(new CovidVariant("Beta", "210311A"));
        col.addVariant(new CovidVariant("Omicron", "211120D"));
        System.out.println("In order traversal");
        col.inOrder(col.root);
        System.out.println();

        System.out.println("Find 210311A: " + col.search("210311A").name); // return the Beta variant

        col.previous("211120D"); // return the Delta variant
    }
}
