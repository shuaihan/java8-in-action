package com.exmpale.java8.model;

/**
 * Created by shuaihan on 2017. 7. 17..
 */
public class Apple {

    private String color;

    public Apple(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                '}';
    }
}
