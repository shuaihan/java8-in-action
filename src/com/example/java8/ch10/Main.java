package com.example.java8.ch10;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Person person = new Person();

    }

    public String getCarInsuranceName(Person person) {
        return person.getCar().getInsurance().getName();
    }

    //  Reducing NullPointerExceptions with defensive checking
    public String getCarInsuranceNameNullSafe(Person person) {
        if(person != null) {
            Car car = person.getCar();
            if(car != null) {
                Insurance insurance = car.getInsurance();
                if(insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
    }
}
