package com.site.autosite.car;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/catalogue")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService){
        this.carService = carService;
    }

    @GetMapping("")
    public String catalogue(Model model){

        List<Car> cars = carService.getCars();

        Map<String, List<Car>> carsByBrand = cars.stream()
            .collect(Collectors.groupingBy(Car::getBrand));

        model.addAttribute("carsByBrand", carsByBrand);
        return "catalogue";
    }

    @GetMapping("/create")
    public String showFormRegisterNewCar(Model model){
        CarDto carDto = new CarDto();
        model.addAttribute("carDto", carDto);
        return "createCar";
    }

    @PostMapping("/create")
    public String RegisterNewCar(@Valid @ModelAttribute CarDto carDto, BindingResult result){
        
        if (carDto.getImageFile().isEmpty()) {
            result.addError(new FieldError("carDto", "imageFile", "null"));
        }

        if (result.hasErrors()) {
            return "createCar";
        }

        //save
        MultipartFile image = carDto.getImageFile();
        String storageFileName = image.getOriginalFilename();

        try {
            String uploadDir = "src/main/resources/static/images/auto/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()){
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING); 
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        Car car = new Car();
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setCarBody(carDto.getCarBody());
        car.setImage(storageFileName);

        carService.save(car);

        return "redirect:/catalogue";
    }

    @GetMapping("/edit")
    public String showFormEditCar(Model model, @RequestParam Long id) {
        
        try {
            Car car = carService.getCarById(id);
            model.addAttribute("car", car);

            CarDto carDto = new CarDto();
            carDto.setBrand(car.getBrand());
            carDto.setModel(car.getModel());
            carDto.setCarBody(car.getCarBody());

            model.addAttribute("carDto", carDto);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/catalogue";
        }

        return "editCar";
    }

    @PostMapping("/edit")
    public String updateCar(Model model, @RequestParam Long id, @Valid @ModelAttribute CarDto carDto, BindingResult result) {
        
        try {
            Car car = carService.getCarById(id);
            model.addAttribute("car", car);
    
            if (result.hasErrors()) {
                return "editCar";
            }
    
            String uploadDir = "src/main/resources/static/images/auto/";
            Path oldImagePath = Paths.get(uploadDir + car.getImage());
    
            try {
                Files.deleteIfExists(oldImagePath);
            } catch (Exception e) {
                System.out.println("Error deleting old image: " + e.getMessage());
            }
    

            if (!carDto.getImageFile().isEmpty()) {
                MultipartFile image = carDto.getImageFile();
                String storageFileName = image.getOriginalFilename();
    
                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING); 
                    car.setImage(storageFileName);
                } catch (Exception e) {
                    System.out.println("Error copying new image: " + e.getMessage());
                }
            }
    
            car.setBrand(carDto.getBrand());
            car.setModel(carDto.getModel());
            car.setCarBody(carDto.getCarBody());
    

            carService.save(car);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    
        return "redirect:/catalogue";
    }
        
    @GetMapping("/delete")
    public String deleteCar(@RequestParam Long id) {

        try {
            
            Car car = carService.getCarById(id);

            Path imagePath = Paths.get("src/main/resources/static/images/auto/" + car.getImage());

            try {
                Files.deleteIfExists(imagePath);
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }

            carService.deleteCar(id);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        return "redirect:/catalogue";
    }
    

    
    // @GetMapping("/car")
    // public List<Car> getCars() {
    //     return carService.getCars();
    // }

    

    // @GetMapping("/car/{id}")
    // public ResponseEntity<Car> getCarById(@PathVariable Long id) {
    //     return carService.getCarById(id);
    // }

    // @PutMapping("/car/{id}")
    // public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carInfo){
    //     return carService.updateCarById(id, carInfo);
    // }
    
    // @DeleteMapping("/car/{id}")
    // public ResponseEntity<Map<String, Boolean>> deleteCar(@PathVariable Long id){
    //     return carService.deleteCar(id);
    // }
}
