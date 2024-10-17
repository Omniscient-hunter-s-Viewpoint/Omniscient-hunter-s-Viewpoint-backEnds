package com.hunter.user.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDTO {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
