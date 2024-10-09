package com.demoapi.democrud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Permission {
    @Id
    String name;

    @Column(name = "description" , columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String description;

    public static List<String> getKeyNames(){
        List<String> k = new ArrayList<>();
        k.add("name");
        k.add("description");
        return k;
    }
}
