package com.shraddha.ranjantasker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TaskLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String city;

    private String country;

    public TaskLocation() {
    }

    public TaskLocation(Integer id, String city, String country) {
        Id = id;
        this.city = city;
        this.country = country;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "TaskLocation{" +
                "Id=" + Id +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
