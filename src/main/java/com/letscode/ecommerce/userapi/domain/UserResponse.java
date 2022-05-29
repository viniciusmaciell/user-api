package com.letscode.ecommerce.userapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
}
