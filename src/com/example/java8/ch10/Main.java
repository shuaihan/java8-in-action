package com.example.java8.ch10;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Optional<Car> optCar = Optional.empty();
        // If car were null, a NullPointerException would be
        // immediately thrown (rather than getting a latent error once you try to access properties of the car).
        Car car = new Car();
        Optional<Car> optCar2 = Optional.of(car);

        Optional<Car> optCar3 = Optional.ofNullable(car);

        Insurance insurance = new Insurance();
        Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
        Optional<String> name = optInsurance.map(Insurance::getName);



    }

    public String getCarInsuranceName(Person person) {
        Optional<Person> optPerson = Optional.of(person);
        Optional<String> name =
                optPerson.flatMap(Person::getCar)
                        .flatMap(Car::getInsurance)
                        .map(Insurance::getName);
        return name.orElse("Unknown");
    }
    /*
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

    // Null-safe attempt 2: too many exits
    public String getCarInsuranceNameNullSafe2(Person person) {
        if(person == null) {
            return "Unknown";
        }

        Car car = person.getCar();
        if( car == null) {
            return "Unknown";
        }

        Insurance insurance = car.getInsurance();
        if(insurance == null) {
            return "Unknown";
        }

        return insurance.getName();
    }
    */
}
