package cz.cvut.fel.ts1.storage;

import cz.cvut.fel.ts1.shop.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Test for constructor.
 *
 * Parametrized tests for methods
 * that are changing items count.
 */
public class ItemStockTest {

    /**
     * Parameters of StandardItem.
     */
    int id = 111;
    String name = "NAME";
    float price = 111;
    String category = "CATEGORY";
    int loyaltyPoints = 111;

    Item refItem = new StandardItem(id, name, price, category, loyaltyPoints);
    ItemStock stock;

    @BeforeEach
    public void setItemStock() {
        stock = new ItemStock(refItem);
    }

    @Test
    public void constructorTest_createsInstance_setsExpectedParameters() {
        int defaultCount = 0;

        Assertions.assertEquals(refItem, stock.getItem());
        Assertions.assertEquals(defaultCount, stock.getCount());
    }

    @ParameterizedTest(name = "Count after adding {0} and {1} is {2}")
    @CsvSource({"0, 0, 0", "1, -2, -1", "30, 100, 130", "-30, -100, -130"})
    public void increaseTest_addsAandBToCount_countIsSetToExpectedResult(int a, int b, int expectedResult) {
        stock.increaseItemCount(a);
        stock.increaseItemCount(b);

        Assertions.assertEquals(expectedResult, stock.getCount());
    }

    @ParameterizedTest(name = "Count after subtracting {0} and {1} is {2}")
    @CsvSource({"0, 0, 0", "-2, 1, 1", "30, 100, -130", "-30, -100, 130"})
    public void decreaseTest_subtractsAandBFromCount_countIsSetToExpectedResult(int a, int b, int expectedResult) {
        stock.decreaseItemCount(a);
        stock.decreaseItemCount(b);

        Assertions.assertEquals(expectedResult, stock.getCount());
    }
}
