package com.jcpv.example.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Created by JanCarlo on 22/08/2017.
 */
@Entity
@Table(name="users")
public class UserInfo {
    @Id
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "country")
    private String country;
    @Column(name = "enabled")
    private short enabled;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public short getEnabled() {
        return enabled;
    }

    public void setEnabled(short enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;

        UserInfo userInfo = (UserInfo) o;

        if (getEnabled() != userInfo.getEnabled()) return false;
        if (getUserName() != null ? !getUserName().equals(userInfo.getUserName()) : userInfo.getUserName() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(userInfo.getPassword()) : userInfo.getPassword() != null)
            return false;
        if (getRole() != null ? !getRole().equals(userInfo.getRole()) : userInfo.getRole() != null) return false;
        if (getFullName() != null ? !getFullName().equals(userInfo.getFullName()) : userInfo.getFullName() != null)
            return false;
        return getCountry() != null ? getCountry().equals(userInfo.getCountry()) : userInfo.getCountry() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserName() != null ? getUserName().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        result = 31 * result + (getFullName() != null ? getFullName().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (int) getEnabled();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", fullName='" + fullName + '\'' +
                ", country='" + country + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}

