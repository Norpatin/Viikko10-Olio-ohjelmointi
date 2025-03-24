package com.example.viikko10;

public class CarData {
    private String type;
    private int amount;
    private int car1;
    private int pakettiauto;
    private int kuormaAuto;
    private int linjaAuto;
    private int erikoisAuto;

    public CarData(String type, int amount, int car1, int pakettiauto, int kuormaAuto, int linjaAuto, int erikoisAuto) {
        this.type = type;
        this.amount = amount;
        this.car1 = car1;
        this.pakettiauto = pakettiauto;
        this.kuormaAuto = kuormaAuto;
        this.linjaAuto = linjaAuto;
        this.erikoisAuto = erikoisAuto;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }
    public int getErikoisAuto() {
        return erikoisAuto;
    }

    public void setErikoisAuto(int erikoisAuto) {
        this.erikoisAuto = erikoisAuto;
    }

    public int getLinjaAuto() {
        return linjaAuto;
    }

    public void setLinjaAuto(int linjaAuto) {
        this.linjaAuto = linjaAuto;
    }

    public int getKuormaAuto() {
        return kuormaAuto;
    }

    public void setKuormaAuto(int kuormaAuto) {
        this.kuormaAuto = kuormaAuto;
    }

    public int getPakettiauto() {
        return pakettiauto;
    }

    public void setPakettiauto(int pakettiauto) {
        this.pakettiauto = pakettiauto;
    }

    public int getCar1() {
        return car1;
    }

    public void setCar1(int car1) {
        this.car1 = car1;
    }
}
