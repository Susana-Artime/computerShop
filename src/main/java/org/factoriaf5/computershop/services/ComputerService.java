package org.factoriaf5.computershop.services;

import java.util.List;
import java.util.Optional;

import org.factoriaf5.computershop.models.Computer;
import org.factoriaf5.computershop.repositories.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ComputerService{

    @Autowired
    private ComputerRepository computerRepository;

    public Computer addComputer(Computer computer) {

        return computerRepository.save(computer);
    }

    public List<Computer> getComputers() {
        
        return computerRepository.findAll();
    }

    public List<Computer> findComputersByBrand(String brand) {
        return computerRepository.findByBrand(brand);
    }

    public Optional<Computer> findComputerbyId(Long id) {

        return computerRepository.findById(id);
        
    }

    public Computer updateComputer(Long id, Computer computer) {

        Optional<Computer> computerUpdated = computerRepository.findById(id);
        if (computerUpdated.isPresent()) {

            Computer computerCurrent = computerUpdated.get();
            computerCurrent.setBrand(computer.getBrand());
            computerCurrent.setMemory(computer.getMemory());
            computerCurrent.setProcess(computer.getProcess());
            computerCurrent.setOperatingSystem(computer.getOperatingSystem());
            computerCurrent.setPrice(computer.getPrice());
            
            return computerRepository.save(computerCurrent);
        }
        return null;

    }

    @Transactional
    public void deleteComputerByBrand(String brand) {
            
        computerRepository.deleteByBrand(brand);

        
    }

    public void deleteComputerById(Long id) {

        if (computerRepository.existsById(id)) {

            computerRepository.deleteById(id);

        }

    }

    
}
