package com.project.user.service.entites;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="micro_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name="ID")
    private String userId;
    @Column(name="NAME",length = 20,nullable = false)
    private String name;
    @Column(name="EMAIL",nullable = false)
    private String email;

    @Transient
    private List<Rating> ratings;

}
