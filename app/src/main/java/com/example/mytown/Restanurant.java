package com.example.mytown;

public class Restanurant {

    private String restDenumire;
    private String restDescriere;
    private String restImg;
    private String restContact;

    public Restanurant(String restDenumire, String restDescriere, String restImg, String restContact) {
        this.restDenumire = restDenumire;
        this.restDescriere = restDescriere;
        this.restImg = restImg;
        this.restContact = restContact;
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

    public String getRestContact() {
        return restContact;
    }

    public void setRestContact(String restContact) {
        this.restContact = restContact;
    }
}
