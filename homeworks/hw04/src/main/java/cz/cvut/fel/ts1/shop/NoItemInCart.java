package cz.cvut.fel.ts1.shop;

public class NoItemInCart extends Exception {
    public NoItemInCart() {
        super("No item in shopping cart");
    }
}
