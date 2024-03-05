package com.martin.joukl.cv02martinjoukl.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;
import java.util.List;
/*
ddl-auto - hodnoty: create, create-drop, none, update, validate

 */

@Getter
@Setter
@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username")
    @NotNull
    @NotBlank
    @Length(max = 255)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private boolean active;

    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "update_date")
    private Date updateDate;

    @OneToMany(mappedBy = "appUser")
    private List<Task> tasks;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "app_user_role",
            joinColumns = {@JoinColumn(name = "app_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private List<Role> roles;
}
