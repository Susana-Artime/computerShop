package org.factoriaf5.computershop.controllers;

import java.util.List;
import java.util.Optional;

import org.factoriaf5.computershop.models.Computer;
import org.factoriaf5.computershop.models.Shop;
import org.factoriaf5.computershop.services.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/computers")
@Tag(name = "Computers", description = "API de gestión de ordenadores")
public class ComputerController {

    @Autowired
    private ComputerService computerService;

    @Operation(summary = "Agrega un nuevo ordenador")
    @PostMapping
    public ResponseEntity<Computer> addComputer(@RequestBody Computer computer) {

        Shop shop=new Shop(1L,"Computer Shop","John Doe", "123456789");
        computer.setShop(shop);
        Computer computerCreated = computerService.addComputer(computer);
        return ResponseEntity.status(HttpStatus.CREATED).body(computerCreated);

    }

    @Operation(summary = "Obtiene todos los ordenadores")
    @GetMapping
    public ResponseEntity<List<Computer>> getComputers() { 

        List<Computer> computers= computerService.getComputers();
        return computers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(computers);

    }


    @Operation(summary = "Busca ordenadores por marca")
    @GetMapping ("/{brand}")    
    public ResponseEntity<List<Computer>> getComputersByBrand(@PathVariable String brand) {
        List<Computer> computers = computerService.findComputersByBrand(brand);
        return computers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(computers);
    }


    @Operation(summary = "Actualiza un ordenador por id")
    @PutMapping("/{id}")
    public ResponseEntity<Computer> updateComputer(@PathVariable Long id, @RequestBody Computer computer) {
        Computer computerUpdate = computerService.updateComputer(id, computer);
        if (computerUpdate != null) {
            return ResponseEntity.ok(computerUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Borra un ordenador por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComputerById(@PathVariable Long id) {
        Optional<Computer> computer = computerService.findComputerbyId(id);
        if (computer.isPresent()) {
            computerService.deleteComputerById(id); 
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Borra ordenadores por marca")
    @DeleteMapping("brand/{brand}")
    public ResponseEntity<Void> deleteComputerByBrand(@PathVariable String brand) {
        List<Computer> computers = computerService.findComputersByBrand(brand);
        if (!computers.isEmpty()) {
            computerService.deleteComputerByBrand(brand);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
