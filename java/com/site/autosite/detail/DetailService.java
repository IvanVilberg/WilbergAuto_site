package com.site.autosite.detail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class DetailService {
    
    
    private final DetailRepository detailRepository;

    public DetailService(DetailRepository detailRepository){
        this.detailRepository = detailRepository;
    }
    
    public Detail findByArticle(@PathVariable Long article){
        return detailRepository.findById(article).get();
    }

    public List<Detail> getDetails() {
        return  detailRepository.findAll();
    }

    public Detail addNewDetail(Detail detail) {
        System.out.println(detail);
        return detailRepository.save(detail);
    }

    public ResponseEntity<Detail> getDetailById(@PathVariable Long article) {
        Detail detail = detailRepository.findById(article).orElseThrow();
        return ResponseEntity.ok(detail);
    }

    public ResponseEntity<Detail> updateDetailById(@PathVariable Long article, @RequestBody Detail detailInfo){
        Detail detail  = detailRepository.findById(article)
            .orElseThrow();
        detail.setArticle(detailInfo.getArticle());
        detail.setName(detailInfo.getName());
        detail.setRussiaPrice(detailInfo.getRussiaPrice());
        detail.setGermanyPrice(detailInfo.getGermanyPrice());
        detail.setJapanPrice(detailInfo.getJapanPrice());
        detail.setKoreaPrice(detailInfo.getKoreaPrice());
        
        Detail updatedDetail = detailRepository.save(detail);
        return ResponseEntity.ok(updatedDetail);
    }

    public ResponseEntity<Map<String, Boolean>> deleteDetail(@PathVariable Long article){
        Detail detail = detailRepository.findById(article)
            .orElseThrow();
        detailRepository.delete(detail);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
