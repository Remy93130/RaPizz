package fr.esiee.rapizz.model;

import java.util.Objects;

public class Adresse {
    private int id;
    private String city;
    private String road;
    private int number;
    private String zipCode;



    public Adresse(int id, String city, String road, int number, String zipCode) {
        this.id = id;
        this.city = Objects.requireNonNull(city);
        this.road = Objects.requireNonNull(road);
        this.number = number;
        this.zipCode = Objects.requireNonNull(zipCode);
    }

    public Adresse(String city, String road, int number, String zipCode) {
        this(-1, city, road, number, zipCode);
    }

    public int getId() {
        return id;
    }

    public Adresse setId(int id) {
        this.id = id;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Adresse setCity(String city) {
        this.city = city;
        return this;
    }

    public String getRoad() {
        return road;
    }

    public Adresse setRoad(String road) {
        this.road = road;
        return this;
    }

    public int getNumber() {
        return number;
    }

    public Adresse setNumber(int number) {
        this.number = number;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Adresse setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adresse adresse = (Adresse) o;
        return number == adresse.number &&
                city.equals(adresse.city) &&
                road.equals(adresse.road) &&
                zipCode.equals(adresse.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, road, number, zipCode);
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", road='" + road + '\'' +
                ", number=" + number +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
