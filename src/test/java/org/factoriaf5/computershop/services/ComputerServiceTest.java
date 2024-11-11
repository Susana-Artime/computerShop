package org.factoriaf5.computershop.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.factoriaf5.computershop.models.Computer;
import org.factoriaf5.computershop.models.Shop;
import org.factoriaf5.computershop.repositories.ComputerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ComputerServiceTest {

    @Mock
    ComputerRepository computerRepository;
    @InjectMocks
    ComputerService computerService;
    @Mock
    Computer mockComputer;
    @Mock
    Shop mockShop;
    

    @BeforeEach
    void setUp() {

        mockShop = new Shop(1L, "Computer Shop", "John Doe", "123456789");
        mockComputer = new Computer("Lenovo", 16, "Intel Core i7", "Windows 11", 1200.0, mockShop);
        mockComputer.setId(1L);
        
    }

    @Test
    void testAddComputer() {
        
        when(computerRepository.save(any(Computer.class))).thenReturn(mockComputer);

        Computer result = computerService.addComputer(mockComputer);

        verify(computerRepository, times(1)).save(any(Computer.class));
        assertNotNull(result);
        assertEquals("Lenovo", result.getBrand());
    }

    

    @Test
    void testGetComputers() {
        List<Computer> mockComputers = new ArrayList<>();

        mockComputers.add(mockComputer);
                
        when(computerRepository.findAll()).thenReturn(mockComputers);

        List<Computer> result = computerService.getComputers();
        assertTrue(result.equals(mockComputers));
    }

    @Test
    void testFindComputerById() {
        
        when(computerRepository.findById(1L)).thenReturn(Optional.of(mockComputer));

        Optional<Computer> result = computerService.findComputerbyId(1L);

        assertTrue(result.isPresent());
        assertEquals("Lenovo", result.get().getBrand());
    }

    @Test
    void testFindComputerByIdNotFound() {

        when(computerRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Computer> result = computerService.findComputerbyId(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testFindComputerByBrand() {
        List<Computer> mockComputers = new ArrayList<>();

        when(computerRepository.findByBrand("Lenovo")).thenReturn(mockComputers);

        List<Computer> result = computerService.findComputersByBrand("Lenovo");

        assertTrue(result.equals(mockComputers));
    }

    @Test
    void testFindComputerByBrandNotFound() {

        when(computerRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Computer> result = computerService.findComputerbyId(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testUpdateComputer() {
        Computer updatedComputer = new Computer("HP", 32, "Intel i9", "Windows 11", 1500.00, null);

        when(computerRepository.findById(1L)).thenReturn(Optional.of(mockComputer));
        when(computerRepository.save(any(Computer.class))).thenReturn(updatedComputer);

        Computer result = computerService.updateComputer(1L, updatedComputer);

        assertNotNull(result);
        assertEquals("HP", result.getBrand());
        assertEquals(32, result.getMemory());
        assertEquals("Intel i9", result.getProcess());
        assertEquals("Windows 11", result.getOperatingSystem());
        assertEquals(1500.00, result.getPrice());
    }

    @Test
    void testUpdateComputerNotFound() {

        Computer updatedComputer = new Computer("HP", 32, "Intel i9", "Windows 11", 1500.00, null);
        when(computerRepository.findById(1L)).thenReturn(Optional.empty());

        Computer result = computerService.updateComputer(1L,updatedComputer);

        assertNull(result);
    }

    @Test
    void testDeleteComputerById() {
        
        when(computerRepository.existsById(1L)).thenReturn(true);

        computerService.deleteComputerById(1L);

        verify(computerRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteComputerByIdNotFound() {

        when(computerRepository.existsById(1L)).thenReturn(false);

        computerService.deleteComputerById(1L);

        verify(computerRepository, times(0)).deleteById(1L);
    }

    @Test
    void testDeleteComputerByBrand() {
        
        doNothing().when(computerRepository).deleteByBrand("HP");

        computerService.deleteComputerByBrand("HP");

        verify(computerRepository, times(1)).deleteByBrand("HP");
    }

}

