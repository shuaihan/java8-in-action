package com.exmpale.java8.model;

/**
 * Created by shuaihan on 2017. 7. 17..
 */
public class Apple implements Fruit{

    private String color;
    private Integer weight ;

    public Apple(String color, Integer weight) {

        this.color = color;
        this.weight = weight;
    }

    public Apple(Integer weight) {
        this.weight = weight;

    }


    public Apple() {

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
