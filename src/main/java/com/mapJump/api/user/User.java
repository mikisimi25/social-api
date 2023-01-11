package com.mapJump.api.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="user_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
}
