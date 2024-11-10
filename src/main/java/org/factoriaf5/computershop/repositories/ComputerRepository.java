package org.factoriaf5.computershop.repositories;

import java.util.List;
import org.factoriaf5.computershop.models.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface ComputerRepository extends JpaRepository<Computer,Long> {

    List<Computer> findByBrand(String brand);
    @Transactional
    void deleteByBrand(String brand);


}