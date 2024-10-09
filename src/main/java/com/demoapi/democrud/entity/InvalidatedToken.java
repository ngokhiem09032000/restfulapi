package com.demoapi.democrud.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class InvalidatedToken {
    @Id
    String id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP")
    Date expiryTime;
}
