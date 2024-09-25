package com.demoapi.democrud.entity;

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
    String description;

    public static List<String> getKeyNames(){
        List<String> k = new ArrayList<>();
        k.add("name");
        k.add("description");
        return k;
    }
}
