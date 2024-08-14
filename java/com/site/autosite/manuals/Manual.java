package com.site.autosite.manuals;

import com.site.autosite.car.Car;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Manual {
    
    @Id
    @GeneratedValue
    private Long id;
    private String theme;
    private String item;
    
    //connect to cars
    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;
    
    @Override
    public String toString() {
        return "Manual [id=" + id + ", theme=" + theme + ", item=" + item + ", car=" + car + "]";
    }

    public Manual(){}

    public Manual(String theme, String item, Car car) {
        this.theme = theme;
        this.item = item;
        this.car = car;
    }

    public Manual(Long id, String theme, String item, Car car) {
        this.id = id;
        this.theme = theme;
        this.item = item;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

        
}
