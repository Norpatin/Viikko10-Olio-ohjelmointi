package com.example.viikko10;

public class MunicipalityData {
    private int year;
    private int car1;
    private int pakettiauto;
    private int kuormaAuto;
    private int linjaAuto;
    private int erikoisAuto;

    public MunicipalityData(int y, int c1, int pa, int kA, int lA, int eA) {
        year = y;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCar1() {
        return car1;
    }

    public void setCar1(int car1) {
        this.car1 = car1;
    }

}
