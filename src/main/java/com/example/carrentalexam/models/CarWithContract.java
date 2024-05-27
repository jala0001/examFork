package com.example.carrentalexam.models;

import java.util.List;

public class CarWithContract {
    private Car car;
    private int rentalContractId;
    private List<Damage> damages;


    public CarWithContract() {
    }


    public CarWithContract(Car car, int rentalContractId) { // bruges til listen af biler som skal behandles, men hvor evt skader endnu ikke er oprettet.
        this.car = car;
        this.rentalContractId = rentalContractId;
    }

    public CarWithContract(Car car, int rentalContractId, List<Damage> damages) { // Bruges til listen af biler hvor skader er blevet registreret.
        this.car = car;
        this.rentalContractId = rentalContractId;
        this.damages = damages;
    }


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


    public int getRentalContractId() {
        return rentalContractId;
    }

    public void setRentalContractId(int rentalContractId) {
        this.rentalContractId = rentalContractId;
    }

    public List<Damage> getDamages() {
        return damages;
    }

    public void setDamages(List<Damage> damages) {
        this.damages = damages;
    }
}
