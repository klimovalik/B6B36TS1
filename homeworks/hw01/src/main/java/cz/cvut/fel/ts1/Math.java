package cz.cvut.fel.ts1;

public class Math {

    public static void main(String[] args) {
        System.out.println("result for 3! is " + factorialRecursive(3));
    }

    public static long factorialRecursive(int num) {
        if (num <= 1) return 1;
        return num * factorialRecursive(num - 1);
    }
}
