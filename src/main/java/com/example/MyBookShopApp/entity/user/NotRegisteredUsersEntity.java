package com.example.MyBookShopApp.entity.user;

import javax.persistence.*;

@Table(name = "not_registered_users")
@Entity
public class NotRegisteredUsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code_user")
    private Integer code_user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUser() {
        return code_user;
    }

    public void setUser(Integer code_user) {
        this.code_user = code_user;
    }
}