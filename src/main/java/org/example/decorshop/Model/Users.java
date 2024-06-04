package org.example.decorshop.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(nullable = true, length = 20)
    private String first_name;

    @Column(nullable = false, unique = false, length = 20)
    private String last_name;

    @Column(nullable = false, unique = false, length = 50)
    private String email;

    @Column(name = "password", nullable =  false)
    private String password;

//    @Column(name = "repeat_password")
//    private String repeat_password;

    @Column(name = "role")
    private String role;

//    @OneToOne(mappedBy = "user")
//    private Cart cart;
}
