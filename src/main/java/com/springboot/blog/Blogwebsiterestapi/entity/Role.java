package com.springboot.blog.Blogwebsiterestapi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.IdentityHashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles",uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}
