package cz.cvut.fel.ts1.shop;

import cz.cvut.fel.ts1.archive.PurchasesArchive;
import cz.cvut.fel.ts1.storage.ItemStock;
import cz.cvut.fel.ts1.storage.NoItemInStorage;
import cz.cvut.fel.ts1.storage.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Cover process of cart filling.
 *
 * Remember about:  - variability of the process's steps
 *                  - error states
 *
 * There is a difference between
 * process/integrate and unit testing!
 */
public class EShopControllerTest {

    List<Item> storageItems;
    int[] itemCount;

    private static Storage storage;
    private static PurchasesArchive archive;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @BeforeEach
    public void setEShop() {
        EShopController.startEShop();

        /* make up an artificial data */

        itemCount = new int[] {10,10,4,5,10,2};
        storageItems = new ArrayList<Item>() {
            {
                add(new StandardItem(1, "Dancing Panda v.2", 5000, "GADGETS", 5));
                add(new StandardItem(2, "Dancing Panda v.3 with USB port", 6000, "GADGETS", 10));
                add(new StandardItem(3, "Screwdriver", 200, "TOOLS", 5));
                add(new DiscountedItem(4, "Star Wars Jedi buzzer", 500, "GADGETS", 30, "1.8.2013", "1.12.2013"));
                add(new DiscountedItem(5, "Angry bird cup", 300, "GADGETS", 20, "1.9.2013", "1.12.2013"));
                add(new DiscountedItem(6, "Soft toy Angry bird (size 40cm)", 800, "GADGETS", 10, "1.8.2013", "1.12.2013"));
            }
        };

        // insert data to the storage
        for (int i = 0; i < storageItems.size(); i++) {
            EShopController.getStorage().insertItems(storageItems.get(i), itemCount[i]);
        }
    }

    @AfterEach
    public void stopEShop() {
        EShopController.stopEShop();
    }

    @Test
    public void scenarioTest_validShopping_throwsNoExceptions() throws NoItemInStorage {
        List<Item> itemStockList = EShopController.getStorage().getStockEntries().stream().map(ItemStock::getItem).map(Item.class::cast).collect(Collectors.toList());
        Assertions.assertArrayEquals(storageItems.stream().map(Item.class::cast).toArray(), itemStockList.toArray());

        ArrayList<Item> expectedCartItems = new ArrayList<>() {
            {
                add(storageItems.get(0));
                add(storageItems.get(1));
                add(storageItems.get(2));
                add(storageItems.get(3));
                add(storageItems.get(4));
                add(storageItems.get(5));
            }
        };
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(storageItems.get(0));
        cart.addItem(storageItems.get(1));
        cart.addItem(storageItems.get(2));
        cart.addItem(storageItems.get(3));
        cart.addItem(storageItems.get(4));
        cart.addItem(storageItems.get(5));
        ArrayList<Item> actualCartItems = new ArrayList<>(cart.getCartItems());
        Assertions.assertEquals(expectedCartItems, actualCartItems);
        EShopController.purchaseShoppingCart(cart, "Libuse Novakova","Kosmonautu 25, Praha 8");



        ArrayList<Integer> expectedItemsCounts = new ArrayList<>() {
            {
                add(itemCount[0] - 1);
                add(itemCount[1] - 1);
                add(itemCount[2] - 1);
                add(itemCount[3] - 1);
                add(itemCount[4] - 1);
                add(itemCount[5] - 1);
            }
        };
        ArrayList<Integer> actualItemsCounts = EShopController.getStorage().getItemsCounts();
        Assertions.assertEquals(expectedItemsCounts, actualItemsCounts);
    }

    @Test
    public void scenarioTest_purchasesEmptyCart_printsErrorMessage() throws NoItemInStorage {
        List<Item> itemStockList = EShopController.getStorage().getStockEntries().stream().map(ItemStock::getItem).map(Item.class::cast).collect(Collectors.toList());
        Assertions.assertArrayEquals(storageItems.stream().map(Item.class::cast).toArray(), itemStockList.toArray());

        ShoppingCart cart = new ShoppingCart();

        setUpStreams();
        String expectedOutput = "Error: shopping cart is empty\r\n";
//        String expectedOutput = "Error: shopping cart is empty\n";
        EShopController.purchaseShoppingCart(cart, "Libuse Novakova","Kosmonautu 25, Praha 8");
        String actualOutput = outContent.toString();
        Assertions.assertEquals(expectedOutput, actualOutput);
        restoreStreams();
    }

