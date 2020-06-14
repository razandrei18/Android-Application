package com.example.mytown;

public class Restanurant {

    private String restDenumire;
    private String restDescriere;
    private String restImg;

    public Restanurant(String restDenumire, String restDescriere, String restImg) {
        this.restDenumire = restDenumire;
        this.restDescriere = restDescriere;
        this.restImg = restImg;
    }

    public String getRestDenumire() {
        return restDenumire;
    }

    public void setRestDenumire(String restDenumire) {
        this.restDenumire = restDenumire;
    }

    public String getRestDescriere() {
        return restDescriere;
    }

    public void setRestDescriere(String restDescriere) {
        this.restDescriere = restDescriere;
    }

    public String getRestImg() {
        return restImg;
    }

    public void setRestImg(String restImg) {
        this.restImg = restImg;
    }
}
