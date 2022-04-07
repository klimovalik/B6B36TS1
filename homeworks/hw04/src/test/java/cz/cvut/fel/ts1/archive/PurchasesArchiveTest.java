package cz.cvut.fel.ts1.archive;

import cz.cvut.fel.ts1.shop.*;
import cz.cvut.fel.ts1.shop.Order;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for methods printItemPurchaseStatistics,
 * getHowManyTimesHasBeenItemSold and putOrderToPurchasesArchive.
 *
 * Test for println ( stream ).
 *
 * Mock orderArchive and mock ItemPurchaseArchiveEntry.
 *
 * Checking call of ItemPurchaseArchiveEntry constructor.
 */
public class PurchasesArchiveTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    /** printItemPurchaseStatistics tests */

    @Test
    public void printItemPurchaseStatisticsTest_ArchiveIsEmpty_thereIsNoStatistics() {
        PurchasesArchive archive = new PurchasesArchive();

        archive.printItemPurchaseStatistics();

        // works on windows
        String expectedResult = "ITEM PURCHASE STATISTICS:\r\n";

/*
        // works on linux, does not work on windows
        String expectedResult = "ITEM PURCHASE STATISTICS:\n";
*/

        Assertions.assertEquals(expectedResult, outContent.toString());
    }

    private ShoppingCart setCart() {
        ShoppingCart cart = new ShoppingCart();

        StandardItem standardItem = new StandardItem(3, "Screwdriver", 200, "TOOLS", 5);
        DiscountedItem discountedItem = new DiscountedItem(5, "Angry bird cup", 300, "GADGETS", 20, "1.9.2013", "1.12.2013");

        restoreStreams();
        cart.addItem(standardItem);
        cart.addItem(discountedItem);
        setUpStreams();

        return cart;
    }

    @Test
    public void printItemPurchaseStatisticsTest_thereAreItemsInArchive_printsStatistics() {
        Order order = new Order(setCart(), "NAME", "ADDRESS");
        PurchasesArchive archive = new PurchasesArchive();

        archive.putOrderToPurchasesArchive(order);
        archive.printItemPurchaseStatistics();

        // works on windows
        String expectedResult = "ITEM PURCHASE STATISTICS:\r\n" +
                "ITEM  Item   ID 3   NAME Screwdriver   CATEGORY TOOLS   PRICE 200.0   LOYALTY POINTS 5   HAS BEEN SOLD 1 TIMES\r\n" +
                "ITEM  Item   ID 5   NAME Angry bird cup   CATEGORY GADGETS   ORIGINAL PRICE 300.0    DISCOUNTED PRICE 240.0  DISCOUNT FROM Sun Sep 01 00:00:00 CEST 2013    DISCOUNT TO Sun Dec 01 00:00:00 CET 2013   HAS BEEN SOLD 1 TIMES\r\n";

/*
        // works on linux, does not work on windows
        String expectedResult = "ITEM PURCHASE STATISTICS:\n" +
        "ITEM  Item   ID 3   NAME Screwdriver   CATEGORY TOOLS   PRICE 200.0   LOYALTY POINTS 5   HAS BEEN SOLD 1 TIMES\n" +
        "ITEM  Item   ID 5   NAME Angry bird cup   CATEGORY GADGETS   ORIGINAL PRICE 300.0    DISCOUNTED PRICE 24000.0  DISCOUNT FROM Sun Sep 01 00:00:00 CEST 2013    DISCOUNT TO Sun Dec 01 00:00:00 CET 2013   HAS BEEN SOLD 1 TIMES\n";
*/

        Assertions.assertEquals(expectedResult, outContent.toString());
    }

    /** getHowManyTimesHasBeenItemSold tests */

    @Test
    public void getHowManyTimesHasBeenItemSoldTest_getsUnsoldItems_returnsZero() {
        StandardItem standardItem = new StandardItem(3, "Screwdriver", 200, "TOOLS", 5);
        DiscountedItem discountedItem = new DiscountedItem(5, "Angry bird cup", 300, "GADGETS", 20, "1.9.2013", "1.12.2013");
        PurchasesArchive archive = new PurchasesArchive();

        int actualResultStandard = archive.getHowManyTimesHasBeenItemSold(standardItem);
        int actualResultDiscounted = archive.getHowManyTimesHasBeenItemSold(discountedItem);
        int expectedResult = 0;

        Assertions.assertEquals(expectedResult, actualResultStandard);
        Assertions.assertEquals(expectedResult, actualResultDiscounted);
    }

    static Stream<Arguments> argumentsSource() {
        StandardItem standardItem = new StandardItem(3, "Screwdriver", 200, "TOOLS", 5);
        DiscountedItem discountedItem = new DiscountedItem(5, "Angry bird cup", 300, "GADGETS", 20, "1.9.2013", "1.12.2013");

        return Stream.of(
                Arguments.of(standardItem),
                Arguments.of(discountedItem)
        );
    }

    @ParameterizedTest(name = "Item {0} has been sold 11 times")
    @MethodSource("argumentsSource")
    public void getHowManyTimesHasBeenItemSoldTest_getsItem_returnsCount(Item item) {
        ItemPurchaseArchiveEntry mockedArchive = mock(ItemPurchaseArchiveEntry.class);
        ArrayList<Order> mockedOrderArchive = mock(ArrayList.class);
        HashMap<Integer, ItemPurchaseArchiveEntry> itemArchive = new HashMap<>();

        int expectedResult = 11;

        when(mockedArchive.getRefItem()).thenReturn(item);
        when(mockedArchive.getCountHowManyTimesHasBeenSold()).thenReturn(expectedResult);
        itemArchive.put(item.getID(), mockedArchive);
        PurchasesArchive archive = new PurchasesArchive(itemArchive, mockedOrderArchive);
        int actualResult = archive.getHowManyTimesHasBeenItemSold(item);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    /** putOrderToPurchasesArchive tests */

    @Test
    public void putOrderToPurchasesArchiveTest_putsNullOrder_throwsNullPointerException() {
        PurchasesArchive archive = new PurchasesArchive();
        Order order = null;

        Assertions.assertThrows(NullPointerException.class,
                () -> {archive.putOrderToPurchasesArchive(order);});
    }

    @Test
    public void putOrderToPurchasesArchiveTest_putsValidOrder_soldCountWasIncreased() {
        StandardItem standardItem = new StandardItem(3, "Screwdriver", 200, "TOOLS", 5);
        DiscountedItem discountedItem = new DiscountedItem(5, "Angry bird cup", 300, "GADGETS", 20, "1.9.2013", "1.12.2013");
        PurchasesArchive archive = new PurchasesArchive();
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(standardItem);
        cart.addItem(discountedItem);
        Order order = new Order(cart, "NAME", "ADDRESS");
        archive.putOrderToPurchasesArchive(order);

        int expectedResult = 1;
        int actualResultStandard = archive.getHowManyTimesHasBeenItemSold(standardItem);
        int actualResultDiscounted = archive.getHowManyTimesHasBeenItemSold(discountedItem);

        Assertions.assertEquals(expectedResult, actualResultStandard);
        Assertions.assertEquals(expectedResult, actualResultDiscounted);
    }

    /** ItemPurchaseArchiveEntry constructor test */

    @Test
    public void ItemPurchaseArchiveEntryConstructorTest_createsInstance_setsParameters() {
        StandardItem refItem = new StandardItem(3, "Screwdriver", 200, "TOOLS", 5);
        ItemPurchaseArchiveEntry archive = new ItemPurchaseArchiveEntry(refItem);

        int defaultSoldCount = 1;

        Assertions.assertEquals(refItem, archive.getRefItem());
        Assertions.assertEquals(defaultSoldCount, archive.getCountHowManyTimesHasBeenSold());
    }
}
