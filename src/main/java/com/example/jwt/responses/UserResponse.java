package com.example.jwt.responses;


import com.example.jwt.Entities.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {

    private Long idUser;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String adresse;
    private Role role;

}
