package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Uzer")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String hashedPassword;
    @Column(nullable = false, unique = true)
    private String emailAddress;
    @Column()
    private Boolean userPrivate = false;

    // todo: add column  to other entities
    //  https://www.baeldung.com/jpa-unique-constraints
    public User(String userName, String hashedPassword, String emailAddress) {
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
