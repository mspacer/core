package com.core.clone;

public class Address implements Cloneable {
    //public final String country = "Ukr";
    public final Country country = new Country("Ukr");
    private String city;

    public Address(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    protected Address clone() throws CloneNotSupportedException {
        Address clone =  (Address)super.clone();
        // Нельзя создать копию финального поля. Для объектов будет не полное клонирование
        //clone.country = country.clone();
        return clone;
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
