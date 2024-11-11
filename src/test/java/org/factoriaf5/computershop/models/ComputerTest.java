package org.factoriaf5.computershop.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

public class ComputerTest {

    @Test
    public void testComputerConstructor() {

        Shop mockShop = Mockito.mock(Shop.class);
        Mockito.when(mockShop.getName()).thenReturn("Computer Shop");

        Computer computer = new Computer("Dell", 16, "Intel i7", "Windows 10", 1500.00, mockShop);

        assertEquals("Dell", computer.getBrand());
        assertEquals(16, computer.getMemory());
        assertEquals("Intel i7", computer.getProcess());
        assertEquals("Windows 10", computer.getOperatingSystem());
        assertEquals(1500.00, computer.getPrice());
        assertEquals(mockShop, computer.getShop());
    }

    
    @Test
    public void testSettersAndGetters() {
        
        Computer computer = new Computer();
        Shop mockShop = Mockito.mock(Shop.class);

        computer.setId(1L);
        computer.setBrand("Lenovo");
        computer.setMemory(32);
        computer.setProcess("AMD Ryzen 7");
        computer.setOperatingSystem("Linux");
        computer.setPrice(1800.00);
        computer.setShop(mockShop);

        assertEquals(1L, computer.getId());
        assertEquals("Lenovo", computer.getBrand());
        assertEquals(32, computer.getMemory());
        assertEquals("AMD Ryzen 7", computer.getProcess());
        assertEquals("Linux", computer.getOperatingSystem());
        assertEquals(1800.00, computer.getPrice());
        assertEquals(mockShop, computer.getShop());
    }

    @Test
    public void testDefaultConstructor() {
        
        Computer computer = new Computer();

        assertNull(computer.getBrand());
        assertEquals(0, computer.getMemory());
        assertNull(computer.getProcess());
        assertNull(computer.getOperatingSystem());
        assertEquals(0.0, computer.getPrice());
        assertNull(computer.getShop());
    }
}





