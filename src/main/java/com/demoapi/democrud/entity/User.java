package com.demoapi.democrud.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "user_name" , unique = true, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String userName;
    String passWord;
    String fullName;
    LocalDate birthDate;

    @ManyToMany
    Set<Role> roles;

    public static List<String> getKeyNames(){
        List<String> k = new ArrayList<>();
        k.add("id");
        k.add("userName");
        k.add("passWord");
        k.add("fullName");
        k.add("birthDate");
        k.add("roles");
        return k;
    }
}
