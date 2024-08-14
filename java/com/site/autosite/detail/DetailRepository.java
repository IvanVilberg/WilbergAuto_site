package com.site.autosite.detail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
interface DetailRepository extends JpaRepository<Detail, Long>{

}