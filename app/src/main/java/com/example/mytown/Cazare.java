package com.example.mytown;

public class Cazare {
    private String cazareDenumire;
    private String cazareEmailContact;
    private String cazareImg;
    private String cazareDescriere;
    private String cazareDetaliiContact;


    public Cazare(String cazareDenumire, String cazareEmailContact, String cazareImg, String cazareDescriere, String cazareDetaliiContact) {
        this.cazareDenumire = cazareDenumire;
        this.cazareEmailContact = cazareEmailContact;
        this.cazareImg = cazareImg;
        this.cazareDescriere=cazareDescriere;
        this.cazareDetaliiContact=cazareDetaliiContact;
    }

    public String getCazareDenumire() {
        return cazareDenumire;
    }

    public void setCazareDenumire(String cazareDenumire) {
        this.cazareDenumire = cazareDenumire;
    }

    public String getCazareEmailContact() {
        return cazareEmailContact;
    }

    public void setCazareEmailContact(String cazareDetalii) {
        this.cazareEmailContact = cazareDetalii;
    }

    public String getCazareImg() {
        return cazareImg;
    }

    public void setCazareImg(String cazareImg) {
        this.cazareImg = cazareImg;
    }

    public String getCazareDescriere() {
        return cazareDescriere;
    }

    public void setCazareDescriere(String cazareDescriere) {
        this.cazareDescriere = cazareDescriere;
    }

    public String getCazareDetaliiContact() {
        return cazareDetaliiContact;
    }

    public void setCazareDetaliiContact(String cazareDetaliiContact) {
        this.cazareDetaliiContact = cazareDetaliiContact;
    }
}
