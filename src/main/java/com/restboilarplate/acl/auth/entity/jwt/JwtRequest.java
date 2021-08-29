package com.restboilarplate.acl.auth.entity.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {

    @NotBlank(message = "username must not be blank")
    String username;

    @Size(message = "password size must be 5 to 14", min = 4,max = 14)
    @NotBlank
    String password;
}
