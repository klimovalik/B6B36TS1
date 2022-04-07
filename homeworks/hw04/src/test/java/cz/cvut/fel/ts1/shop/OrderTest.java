package cz.cvut.fel.ts1.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests for constructors.
 *
 * Don't forget about null values!
 */
public class OrderTest {

    /**
     * Parameters of Order.
     */
    static ShoppingCart cart = new ShoppingCart();
    String customerName = "NAME";
    String customerAddress = "ADDRESS";
    int state = 1;

    @BeforeAll
    public static void setItems() {
        Item[] storageItems = {
                new StandardItem(1, "Dancing Panda v.2", 5000, "GADGETS", 5),
                new StandardItem(2, "Dancing Panda v.3 with USB port", 6000, "GADGETS", 10),
                new StandardItem(3, "Screwdriver", 200, "TOOLS", 5),
                new DiscountedItem(4, "Star Wars Jedi buzzer", 500, "GADGETS", 30, "1.8.2013", "1.12.2013"),
                new DiscountedItem(5, "Angry bird cup", 300, "GADGETS", 20, "1.9.2013", "1.12.2013"),
                new DiscountedItem(6, "Soft toy Angry bird (size 40cm)", 800, "GADGETS", 10, "1.8.2013", "1.12.2013")
        };

        cart.addItem(storageItems[0]);
        cart.addItem(storageItems[1]);
        cart.addItem(storageItems[2]);
        cart.addItem(storageItems[3]);
        cart.addItem(storageItems[4]);
        cart.addItem(storageItems[5]);
    }

    @Test
    public void constructorTest_createsInstanceWithStateToSet_setsExpectedParameters() {
        Order order = new Order(cart, customerName, customerAddress, state);

        Assertions.assertEquals(cart.getCartItems(), order.getItems());
        Assertions.assertEquals(customerName, order.getCustomerName());
        Assertions.assertEquals(customerAddress, order.getCustomerAddress());
        Assertions.assertEquals(state, order.getState());
    }

    @Test
    public void constructorTest_createsInstanceWithDefaultState_setsExpectedParameters() {
        Order order = new Order(cart, customerName, customerAddress);
        int defaultState = 0;

        Assertions.assertEquals(cart.getCartItems(), order.getItems());
        Assertions.assertEquals(customerName, order.getCustomerName());
        Assertions.assertEquals(customerAddress, order.getCustomerAddress());
        Assertions.assertEquals(defaultState, order.getState());
    }

    @Test
    public void constructorTest_setsNullCart_throwsNullPointerException() {
        ShoppingCart nullCart = null;

        Assertions.assertThrows(NullPointerException.class,
                () -> {Order order = new Order(nullCart, customerName, customerAddress);});
    }
}
