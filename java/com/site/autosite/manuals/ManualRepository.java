package com.site.autosite.manuals;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManualRepository extends JpaRepository<Manual, Long> {
    List<Manual> findByCarId(Long carId);
}

