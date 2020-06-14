package com.example.mytown;

public class Cazare {
    private String cazareDenumire;
    private String cazareDetalii;
    private String cazareImg;

    public Cazare(String cazareDenumire, String cazareDetalii, String cazareImg) {
        this.cazareDenumire = cazareDenumire;
        this.cazareDetalii = cazareDetalii;
        this.cazareImg = cazareImg;
    }

    public String getCazareDenumire() {
        return cazareDenumire;
    }

    public void setCazareDenumire(String cazareDenumire) {
        this.cazareDenumire = cazareDenumire;
    }

    public String getCazareDetalii() {
        return cazareDetalii;
    }

    public void setCazareDetalii(String cazareDetalii) {
        this.cazareDetalii = cazareDetalii;
    }

    public String getCazareImg() {
        return cazareImg;
    }

    public void setCazareImg(String cazareImg) {
        this.cazareImg = cazareImg;
    }
}
