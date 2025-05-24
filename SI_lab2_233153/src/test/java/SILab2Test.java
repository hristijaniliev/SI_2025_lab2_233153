import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    void testEveryStatement() {
        // Invalid: allItems is null
        RuntimeException ex1 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(null, "1234567890123456"));
        assertTrue(ex1.getMessage().contains("allItems list can't be null!"));

        // Invalid item name (null)
        Item item1 = new Item(null, 100, 0, 1);
        RuntimeException ex2 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(List.of(item1), "1234567890123456"));
        assertTrue(ex2.getMessage().contains("Invalid item!"));

        // Valid item, no discount
        Item item2 = new Item("Book", 100, 0, 2);
        double sum1 = SILab2.checkCart(List.of(item2), "1234567890123456");
        assertEquals(200, sum1); // 100 * 2 = 200

        // Valid item, with discount
        Item item3 = new Item("Pen", 50, 0, 2); // price > 0 and discount > 0
        double sum2 = SILab2.checkCart(List.of(item3), "1234567890123456");
        assertEquals(90, sum2); // (50 * 0.9) * 2 = 90

        // Discount condition with penalty (-30)
        Item item4 = new Item("Laptop", 400, 0, 1); // price > 300
        double sum3 = SILab2.checkCart(List.of(item4), "1234567890123456");
        assertEquals(370, sum3); // (400 * 1) - 30 = 370

        // Invalid card number: not 16 digits
        RuntimeException ex3 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(List.of(item2), "1234"));
        assertTrue(ex3.getMessage().contains("Invalid card number!"));

        // Invalid card number: contains letter
        RuntimeException ex4 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(List.of(item2), "123456789012345A"));
        assertTrue(ex4.getMessage().contains("Invalid character in card number!"));
    }
}