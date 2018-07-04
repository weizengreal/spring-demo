package com.woai662.demo.entity;


import javax.persistence.*;

@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;

    private String name;

    private String password;

    public long getUserid(){
        return this.userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String  getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
