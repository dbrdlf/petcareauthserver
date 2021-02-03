package com.yukil.petcareauthserver.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<AuthType> authType;
    private LocalDate localDate;

}
