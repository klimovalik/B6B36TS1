package cz.cvut.fel.ts1.lab03;

public class Foo {

    private int num = 0;

    public int returnNumber() {
        return 5;
    }

    public int getNum() {
        return num;
    }

    public void increment() {
        num++;
    }

    public void exceptionThrown() throws Exception {
        throw new Exception("Ocekavana vyjimka");
    }

}