    @Test
    public void scenarioTest_purchasesUnavailableItems_throwsNoItemInStorageException() {
        List<Item> itemStockList = EShopController.getStorage().getStockEntries().stream().map(ItemStock::getItem).map(Item.class::cast).collect(Collectors.toList());
        Assertions.assertArrayEquals(storageItems.stream().map(Item.class::cast).toArray(), itemStockList.toArray());

        ArrayList<Item> expectedCartItems = new ArrayList<>() {
            {
                add(storageItems.get(5));
                add(storageItems.get(5));
                add(storageItems.get(5));
            }
        };
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(storageItems.get(5));
        cart.addItem(storageItems.get(5));
        cart.addItem(storageItems.get(5));
        ArrayList<Item> actualCartItems = new ArrayList<>(cart.getCartItems());
        Assertions.assertEquals(expectedCartItems, actualCartItems);

        Assertions.assertThrows(NoItemInStorage.class,
                () -> {EShopController.purchaseShoppingCart(cart, "Libuse Novakova","Kosmonautu 25, Praha 8");});
    }

    @Test
    public void scenarioTest_removesItemsFromEmptyCart_throwsNoItemInCartException() {
        List<Item> itemStockList = EShopController.getStorage().getStockEntries().stream().map(ItemStock::getItem).map(Item.class::cast).collect(Collectors.toList());
        Assertions.assertArrayEquals(storageItems.stream().map(Item.class::cast).toArray(), itemStockList.toArray());

        ShoppingCart cart = new ShoppingCart();
        Assertions.assertThrows(NoItemInCart.class,
                () -> {cart.removeItem(storageItems.get(0).getID());});
    }

    @Test
    public void scenarioTest_removesValidItemsFromCart_removalIsDone() throws NoItemInCart, NoItemInStorage {
        List<Item> itemStockList = EShopController.getStorage().getStockEntries().stream().map(ItemStock::getItem).map(Item.class::cast).collect(Collectors.toList());
        Assertions.assertArrayEquals(storageItems.stream().map(Item.class::cast).toArray(), itemStockList.toArray());

        ArrayList<Item> expectedCartItemsBefore = new ArrayList<>() {
            {
                add(storageItems.get(0));
                add(storageItems.get(1));
                add(storageItems.get(2));
                add(storageItems.get(3));
                add(storageItems.get(4));
                add(storageItems.get(5));
            }
        };
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(storageItems.get(0));
        cart.addItem(storageItems.get(1));
        cart.addItem(storageItems.get(2));
        cart.addItem(storageItems.get(3));
        cart.addItem(storageItems.get(4));
        cart.addItem(storageItems.get(5));
        ArrayList<Item> actualCartItemsBefore = new ArrayList<>(cart.getCartItems());
        Assertions.assertEquals(expectedCartItemsBefore, actualCartItemsBefore);

        ArrayList<Item> expectedCartItemsAfter = new ArrayList<>() {
            {
                add(storageItems.get(0));
                add(storageItems.get(2));
                add(storageItems.get(4));
            }
        };
        cart.removeItem(storageItems.get(1).getID());
        cart.removeItem(storageItems.get(3).getID());
        cart.removeItem(storageItems.get(5).getID());
        ArrayList<Item> actualCartItemsAfter = new ArrayList<>(cart.getCartItems());
        Assertions.assertEquals(expectedCartItemsAfter, actualCartItemsAfter);

        EShopController.purchaseShoppingCart(cart, "Libuse Novakova","Kosmonautu 25, Praha 8");

        ArrayList<Integer> expectedItemsCounts = new ArrayList<>() {
            {
                add(itemCount[0] - 1);
                add(itemCount[1]);
                add(itemCount[2] - 1);
                add(itemCount[3]);
                add(itemCount[4] - 1);
                add(itemCount[5]);
            }
        };
        ArrayList<Integer> actualItemsCounts = EShopController.getStorage().getItemsCounts();
        Assertions.assertEquals(expectedItemsCounts, actualItemsCounts);
    }
}
