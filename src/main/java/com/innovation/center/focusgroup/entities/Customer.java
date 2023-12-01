package com.innovation.center.focusgroup.entities;

import lombok.Getter;
import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String username;


    @Column(nullable = false, length = 64)
    private String password;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER, mappedBy = "customer")
    private List<Answer> answers;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
