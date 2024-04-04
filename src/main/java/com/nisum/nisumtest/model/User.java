package com.nisum.nisumtest.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @Id
    private UUID userId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<Phone> phones;

    private Date createdAt;
    private Date updatedAt;
    private Date loggedAt;

    private String token;
    private Boolean isActive;

    public static User getUserBuilder(User user) {
        UUID userId = UUID.randomUUID();
        return User.builder()
                .userId(userId)
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phones(Phone.getPhoneListBuilder(user.getPhones(), userId))
                .createdAt(new Date())
                .updatedAt(null)
                .loggedAt(new Date())
                .isActive(Boolean.TRUE)
                .token(user.getToken())
                .build();
    }
}
