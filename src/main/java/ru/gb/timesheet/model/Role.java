package ru.gb.timesheet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//public enum Role{
//
//    ADMIN("admin"),USER("user");
//
//    private final String name;
//
//    Role(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//}
    @Data
    @Entity
    @Table(name = "roles")
    //@RequiredArgsConstructor
    public class Role {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        //@EqualsAndHashCode.Include
         Long id;
        @Column(name = "name")
         String name;

        @ToString.Exclude
        @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
        private List<User> users;

}
