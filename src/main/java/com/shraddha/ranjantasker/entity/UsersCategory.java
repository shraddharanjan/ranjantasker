package com.shraddha.ranjantasker.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users_category")
public class UsersCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userCategoryId;

    private String userCategoryName;

    @OneToMany(targetEntity = Users.class, mappedBy = "userCategoryId", cascade = CascadeType.ALL)
    private List<Users> users;

    public UsersCategory() {
    }

    public UsersCategory(int userCategoryId, String userCategoryName, List<Users> users) {
        this.userCategoryId = userCategoryId;
        this.userCategoryName = userCategoryName;
        this.users = users;
    }

    public int getUserCategoryId() {
        return userCategoryId;
    }

    public void setUserCategoryId(int userCategoryId) {
        this.userCategoryId = userCategoryId;
    }

    public String getUserCategoryName() {
        return userCategoryName;
    }

    public void setUserCategoryName(String userCategoryName) {
        this.userCategoryName = userCategoryName;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UsersCategory{" +
                "userCategoryId=" + userCategoryId +
                ", userCategoryName='" + userCategoryName + '\'' +
                '}';
    }
}
