package com.example.MyBookShopApp.entity.user;

import javax.persistence.*;

@Table(name = "not_registered_users")
@Entity
public class UsersEntityNR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code_user")
    private Integer codeUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUser() {
        return codeUser;
    }

    public void setUser(Integer code_user) {
        this.codeUser = code_user;
    }
}