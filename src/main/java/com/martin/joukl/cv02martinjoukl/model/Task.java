package com.martin.joukl.cv02martinjoukl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "title")
    private String Title;
    @Column(name = "description")
    private String description;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "update_date")
    private Date updateDate;

    @ManyToOne()
    @JoinColumn(name = "author_id", nullable = false)
    @ToString.Exclude
    private AppUser appUser;
}
