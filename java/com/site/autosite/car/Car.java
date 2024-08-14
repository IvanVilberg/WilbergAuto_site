package com.site.autosite.car;

import java.util.List;

import com.site.autosite.detail.Detail;
import com.site.autosite.manuals.Manual;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


//select with brand
@NamedQuery(
    name = "Car.findByBrand",
    query = "select c from Car c where c.brand = :brand"
)

//select with model
@NamedQuery(
    name = "Car.findByModel",
    query = "select c from Car c where c.model = :model"
)

//select with ID
@NamedQuery(
    name = "Car.findByID",
    query = "select c from Car c where c.id = :id"
)

@Entity
@Table
public class Car {

    @Id
    @SequenceGenerator(
        name = "car_sequence",
        sequenceName = "car_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "car_sequence"
    )
    private Long id;
    private String brand;
    private String model;
    private String carBody;
    private String image;

    
    @OneToMany(mappedBy = "car") 
    private List<Manual> manuals;

    public Car(){}

    

    @Override
    public String toString() {
        return "Car [id=" + id + ", brand=" + brand + ", model=" + model + ", carBody=" + carBody + ", image=" + image
                + "]";
    }



    public Car(Long id, String brand, String model, String carBody, List<Detail> details) {
        this.brand = brand;
        this.model = model;
        this.carBody = carBody;
    }

    public Car(Long id, String brand, String model, String carBody, List<Detail> details, String image) {
        this.brand = brand;
        this.model = model;
        this.carBody = carBody;
        this.image = image;
    }



    
    public Car(String brand, String model, String carBody, String image, List<Manual> manuals) {
        this.brand = brand;
        this.model = model;
        this.carBody = carBody;
        this.image = image;
        this.manuals = manuals;
    }



    public Car(Long id, String brand, String model, String carBody, String image, List<Manual> manuals) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.carBody = carBody;
        this.image = image;
        this.manuals = manuals;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarBody() {
        return carBody;
    }

    public void setCarBody(String carBody) {
        this.carBody = carBody;
    }



    public List<Manual> getManuals() {
        return manuals;
    }



    public void setManuals(List<Manual> manuals) {
        this.manuals = manuals;
    }


}

