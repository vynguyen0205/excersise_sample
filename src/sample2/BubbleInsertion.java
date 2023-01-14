package sample2;

public class BubbleInsertion {
    int[] originalArr;

    public BubbleInsertion(int[] arr) {
        this.originalArr = arr;
    }

    public int[] bubbleSort() {
        int[] arr = originalArr.clone();
        printArray(arr);

        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false; // used to check if there is anny swap
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap 2 elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true; // swapping happens
                }
            }
            printArray(arr);
            // array in sorted order?
            if (!swapped) {
                break;
            }
        }
        return arr;
    }

    public int calbubbleCost() {
        int cost = 0;
        int[] arr = originalArr.clone();

        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false; // used to check if there is anny swap
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    cost += 2;
                    // swap 2 elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true; // swapping happens
                }
            }
            // array in sorted order?
            if (!swapped) {
                break;
            }
        }
        return cost;
    }

    public int[] insertionSort() {
        int[] arr = originalArr.clone();
        printArray(arr);

        for (int i = 1; i < arr.length; i++) {
            int v = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > v) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = v;
            printArray(arr);
        }
        return arr;
    }

    public int calInsertionCost() {
        int cost = 0;
        int[] arr = originalArr.clone();

        for (int i = 1; i < arr.length; i++) {
            int v = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > v) {
                arr[j + 1] = arr[j];
                j = j - 1;
                cost++;
            }
            arr[j + 1] = v;
            cost += i - j - 1;
        }
        return cost;
    }

    public String winner() {
        int insertionSortCost = calInsertionCost();
        int bubbleSortCost = calbubbleCost();

        System.out.println("Bubble Sort Cost: " + bubbleSortCost);
        System.out.println("Insertion Sort Cost: " + insertionSortCost);

        return insertionSortCost > bubbleSortCost ? "Insertion Sort" : insertionSortCost == bubbleSortCost ? "Draw" : "Bubble Sort";
    }

    public static void printArray(int[] arr) {
        if (arr == null) return;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {3,2,1,5,4,9};
        BubbleInsertion bi = new BubbleInsertion(arr);

        System.out.println("Bubble sort: ");
        bi.bubbleSort();
        System.out.println();

        System.out.println("Insertion sort: ");
        bi.insertionSort();
        System.out.println();

        System.out.println("Winner: " + bi.winner());
    }
}
