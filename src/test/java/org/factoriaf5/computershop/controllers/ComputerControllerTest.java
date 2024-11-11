package org.factoriaf5.computershop.controllers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.factoriaf5.computershop.models.Computer;
import org.factoriaf5.computershop.models.Shop;
import org.factoriaf5.computershop.services.ComputerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ComputerController.class)
public class ComputerControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    ComputerService computerService;

    private Computer mockComputer;
    private Shop mockShop;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        mockShop = new Shop(1L, "Computer Shop", "John Doe", "123456789");
        mockComputer = new Computer("Lenovo", 16, "Intel Core i7", "Windows 11", 1200.0, mockShop);
        mockComputer.setId(1L);
    }

    @Test
    void testAddComputer() throws Exception {

        when(computerService.addComputer(any(Computer.class))).thenReturn(mockComputer);

        mockMvc.perform(post("/computers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockComputer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.brand").value("Lenovo"))
                .andExpect(jsonPath("$.memory").value(16))
                .andExpect(jsonPath("$.process").value("Intel Core i7"))
                .andExpect(jsonPath("$.operatingSystem").value("Windows 11"))
                .andExpect(jsonPath("$.price").value(1200.0))

                .andExpect(jsonPath("$.shop.id").value(1))
                .andExpect(jsonPath("$.shop.name").value("Computer Shop"))
                .andExpect(jsonPath("$.shop.owner").value("John Doe"))
                .andExpect(jsonPath("$.shop.cif").value("123456789"));

        verify(computerService, times(1)).addComputer(any(Computer.class));
    }

    @Test
    void testGetComputers() throws Exception {
        List<Computer> computers = Arrays.asList(mockComputer);
        when(computerService.getComputers()).thenReturn(computers);

        mockMvc.perform(get("/computers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].brand").value("Lenovo"))
                .andExpect(jsonPath("$[0].memory").value(16))
                .andExpect(jsonPath("$[0].process").value("Intel Core i7"))
                .andExpect(jsonPath("$[0].operatingSystem").value("Windows 11"))
                .andExpect(jsonPath("$[0].price").value(1200.0))

                .andExpect(jsonPath("$[0].shop.id").value(1))
                .andExpect(jsonPath("$[0].shop.name").value("Computer Shop"))
                .andExpect(jsonPath("$[0].shop.owner").value("John Doe"))
                .andExpect(jsonPath("$[0].shop.cif").value("123456789"));

        verify(computerService, times(1)).getComputers();
    }

    @Test
    void testGetComputersNoContent() throws Exception {
        List<Computer> computers = Arrays.asList();
        when(computerService.getComputers()).thenReturn(computers);
        
        mockMvc.perform(get("/computers"))
                .andExpect(status().isNoContent());

        verify(computerService, times(1)).getComputers();
    }

    @Test
    void testGetComputersByBrand() throws Exception {
        List<Computer> computers = Arrays.asList(mockComputer);
        when(computerService.findComputersByBrand("Lenovo")).thenReturn(computers);

        mockMvc.perform(get("/computers/Lenovo"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].brand").value("Lenovo"));

        verify(computerService, times(1)).findComputersByBrand("Lenovo");
    }

    @Test
    void testGetComputersByBrandNoContent() throws Exception {
        List<Computer> computers = Arrays.asList();
        when(computerService.findComputersByBrand("Lenovo")).thenReturn(computers);
        
        mockMvc.perform(get("/computers/Lenovo"))
                .andExpect(status().isNoContent());

        verify(computerService, times(1)).findComputersByBrand("Lenovo");
    }

    @Test
    void testUpdateComputer() throws Exception {
        Computer updatedComputer = new Computer("Lenovo", 32, "Intel Core i9", "Windows 10", 1500.0, mockShop);
        updatedComputer.setId(1L);
        when(computerService.updateComputer(eq(1L), any(Computer.class))).thenReturn(updatedComputer);

        mockMvc.perform(put("/computers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedComputer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memory").value(32))
                .andExpect(jsonPath("$.process").value("Intel Core i9"));

        verify(computerService, times(1)).updateComputer(eq(1L), any(Computer.class));
    }

    @Test
    void testUpdateComputerNotFound() throws Exception {
        
        when(computerService.updateComputer(eq(1L), any(Computer.class))).thenReturn(null);
        
        mockMvc.perform(put("/computers/1") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockComputer))) 
                .andExpect(status().isNotFound());  

        verify(computerService, times(1)).updateComputer(eq(1L), any(Computer.class));
    }

    @Test
    void testDeleteComputerById() throws Exception {
        when(computerService.findComputerbyId(1L)).thenReturn(Optional.of(mockComputer));
        doNothing().when(computerService).deleteComputerById(1L);

        mockMvc.perform(delete("/computers/1"))
                .andExpect(status().isNoContent());

        verify(computerService, times(1)).deleteComputerById(1L);
    }

    @Test
    void testDeleteComputerByIdNotFound() throws Exception {
        when(computerService.findComputerbyId(1L)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/computers/1"))
                .andExpect(status().isNotFound());

        verify(computerService, times(1)).findComputerbyId(1L);
    }

    @Test
    void testDeleteComputerByBrand() throws Exception {
        when(computerService.findComputersByBrand("Lenovo")).thenReturn(Arrays.asList(mockComputer));
        doNothing().when(computerService).deleteComputerByBrand("Lenovo");

        mockMvc.perform(delete("/computers/brand/Lenovo"))
                .andExpect(status().isNoContent());

        verify(computerService, times(1)).deleteComputerByBrand("Lenovo");
    }

    @Test
    void testDeleteComputerByBrandNotFound() throws Exception {
        List<Computer> computers = Arrays.asList();
        when(computerService.findComputersByBrand("Lenovo")).thenReturn(computers);
        mockMvc.perform(delete("/computers/brand/Lenovo"))
                .andExpect(status().isNotFound());

        verify(computerService, times(1)).findComputersByBrand("Lenovo");
    }






}
