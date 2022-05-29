package com.letscode.ecommerce.userapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
