package com.jeevan.trainticketreservation.loginregisterservice.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="users")
public class Users implements UserDetails {

    @Id
    @GeneratedValue
    private int uId;

    public String UserId;

    @Column(unique = true)
    public String userName;


    public String userPassword;

    @Column(unique = true)
    @Pattern(regexp = "[A-Za-z0-9._]+@[A-Za-z0-9]+\\.[A-Z|a-z]{2,}")
    public String email;
    public int age;

    public String firstName;
    public String lastName;

    @Pattern(regexp = "\\+\\{12}")
    public String phoneNum;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    @JsonManagedReference
    public Set<Reservation> reservation;

    public Set<Reservation> getReservation() {
        return reservation;
    }

    public Users setReservation(Set<Reservation> reservation) {
        this.reservation = reservation;
        return this;
    }

    public Users() {
    }

    public Users(String userId, String userName, String userPassword, String email, int age, String firstName, String lastName, String phoneNum) {
        UserId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
    }

    public String getUserId() {
        return UserId;
    }

    public Users setUserId(String userId) {
        UserId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Users setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public Users setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Users setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Users setAge(int age) {
        this.age = age;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Users setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Users setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public Users setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        return this;
    }

    @Override
    public String toString() {
        return "Users{" +
                "UserId='" + UserId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
