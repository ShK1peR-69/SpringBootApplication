package com.itis.master.practice.simpleapp.entitites;

import javax.persistence.*;

/*****
 * @author Igor Astafyev
 * June, 2019
 * "Users" entity
 *****/

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "user_seq", sequenceName = "users_id_seq",
            initialValue = 51, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_seq")
    private Long id;

    @Column(name = "fio")
    private String fio;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "approved")
    private boolean approved;

    @Column(name = "validkey")
    private String validkey;

    public User() {
    }

    public User(String fio, int age, String email, String password) {
        this.fio = fio;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getValidkey() {
        return validkey;
    }

    public void setValidkey(String validkey) {
        this.validkey = validkey;
    }

    public User(String fio, int age, String email, String password,
                String role, boolean approved, String validkey) {
        this.fio = fio;
        this.age = age;
        this.email = email;
        this.password = password;
        this.role = role;
        this.approved = approved;
        this.validkey = validkey;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", approved=" + approved +
                ", validkey='" + validkey + '\'' +
                '}';
    }
}