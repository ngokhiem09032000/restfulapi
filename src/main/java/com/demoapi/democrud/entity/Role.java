package com.demoapi.democrud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Role {
    @Id
    String name;

    @Column(name = "description" , columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String description;

    @ManyToMany
    Set<Permission> permissions;

    public static List<String> getKeyNames(){
        List<String> k = new ArrayList<>();
        k.add("name");
        k.add("description");
        k.add("permissions");
        return k;
    }
}
