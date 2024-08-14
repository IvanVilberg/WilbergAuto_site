package com.site.autosite.manuals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ManualService {
    
    private final ManualRepository manualRepository;

    public ManualService(ManualRepository manualRepository){
        this.manualRepository = manualRepository;
    }

    public List<Manual> getManualsByCarId(Long carId) {
        return manualRepository.findByCarId(carId);
    }
    

    public Manual addNewManual(Manual manual){
        System.out.println(manual);
        return manualRepository.save(manual);
    }

    public ResponseEntity<Manual> getManualById(@PathVariable Long id){
        Manual manual = manualRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(manual);
    }

    public ResponseEntity<Manual> updateManualById(@PathVariable Long id, @RequestBody Manual manualInfo){
        Manual manual = manualRepository.findById(id).orElseThrow();
        manual.setTheme(manualInfo.getTheme());
        manual.setItem(manualInfo.getItem());
        manual.setCar(manualInfo.getCar());
        Manual updatedManual = manualRepository.save(manual);
        return ResponseEntity.ok(updatedManual);
    }

    public ResponseEntity<Map<String, Boolean>> deleteManual(@PathVariable Long id){
        Manual manual = manualRepository.findById(id).orElseThrow();
        manualRepository.delete(manual);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
