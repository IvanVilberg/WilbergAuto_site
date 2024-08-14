package com.site.autosite.detail;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;

public class DetailDto {
    @NotEmpty
    private Long article;
    @NotEmpty
    private String name;
    @NotEmpty
    private String russiaPrice;
    @NotEmpty
    private String germanyPrice;
    @NotEmpty
    private String japanPrice;
    @NotEmpty
    private String koreaPrice;
    
    private MultipartFile imageFile;

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

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    
}
