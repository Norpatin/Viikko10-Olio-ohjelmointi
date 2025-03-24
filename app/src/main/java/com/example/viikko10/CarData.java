package com.example.viikko10;

public class CarData {

    private String type;
    private int amount;
    private int car1;
    private int pakettiauto;
    private int kuormaAuto;
    private int linjaAuto;
    private int erikoisAuto;

    public CarData(String type, int amount){
        this.type = type;
        this.amount = amount;
    }

    public CarData(String t, int y, int c1, int pa, int kA, int lA, int eA) {
        type = t;
        amount = y;
        car1 = c1;
        pakettiauto = pa;
        kuormaAuto = kA;
        linjaAuto = lA;
        erikoisAuto = eA;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCar() {
        return car1;
    }

    public void setCar(int car1) {
        this.car1 = car1;
    }

}
