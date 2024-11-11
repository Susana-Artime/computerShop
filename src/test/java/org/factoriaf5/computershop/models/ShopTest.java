package org.factoriaf5.computershop.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import java.util.Arrays;

public class ShopTest {

    @Test
    public void testShopConstructor() {
        
        Shop shop = new Shop(1L, "TechStore", "John Doe", "A12345678");

        assertEquals(1L, shop.getId());
        assertEquals("TechStore", shop.getName());
        assertEquals("John Doe", shop.getOwner());
        assertEquals("A12345678", shop.getCif());
    }

    @Test
    public void testSettersAndGetters() {

        Shop shop = new Shop();

        shop.setId(2L);
        shop.setName("SuperComputers");
        shop.setOwner("Jane Doe");
        shop.setCif("B98765432");


        assertEquals(2L, shop.getId());
        assertEquals("SuperComputers", shop.getName());
        assertEquals("Jane Doe", shop.getOwner());
        assertEquals("B98765432", shop.getCif());
    }

    @Test
    public void testComputersRelationship() {
        
        Shop shop = new Shop(1L, "TechHub", "Alice Smith", "C12345678");

        Computer computer1 = Mockito.mock(Computer.class);
        Computer computer2 = Mockito.mock(Computer.class);

        shop.setComputers(Arrays.asList(computer1, computer2));

        assertEquals(2, shop.getComputers().size());
        assertEquals(computer1, shop.getComputers().get(0));
        assertEquals(computer2, shop.getComputers().get(1));
    }

    @Test
    public void testEmptyComputersList() {
        
        Shop shop = new Shop(1L, "GadgetWorld", "Sam Lee", "E12345679");

        assertTrue(shop.getComputers().isEmpty());
    }
}
