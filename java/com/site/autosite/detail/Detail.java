package com.site.autosite.detail;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table
public class Detail {
    @Id
    private Long article;
    private String name;
    private String russiaPrice;
    private String germanyPrice;
    private String japanPrice;
    private String koreaPrice;
    private String image;


    public Detail(){}
    
    public Detail(Long article, String name, String russiaPrice, String germanyPrice, String japanPrice,
            String koreaPrice, String image) {
        this.article = article;
        this.name = name;
        this.russiaPrice = russiaPrice;
        this.germanyPrice = germanyPrice;
        this.japanPrice = japanPrice;
        this.koreaPrice = koreaPrice;
        this.image = image;
    }



    public Detail(String name, String russiaPrice, String germanyPrice, String japanPrice,
            String koreaPrice, String image) {
        this.name = name;
        this.russiaPrice = russiaPrice;
        this.germanyPrice = germanyPrice;
        this.japanPrice = japanPrice;
        this.koreaPrice = koreaPrice;
        this.image = image;
    }

    public Long getArticle() {
        return article;
    }

    public void setArticle(Long article) {
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRussiaPrice() {
        return russiaPrice;
    }

    public void setRussiaPrice(String russiaPrice) {
        this.russiaPrice = russiaPrice;
    }

    public String getGermanyPrice() {
        return germanyPrice;
    }

    public void setGermanyPrice(String germanyPrice) {
        this.germanyPrice = germanyPrice;
    }

    public String getJapanPrice() {
        return japanPrice;
    }

    public void setJapanPrice(String japanPrice) {
        this.japanPrice = japanPrice;
    }

    public String getKoreaPrice() {
        return koreaPrice;
    }

    public void setKoreaPrice(String koreaPrice) {
        this.koreaPrice = koreaPrice;
    }

    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }

    
}
