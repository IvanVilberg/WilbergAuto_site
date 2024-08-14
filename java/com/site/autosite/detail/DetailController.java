package com.site.autosite.detail;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.site.autosite.car.Car;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/detail")
public class DetailController {

    private final DetailService detailService;

    public DetailController(DetailService detailService){
        this.detailService = detailService;
    }

    @GetMapping("")
    public String showDetail(Model model){        
        List<Detail> detailList = detailService.getDetails();
        
        model.addAttribute("detail", new Detail());
        return "detail";
    }

    @PostMapping("search")
    public String searchDetail (@ModelAttribute() Detail formData, Model model) {
        
        Detail detail = detailService.findByArticle(formData.getArticle());
        model.addAttribute("detail", detail);

        return "detail";
    }
    


    @GetMapping("/create")
    public String showFromRegisterDetail(Model model){
        DetailDto detailDto = new DetailDto();
        model.addAttribute("detailDto", detailDto);
        return "createDetail";
    }

    @PostMapping("/create")
    public String RegisterDetail(@Valid @ModelAttribute DetailDto detailDto, BindingResult result) {
        if (detailDto.getImageFile().isEmpty()) {
            result.addError(new FieldError("detailDto", "imageFile", "null"));
        }

        if (result.hasErrors()) {
            return "createDetail";
        }

        //save
        MultipartFile image = detailDto.getImageFile();
        String storageFileName = image.getOriginalFilename();

        try {

            String uploadDir = "src/main/resources/static/images/details/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try(InputStream inputStream = image.getInputStream()){
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName));
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        Detail detail = new Detail();
        detail.setArticle(detailDto.getArticle());
        detail.setName(detailDto.getName());
        detail.setRussiaPrice(detailDto.getRussiaPrice());
        detail.setGermanyPrice(detailDto.getGermanyPrice());
        detail.setJapanPrice(detailDto.getJapanPrice());
        detail.setKoreaPrice(detailDto.getKoreaPrice());
        detail.setImage(storageFileName);

        detailService.addNewDetail(detail);

        return "redirect:/detail";
    }
    

    
    // @GetMapping("/detail")
    // public List<Detail> getDetails() {
    //     return detailService.getDetails();
    // }

    // @PostMapping("/detail")
    // public Detail registerNewDetail(@RequestBody Detail detail){
    //     return detailService.addNewDetail(detail);
    // }

    // @GetMapping("/detail/{id}")
    // public ResponseEntity<Detail> getDetailById(@PathVariable Long id) {
    //     return detailService.getDetailById(id);
    // }

    // @PutMapping("/detail/{id}")
    // public ResponseEntity<Detail> updateDetail(@PathVariable Long id, @RequestBody Detail detailInfo){
    //     return detailService.updateDetailById(id, detailInfo);
    // }
    
    // @DeleteMapping("/detail/{id}")
    // public ResponseEntity<Map<String, Boolean>> deleteDetail(@PathVariable Long id){
    //     return detailService.deleteDetail(id);
    // }
}
