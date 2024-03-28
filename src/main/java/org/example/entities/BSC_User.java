package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BSC_User {

    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String hashedPassword;
    private String emailAddress;

    public BSC_User() {
    }

    public BSC_User(String userName, String hashedPassword, String emailAddress) {
        this.userName = userName;
        this.hashedPassword = hashedPassword;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
