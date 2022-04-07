package cz.cvut.fel.ts1.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * Tests for constructor and copy.
 *
 * Parametrized test for equals.
 */
public class StandardItemTest {

    /**
     * Parameters of StandardItem.
     */
    int id = 111;
    String name = "NAME";
    float price = 111;
    String category = "CATEGORY";
    int loyaltyPoints = 111;

    StandardItem item;

    @Test
    public void constructorTest_createsInstance_setsParameters() {
        item = new StandardItem(id, name, price, category, loyaltyPoints);

        Assertions.assertEquals(id, item.getID());
        Assertions.assertEquals(name, item.getName());
        Assertions.assertEquals(price, item.getPrice());
        Assertions.assertEquals(category, item.getCategory());
        Assertions.assertEquals(loyaltyPoints, item.getLoyaltyPoints());
    }

    @Test
    public void copyTest_copiesInstance_setsSameParameters() {
        item = new StandardItem(id, name, price, category, loyaltyPoints);
        StandardItem copy = item.copy();

        Assertions.assertEquals(item.getID(), copy.getID());
        Assertions.assertEquals(item.getName(), copy.getName());
        Assertions.assertEquals(item.getPrice(), copy.getPrice());
        Assertions.assertEquals(item. getCategory(), copy.getCategory());
        Assertions.assertEquals(item.getLoyaltyPoints(), copy.getLoyaltyPoints());
    }


    static Stream<Arguments> argumentsSource() {
        StandardItem testItem = new StandardItem(1, "Dancing Panda v.2", 5000, "GADGETS", 5);
        StandardItem sameItem = new StandardItem(1, "Dancing Panda v.2", 5000, "GADGETS", 5);
        StandardItem spaceItem = new StandardItem(1, "Dancing Panda v.2 ", 5000, "GADGETS", 5);
        StandardItem difItem = new StandardItem(2, "Dancing Panda v.3 with USB port", 6000, "GADGETS", 10);

        return Stream.of(
                Arguments.of(testItem, testItem, true),
                Arguments.of(testItem, sameItem, true),
                Arguments.of(testItem, spaceItem, false),
                Arguments.of(testItem, difItem, false),
                Arguments.of(testItem, null, false)
        );
    }

    @ParameterizedTest(name = "Result of comparing {0} and {1} is {2}")
    @MethodSource("argumentsSource")
    public void equalsTest_comparesTwoItems_returnsTrueOrFalse(StandardItem item1, StandardItem item2, boolean expectedResult) {
        Assertions.assertEquals(expectedResult, item1.equals(item2));
    }
}
