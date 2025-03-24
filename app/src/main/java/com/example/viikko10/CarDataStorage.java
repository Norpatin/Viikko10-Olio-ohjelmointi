package com.example.viikko10;

import java.util.ArrayList;

public class CarDataStorage {

    String city;
    int year;
    private ArrayList<CarData> carDatas = new ArrayList<CarData>();
    private static CarDataStorage carDataStorage = null;

    private CarDataStorage(){
    }

    static public CarDataStorage getInstance() {
        if(carDataStorage == null) {
            carDataStorage = new CarDataStorage();
        }
        return carDataStorage;
    }

    public ArrayList<CarData> getCarData() {
        return carDatas;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void clearData() {
        carDatas.clear();
    }

    public String getCity() {
        return city;
    }

    public int getYear() {
        return year;
    }

    public void addCarData(CarData carData) {
        carDatas.add(carData);
    }
}

