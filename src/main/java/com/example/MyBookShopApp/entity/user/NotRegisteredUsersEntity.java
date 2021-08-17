package com.example.MyBookShopApp.entity.user;

import javax.persistence.*;

@Table(name = "not_registered_users")
@Entity
public class NotRegisteredUsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user")
    private Integer user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}