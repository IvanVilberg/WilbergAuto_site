package com.site.autosite.manuals;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.site.autosite.car.Car;
import com.site.autosite.car.CarService;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ManualController {

    private final CarService carService;
    private final ManualService manualService;

    public ManualController(ManualService manualService, CarService carService){
        this.manualService = manualService;
        this.carService = carService;
    }

    @GetMapping("/car/{carId}/manual")
    public String getManualsByCarId(@PathVariable Long carId, Model model) {
        Car car = carService.getCarById(carId);
        model.addAttribute("car", car);
        List<Manual> manual = manualService.getManualsByCarId(carId);
        model.addAttribute("manual", manual);
        return "manual"; 
    }

    @GetMapping("/car/{carId}/manual/create")
    public String showManualCreationForm(@PathVariable long carId, Model model) {
        Car car = carService.getCarById(carId);
        model.addAttribute("car", car);
        model.addAttribute("manualDto", new ManualDto());
        return "createItem"; 
    }

    @PostMapping("/car/{carId}/manual/create")
    public String createManual(Model model, @ModelAttribute ManualDto manualDto,
        @PathVariable Long carId) {
        try {
            Car car = carService.getCarById(carId);

            Manual manual = new Manual();
            manual.setCar(car);
            manual.setTheme(manualDto.getTheme());
            manual.setItem(manualDto.getItem());
            manualService.addNewManual(manual);

            return "redirect:/car/" + carId + "/manual"; 
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/manual/create"; 
        }
    }

    @GetMapping("/manual/{id}")
    public String showManualDetails(@PathVariable Long id, Model model) {
        Manual manual = manualService.getManualById(id).getBody();
        model.addAttribute("manual", manual);
        return "topic";
    }

    // @GetMapping("/manual")
    // public List<Manual> getManuals(){

    //     return manualService.getManuals();
    // }

    // @PostMapping("/manual")
    // public Manual registerNewManual(@RequestBody Manual manual){
    //     return manualService.addNewManual(manual);
    // }

    // @GetMapping("/manual/{id}")
    // public ResponseEntity<Manual> getManualById(@PathVariable Long id){
    //     return manualService.getManualById(id);
    // }

    // @PutMapping("/manual/{id}")
    // public ResponseEntity<Manual> updateManual(@PathVariable Long id, @RequestBody Manual manual){
    //     return manualService.updateManualById(id, manual);
    // }

    // @DeleteMapping("/manual/{id}")
    // public ResponseEntity<Map<String, Boolean>> deleteManual(@PathVariable Long id){
    //     return manualService.deleteManual(id);
    // }

}
