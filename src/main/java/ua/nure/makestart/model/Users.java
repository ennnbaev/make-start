package ua.nure.makestart.model;

import jakarta.persistence.*;

import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Users {
    @Id
    @Column(name = "user_id")
    private String id = UUID.randomUUID().toString();
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private int age;
    @Column
    private String password;
    @Column
    private String username;
    @Column
    private String email;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cv_id")
    private Cv cv;


}
