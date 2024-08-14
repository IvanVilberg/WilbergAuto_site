package com.site.autosite.car;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    
    List<Car> findByBrand(@Param("brand") String brand);

    List<Car> findByModel(@Param("model") String model);

    List<Car> findByID(@Param("id") Long id);

    
}
