package com.shraddha.ranjantasker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "task_seeker_account")
public class TaskSeekerAccount {

    @Id
    private Integer userAccountId;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    @MapsId
    private Users userId;

    private String firstName;
    private String surname;
    private String city;
    private String country;
    private String employmentType;
    private String cv;

    @Column(nullable = true, length = 64)
    private String profilePhoto;

    public TaskSeekerAccount() {
    }

    public TaskSeekerAccount(Users userId) {
        this.userId = userId;
    }

    public TaskSeekerAccount(int userAccountId, Users userId, String firstName, String surname, String city, String country, String employmentType, String cv, String profilePhoto) {
        this.userAccountId = userAccountId;
        this.userId = userId;
        this.firstName = firstName;
        this.surname = surname;
        this.city = city;
        this.country = country;
        this.employmentType = employmentType;
        this.cv = cv;
        this.profilePhoto = profilePhoto;
    }

    public Integer getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Integer userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @Transient
    public String getPhotosImagePath(){
        if(profilePhoto==null || userAccountId == null)
            return null;
        return "/photos/candidate/" + userAccountId + "/" + profilePhoto;
    }
    @Override
    public String toString() {
        return "TaskSeekerAccount{" +
                "userAccountId=" + userAccountId +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", employmentType='" + employmentType + '\'' +
                ", cv='" + cv + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                '}';
    }
}
